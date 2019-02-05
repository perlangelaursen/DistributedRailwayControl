package s144449s144456.RailwayModelGenerator.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import network.Network;
import network.Segment;
import network.SegmentOneWay;
import network.Train;

public abstract class Translator extends AbstractHandler implements IHandler{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				Network n = getNetwork(structuredSelection.getFirstElement());
				String msg = "";
				if (n != null) {
					//Check routes
					for(Train t : n.getTrains()) {
						for(int i = 0; i < t.getRoute().size()-1; i++) {
							if(!isConnected(t.getRoute().get(i), t.getRoute().get(i+1))) {
								msg += "Error in train "+t.getId()+"'s route: Movement from segment "+t.getRoute().get(i).getId()+" to segment "+t.getRoute().get(i+1).getId()+" is not possible.\n";
							}
						}
					}
					
					if(msg.equals("")) {
						generateCode(n);
						msg = "Model file successfully generated.";
					}
					MessageDialog.openInformation(null, "Generate File", msg);
					
				}
			}
		}
		return null;
	}

	private boolean isConnected(Segment s1, Segment s2) {
		boolean b1 = s1.getEnd().getOutgoing().contains(s2); // --> [] -->
		boolean b2 = s1.getEnd().getIngoing().contains(s2); // --> [] <--
		boolean b3 = s1.getStart().getOutgoing().contains(s2); // <-- [] -->
		boolean b4 = s1.getStart().getIngoing().contains(s2); // <-- [] <--
		
		if(s1 instanceof SegmentOneWay) {
			if(s2 instanceof SegmentOneWay) { // --> [] -->
				return b1;	
			} else { // --> [] <-->
				return b1 || b2;
			}
		} else {
			if(s2 instanceof SegmentOneWay) { // <--> [] -->
				return b1 || b3;
			} else { // <--> [] <-->
				return b1 || b2 || b3 || b4;	
			}
		}
	}

	protected abstract void generateCode(Network n);
	
	private Network getNetwork(Object o) {
		if (o instanceof Network) {
			return (Network) o;
		} else if (o instanceof IAdaptable) {
			return ((IAdaptable) o).getAdapter(Network.class);
		}
		return null;
	}
}
