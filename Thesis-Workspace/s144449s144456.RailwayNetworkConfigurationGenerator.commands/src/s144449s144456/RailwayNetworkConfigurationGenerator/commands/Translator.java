package s144449s144456.RailwayNetworkConfigurationGenerator.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import network.*;

public abstract class Translator extends AbstractHandler implements IHandler{
	protected Map<ControlBox, Integer> cbIDs;
	protected Map<Segment, Integer> segIDs;
	protected Map<Train, Integer> trainIDs;
	protected Map<ControlBox, Integer> pointIDs;
	protected Map<ControlBox, Segment[]> controlBoxSegments;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				Network n = getNetwork(structuredSelection.getFirstElement());
				String msg = "";
				if (n != null) {
					//Check routes
					for(Train t : n.getTrains()) {
						if(t.getId() == null || t.getId().equals("")) {
							t.setId("");
							//msg += "Error in train: Missing ID.\n";
						}
						for(int i = 0; i < t.getRoute().size()-1; i++) {
							if(!areConnected(t.getRoute().get(i), t.getRoute().get(i+1))) {
								msg += "Error in train "+t.getId()+"'s route: Movement from segment "+t.getRoute().get(i).getId()+" to segment "+t.getRoute().get(i+1).getId()+" is not possible.\n";
							}
						}
					}
					
					//Check control boxes
					for(ControlBox cb : n.getControlBoxes()) {
						if(cb.getId() == null || cb.getId().equals("")) {
							cb.setId("");
							//msg += "Error in control box: Missing ID.\n";
						}
						if(cb instanceof SwitchBox) {
							SwitchBox sb = (SwitchBox) cb;
							if(!sb.getSegments().contains(sb.getStem())) {
								msg += "Error in switch box "+sb.getId()+": Stem segment is not connected to switch box.\n";	
							}
							if(!sb.getSegments().contains(sb.getPlus())) {
								msg += "Error in switch box "+sb.getId()+": Plus segment is not connected to switch box.\n";	
							}
							if(!sb.getSegments().contains(sb.getMinus())) {
								msg += "Error in switch box "+sb.getId()+": Minus segment is not connected to switch box.\n";	
							}
							if(sb.getStem() == sb.getPlus()) {
								msg += "Error in switch box "+sb.getId()+": Stem segment and plus segment must be different.\n";
							}
							if(sb.getStem() == sb.getMinus()) {
								msg += "Error in switch box "+sb.getId()+": Stem segment and minus segment must be different.\n";
							}
							if(sb.getMinus() == sb.getPlus()) {
								msg += "Error in switch box "+sb.getId()+": Plus segment and minus segment must be different.\n";
							}
							if(sb.getSegments().size() > 3) {
								msg += "Error in control box "+sb.getId()+": Switch box has too many connected segments.\n";
							}
							
						} else {
							if(cb.getSegments().size() > 2) {
								msg += "Error in control box "+cb.getId()+": Regular control box has too many connected segments.\n";
							}
						}
					}
					//Check segments
					for(Segment s : n.getSegments()) {
						if(s.getId() == null || s.getId().equals("")) {
							s.setId("");
							//msg += "Error in segment: Missing ID.\n";
						}
					}
					
					//Generate code
					if(msg.equals("")) {
						initialize(n);
						msg = generateCode(n);
					}
					
					//Display dialog
					MessageDialog.openInformation(null, "Generate File", msg);
					
				}
			}
		}*/
		return null;
	}

/*
	private boolean isConnected(Segment s1, Segment s2) {
		boolean b1 = s1.getEnd().getOutgoing().contains(s2); // --> [] -->
		boolean b2 = s1.getEnd().getIngoing().contains(s2); // --> [] <--
		boolean b3 = s1.getStart().getOutgoing().contains(s2); // <-- [] -->
		boolean b4 = s1.getStart().getIngoing().contains(s2); // <-- [] <--
		
		ControlBox mid = getMidBox(s1, s2);
		boolean sw = true;
		if(mid instanceof SwitchBox) {
			Segment stem = ((SwitchBox) mid).getStem();
			sw = stem == s1 || stem == s2;
		}
		if(s1 instanceof SegmentOneWay) {
			if(s2 instanceof SegmentOneWay) { // --> [] -->
				return sw && b1;	
			} else { // --> [] <-->
				return sw && (b1 || b2);
			}
		} else {
			if(s2 instanceof SegmentOneWay) { // <--> [] -->
				return sw && (b1 || b3);
			} else { // <--> [] <-->
				return sw && (b1 || b2 || b3 || b4);	
			}
		}
	}*/

	protected abstract String generateCode(Network n);
	
	private Network getNetwork(Object o) {
		if (o instanceof Network) {
			return (Network) o;
		} else if (o instanceof IAdaptable) {
			return ((IAdaptable) o).getAdapter(Network.class);
		}
		return null;
	}
	
	
	
	private void initialize(Network n) {
		int i = 0;
		
		//Segments
		segIDs = new HashMap<>();
		i = 0;
		for(Segment s : n.getSegments()) {
			segIDs.put(s, i);
			i++;
		}
		
		//Control Boxes
		cbIDs = new HashMap<>();
		controlBoxSegments = new HashMap<>();
		i = 0;
		for(ControlBox cb : n.getControlBoxes()) {
			cbIDs.put(cb, i);

			Segment[] segments = new Segment[3];
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				segments[0] = sb.getStem();
				segments[1] = sb.getPlus();
				segments[2] = sb.getMinus();
			} else {
				segments[0] = cb.getSegments().get(0);
				if (cb.getSegments().size() > 1) {
					segments[1] = cb.getSegments().get(1);
					
					if (cb.getSegments().size() > 2) {
						segments[2] = cb.getSegments().get(2);
					}
				}
			}
			controlBoxSegments.put(cb, segments);
			i++;
		}
		
		pointIDs = new HashMap<>();
		i = 0;
		for(ControlBox cb : n.getControlBoxes()) {
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				pointIDs.put(sb, i);
				i++;
			}
		}
		

		
		//Trains
		i = 0;
		trainIDs = new HashMap<>();
		for(Train t : n.getTrains()) {
			trainIDs.put(t,i);
			i++;
		}
	}
}
