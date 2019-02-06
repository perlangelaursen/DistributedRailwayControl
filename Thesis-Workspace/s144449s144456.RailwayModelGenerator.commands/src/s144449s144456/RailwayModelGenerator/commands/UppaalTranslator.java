package s144449s144456.RailwayModelGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import network.*;

public class UppaalTranslator extends Translator{
	private Map<ControlBox, Integer> cbIDs;
	private Map<Segment, Integer> segIDs;
	private int id;
	
	@Override
	protected void generateCode(Network n) {
		if(n != null) {
			initialize(n);
			
			int nSwitch = 0;
			for(ControlBox s : n.getControlBoxes()) {
				if(s instanceof SwitchBox) {
					nSwitch++;
				}
			}
			String sizesString = "const int NCB = "+n.getControlBoxes().size()+";\n"+
						  			"const int NSEG = "+n.getSegments().size()+";\n"+
						  			"const int NT = "+n.getTrains().size()+";\n"+
						  			"const int NSWITCH = "+nSwitch+";\n";
			String typesString = "typedef int[0, NT-1] t_id;\r\n" + 
								"typedef int[0, NCB-1]  CB_id;\r\n" + 
								"typedef int[0, NSWITCH-1] SW_id;\r\n";
			
			//LOCKLIMIT + RESLIMIT
			
			//Control Boxes
			String cbsString= "const int controlBoxes[NT][NCB] = {";
			for(int i = 0; i < n.getTrains().size()-1; i++) {
				cbsString += trainCBs(n.getTrains().get(i)) + ", ";
			}
			cbsString += trainCBs(n.getTrains().get(n.getTrains().size()-1))+"};\n";
			

			
			String routesString = "const int routes[NT][NSEG] = {";
			for(int i = 0; i < n.getTrains().size()-1; i++) {
				routesString += trainRoute(n.getTrains().get(i))+", ";
			}
			routesString += trainRoute(n.getTrains().get(n.getTrains().size()-1))+"};\n";
			
			
			String cbDetailsString = "const int CBs[NCB][3] = {";
			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
				cbDetailsString += cbsDetails(n.getControlBoxes().get(i))+", ";
			}
			cbDetailsString += cbsDetails(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
			
			String switchesString = "const int switches[NCB] = {";
			id = 0;
			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
				ControlBox cb = n.getControlBoxes().get(i);
				switchesString += switchID(cb)+", ";
			} 
			switchesString += switchID(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"}\n";
			
			String channelsString = "chan resSeg[NCB][NT][NSEG]; //[switch box][train id][segment id]\r\n" + 
									"chan switchh[NCB];\r\n" + 
									"chan reqLock[NCB][NT][NSEG][NSEG]; //lock sb for t between two segments\r\n" + 
									"chan OK[NT];\r\n" + 
									"chan notOK[NT];\r\n" + 
									"chan pass[NCB][NT]; //t passing s connected to sb\r\n" + 
									"chan OKsb[NCB];\n";
			
			//Generate file
			PrintWriter writer;
			try {
				writer = new PrintWriter("test.txt", "UTF-8");
				writer.println(sizesString + typesString + cbsString + routesString + cbDetailsString + switchesString + channelsString);
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
	}

	private String switchID(ControlBox cb) {
		String switchesString = "";
		if(cb instanceof SwitchBox) {
			switchesString += id;
			id++;
		} else {
			switchesString += "-1";
		}
		return switchesString;
	}

	private String cbsDetails(ControlBox cb) {
		String cbsDetails = "{";
		if (cb instanceof SwitchBox) {
			SwitchBox sb = (SwitchBox) cb;
			cbsDetails += segIDs.get(sb.getStem())+", "+segIDs.get(sb.getPlus())+", "+segIDs.get(sb.getMinus())+"}";
		} else {
			if (cb.getIngoing().size() == 2) {
				cbsDetails += segIDs.get(cb.getIngoing().get(0))+", "+segIDs.get(cb.getIngoing().get(1))+", "+"-1}";	
			} else if (cb.getOutgoing().size() == 2) {
				cbsDetails += segIDs.get(cb.getOutgoing().get(0))+", "+segIDs.get(cb.getOutgoing().get(1))+", "+"-1}";	
			} else if (cb.getOutgoing().size()+cb.getIngoing().size() == 2) {
				cbsDetails += segIDs.get(cb.getIngoing().get(0))+", "+segIDs.get(cb.getOutgoing().get(0))+", "+"-1}";	
			} else if(cb.getIngoing().size() == 1) {
				cbsDetails += segIDs.get(cb.getIngoing().get(0))+", -1, -1}";
			} else {
				cbsDetails += segIDs.get(cb.getOutgoing().get(0))+", -1, -1}";
			}
		}
		return cbsDetails;
	}

	private String trainRoute(Train t) {
		String routesString = "{";
		for(int j = 0; j < t.getRoute().size()-1; j++) {
			routesString += segIDs.get(t.getRoute().get(j))+",";
		}
		routesString += segIDs.get(t.getRoute().get(t.getRoute().size()-1))+"}";
		return routesString;
	}
	

	private String trainCBs(Train t) {
		String cbsString = "{";
		for(int j = 0; j < t.getRoute().size()-1; j+=2) {
			ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(j), t.getRoute().get(j+1));
			cbsString += cbIDs.get(cbs[0])+", "+cbIDs.get(cbs[1])+", ";
		}
		ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(t.getRoute().size()-2), t.getRoute().get(t.getRoute().size()-1));
		cbsString += cbIDs.get(cbs[2])+"}";	
		return cbsString;
	}

	private void initialize(Network n) {
		int i = 0;

		cbIDs = new HashMap<>();
		for(ControlBox cb : n.getControlBoxes()) {
			cbIDs.put(cb, i);
			i++;
		}
		
		segIDs = new HashMap<>();
		i = 0;
		for(Segment s : n.getSegments()) {
			segIDs.put(s, i);
			i++;
		}
	}


	private ControlBox[] getSegmentBoxes(Segment s, Segment s2) {
		ControlBox[] cbs = new ControlBox[3];
		if(s.getStart() == s2.getStart()) {
			cbs[0] = s.getEnd();
			cbs[1] = s.getStart();
			cbs[2] = s2.getEnd();
		} else if (s.getStart() == s2.getEnd()) {
			cbs[0] = s.getEnd();
			cbs[1] = s.getStart();
			cbs[2] = s2.getStart();
		} else if (s.getEnd() == s2.getStart()) {
			cbs[0] = s.getStart();
			cbs[1] = s.getEnd();
			cbs[2] = s2.getEnd();
		} else {
			cbs[0] = s.getStart();
			cbs[1] = s.getEnd();
			cbs[2] = s2.getStart();
		}
		return cbs;
	}
}
