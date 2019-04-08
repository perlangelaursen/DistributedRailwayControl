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
	protected Map<SwitchBox, Integer> pointIDs;
	protected Map<ControlBox, Segment[]> controlBoxSegments;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("NEW SELECTION");
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				Network n = getNetwork(structuredSelection.getFirstElement());
				if (n != null && isWellFormed(n)) {
					//Generate code
					initialize(n);
					String msg = generateCode(n);
					
					//Display dialog
					MessageDialog.openInformation(null, "Generate File", msg);
				}
			}
		}
		return null;
	}

	private boolean isWellFormed(Network n) {
		String msg = "";
		
		//SIZE 
		if(n.getSegments().size() < 1) {
			msg += "Network must contain at least one segment.\n";
		}
		if(n.getControlBoxes().size() < 2) {
			msg += "Network must contain at least two control boxes.\n";
		}
		if(n.getTrains().size() < 1) {
			msg += "Network must contain at least one train.\n";
		}
		
		//CONTROL BOXES
		HashMap<SwitchBox, LinkedList<ControlBox>> conflicts = new HashMap<>();
		for(ControlBox cb : n.getControlBoxes()) {
			String id = getIDString(cb);
			if(cb instanceof SwitchBox ) {
				SwitchBox sb = (SwitchBox) cb;
				if(sb.getSegments().size() != 3) {
					msg += "Error in switch box "+id+": Switch box must be associated with exactly three segments.\n";
					for(Segment s : sb.getSegments()) {
						System.out.print(id+" has: ");
						System.out.println(s.toString());
					}
				}
				if(sb.getStem() == null) {
					msg += "Error in switch box "+id+": Stem segment has not been set.\n";
				} else if(!sb.getSegments().contains(sb.getStem())) {
					msg += "Error in switch box "+id+": Stem segment is not associated with switch box.\n";	
				}
				if(sb.getPlus() == null) {
					msg += "Error in switch box "+id+": Plus segment has not been set.\n";
				} else if(!sb.getSegments().contains(sb.getPlus())) {
					msg += "Error in switch box "+id+": Plus segment is not associated with switch box.\n";	
				}
				if(sb.getMinus() == null) {
					msg += "Error in switch box "+id+": Minus segment has not been set.\n";
				} else if(!sb.getSegments().contains(sb.getMinus())) {
					msg += "Error in switch box "+id+": Minus segment is not associated with switch box.\n";	
				}
				if(sb.getStem() != null && sb.getStem() == sb.getPlus()) {
					msg += "Error in switch box "+id+": Stem segment and plus segment must be different.\n";
				}
				if(sb.getStem() != null && sb.getStem() == sb.getMinus()) {
					msg += "Error in switch box "+id+": Stem segment and minus segment must be different.\n";
				}
				if(sb.getPlus() != null && sb.getMinus() == sb.getPlus()) {
					msg += "Error in switch box "+id+": Plus segment and minus segment must be different.\n";
				}
				
				for(ControlBox cb2 : n.getControlBoxes()) {
					if(cb2 instanceof SwitchBox && cb != cb2) {
						SwitchBox sb2 = (SwitchBox) cb2;
						String id2 = getIDString(sb2);
						LinkedList<ControlBox> cList2;

						if(conflicts.containsKey(sb2)) {
							cList2 = conflicts.get(sb2);
						} else {
							cList2 = new LinkedList<>();
						}
						
						if(!cList2.contains(sb) && sharedSegments(sb, sb2) > 1 && !(sb.getPlus() == sb2.getPlus() && sb.getMinus() == sb2.getMinus() && sb.getStem() != sb2.getStem())) {
							cList2.add(sb);
							conflicts.put(sb2, cList2);

							LinkedList<ControlBox> cList;
							if(conflicts.containsKey(sb)) {
								cList = conflicts.get(sb);
							} else {
								cList = new LinkedList<>();
							}
							cList.add(sb2);
							conflicts.put(sb, cList);
							msg += "Error in switch boxes "+id+" and "+id2+": Connections between the switch boxes are invalid.\n";							
						}
					}
				}
			} else if(cb.getSegments().size() < 1) {
				msg += "Error in regular control box "+id+": Control box must be associated with at least one segment.\n";
			}
		}
		//TRAINS
		for(Train t : n.getTrains()) {
			String id = getIDString(t);
			if(t.getRoute().size() < 1) {
				msg += "Train " + id + " must have at least one segment in its route.\n";
			} else {
				for(int i = 0; i < t.getRoute().size()-1; i++) {
					if(!areConnected(t.getRoute().get(i), t.getRoute().get(i+1))) {
						msg += "Error in train "+t.getId()+"'s route: Movement from segment "+t.getRoute().get(i).getId()+" to segment "+t.getRoute().get(i+1).getId()+" is not possible.\n";
					}
				}
			}
		}
		if(!msg.equals("")) {
			MessageDialog.openInformation(null, "Generate File", msg);
		}
		return msg.equals("");
	}

	private int sharedSegments(SwitchBox sb, SwitchBox sb2) {
		int count = 0;
		for(Segment s : sb.getSegments()) {
			if(sb2.getSegments().contains(s)) {
				count++;
			}
		}
		return count;
	}

	private String getIDString(Element c) {
		return ((c.getId() != null) ? c.getId() : "<id>");
	}

	private boolean areConnected(Segment s1, Segment s2) {
		return s1.getStart().getSegments().contains(s2) || s1.getEnd().getSegments().contains(s2);
	}

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
