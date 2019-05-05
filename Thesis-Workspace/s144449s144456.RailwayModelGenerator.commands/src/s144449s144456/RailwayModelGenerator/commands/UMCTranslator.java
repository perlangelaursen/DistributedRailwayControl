package s144449s144456.RailwayModelGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import network.*;

public class UMCTranslator extends Translator {

	@Override
	protected String generateCode(Network n) {
		if(n != null) {
			String classesString = generateTrainClass(n.getReserveLimit(), n.getLockLimit())+getCBClass()+getPointClass();
      
			//Objects
			String trainsString = generateTrainObjects(n);
			String cbsString = generateCBObjects(n);
			String pointsString = generatePointObjects(n); 
			String objectString = "\n"+"Objects" + "\n" + trainsString + "\n" + cbsString + "\n" + pointsString;
			
			//Create abstractions
			String abstractionsString = generateAbstractions(n);
			
			return classesString + "\n" + objectString + "\n" + abstractionsString;
		}
		return "An error occurred";
	}

	private String generateAbstractions(Network n) {
		String abs = "Abstractions{\n  State: ";
		for(Train t : n.getTrains()) {
			abs += "inState(t"+trainIDs.get(t)+".Arrived) and ";
		}
		abs = abs.substring(0, abs.length() - 5)+" -> All_Trains_Arrive\n";

		String prop1 = "EF All_Trains_Arrive\n";
		String prop2 = "AG(";
		String prop3 = "AG(";
		String prop4 = "AG(";
		String prop5 = "AG(";
		String prop6 = "AG(";
		String prop7 = "AG("; 
		String prop8 = "AG("; 
		String prop9 = "~EF{";
		String prop10 = "AG(";
		String prop11 = "AG(";
		String prop12 = "AG(";
		String prop13 = "AG(";
		String prop14 = "AG(";
		String prop15 = "~EF{";
		String prop16 = "~EF(";
		String prop17 = "AG(";
		String prop18 = "~EF{";
		String prop19 = "~EF{";
		String prop20 = "~EF{";
		String prop21 = "AG(";

		abs += "  Action: $t:$cb.pass -> passing($t,$cb)\n";
		abs += "  Action: $t:$cb.reqLock($t,$s1,$s2) -> reqLocking($t,$cb,$s1,$s2)\n";
		abs += "  Action: $t:*.reqLock($t,$s1,$s2) -> reqLockingS($t,$s1,$s2)\n";
		abs += "  Action: $t:$cb.reqSeg($t,*) -> reqSegAt($t,$cb)\n";
		abs += "  Action: $t:*.reqSeg($t,$s) -> reqSegS($t,$s)\n";
		abs += "  Action: $cb:*.switchPoint -> switching($cb)\n";
		
		for(Train t : n.getTrains()) {
			String tid = "t"+trainIDs.get(t);
			abs += "  State: inState("+tid+".DoubleSegment)\n" + 
					"    and "+tid+".curSeg = $s1\n" + 
					"    and "+tid+".headSeg = $s2 -> doublePos("+tid+",$s1,$s2)\n";
			abs += "  State: "+tid+".locks > "+tid+".lockLimit -> lockLimitExceeded("+tid+")\n";
			
			for(Train t2: n.getTrains()) {
				if(t != t2) {
					String tid2 = "t"+trainIDs.get(t2);
					abs += "  State: "+tid+".curSeg = "+tid2+".curSeg -> ccCol("+tid+","+tid2+")\n";
					abs += "  State: inState("+tid+".DoubleSegment) \n" + 
							"    and "+tid+".headSeg = "+tid2+".curSeg -> hcCol("+tid+","+tid2+")\n";
					abs += "  State: inState("+tid+".DoubleSegment) \n" + 
							"    and inState("+tid2+".DoubleSegment) \n" + 
							"    and "+tid+".headSeg = "+tid2+".headSeg -> hhCol("+tid+","+tid2+")\n";
					prop2 += "(~ccCol("+tid+","+tid2+") & ~hcCol("+tid+","+tid2+") & ~hhCol("+tid+","+tid2+")) & ";
				}
			}
			for(int i = 0; i < t.getBoxRoute().size(); i++) {
				ControlBox cb = t.getBoxRoute().get(i);
				String cbid = "cb"+cbIDs.get(cb);
				if(i > 0 && i < t.getRoute().size()) {
					int s1 = segIDs.get(t.getRoute().get(i-1));
					int s2 = segIDs.get(t.getRoute().get(i));
					
					//If a train is passing a CB, the segments it moves on are connected
					//Note: Train only moves on reserved segments + Train only reserves segments on its route
					prop3 += "([passing("+tid+", "+cbid+")] (doublePos("+tid+","+s1+","+s2+") & connects("+cbid+","+s1+","+s2+"))) & ";
					
					//A CB only returns acknowledgement for a switch/lock request if the requested segments are its stem and one of its other segments
					//Note: Weaker - canConnect regardless of whether OK is returned or not (possible in UMC)
					//Note: Train only requests switching/locking of CBs in route
					prop13 += "([reqLocking("+tid+","+cbid+","+s1+","+s2+")] canConnect("+cbid+","+s1+","+s2+")) & ";				
					
					
					//A TCC only requests locks for connections that it has reserved
					//Note: Train only requests switching/locking for segments in route
					//Note: Train only requests switching/locking of CBs in route
					prop14 += "([reqLocking("+tid+","+cbid+","+s1+","+s2+")] -> (reserved("+tid+","+s1+","+cbid+") & reserved("+tid+","+s2+","+cbid+"))) & ";

					String cbid2 = "cb"+cbIDs.get(t.getBoxRoute().get(i+1));
					//Reservation consistency: All segment reservations obtained by a TCC are also saved in the state space of the relevant CBs
					//Note: Train only reserves segments in route at CBs in route
					prop7 += "(reserved("+tid+","+s1+","+cbid+") -> reservedBy("+cbid+","+s1+","+tid+")) & (reserved("+tid+","+s1+","+cbid2+") -> reservedBy("+cbid2+","+s1+","+tid+")) & ";
				}
				
				if(cb instanceof SwitchBox) {
					String pid = "p"+pointIDs.get(cb);
					//If a train is passing a CB, the associated point is not in the middle of switching 
					prop4 += "([passing("+tid+", "+cbid+")] ~inSwitching("+pid+")) & ";
					
					int stem = segIDs.get(((SwitchBox) cb).getStem());
					int plus = segIDs.get(((SwitchBox) cb).getPlus());
					int minus = segIDs.get(((SwitchBox) cb).getMinus());
					abs += "  State: "+tid+".curSeg = "+stem+" and "+tid+".headSeg = "+plus+" -> inCrit("+cbid+","+tid+")\n";
					abs += "  State: "+tid+".curSeg = "+plus+" and "+tid+".headSeg = "+stem+" -> inCrit("+cbid+","+tid+")\n";
					abs += "  State: "+tid+".curSeg = "+stem+" and "+tid+".headSeg = "+minus+" -> inCrit("+cbid+","+tid+")\n";
					abs += "  State: "+tid+".curSeg = "+minus+" and "+tid+".headSeg = "+stem+" -> inCrit("+cbid+","+tid+")\n";
				}
				
				abs += "  State: "+tid+".index < "+i+"\n" + 
						"    and "+tid+".lockIndex > "+i+"\n" + 
						"    and "+tid+".requiresLock["+i+"] = true\n" + 
						"    and "+tid+".boxes["+i+"] = $cb -> locked("+tid+",$cb)\n"; 
				
				//Lock consistency: A TCC's obtained locks are reflected in the relevant CBs
				//Note: Trains only lock CBs in route
				prop6 += "(locked("+tid+","+cbid+") -> lockedBy("+cbid+","+tid+")) & ";
				
				abs += "  State: "+tid+".index <= "+i+"\n" + 
						"    and "+tid+".resSegIndex > "+i+"\n" + 
						"    and "+tid+".segments["+i+"] = $s\n" + 
						"    and "+tid+".boxes["+(i+1)+"] = $cb -> reserved("+tid+",$s,$cb)\n"; 
				abs += "  State: "+tid+".index < "+i+"\n" + 
						"    and "+tid+".resCBIndex > "+i+"\n" + 
						"    and "+tid+".segments["+i+"] = $s\n" + 
						"    and "+tid+".boxes["+i+"] = $cb -> reserved("+tid+",$s,$cb)\n";			
				
				if(i < t.getRoute().size()-1) {
					int s1 = segIDs.get(t.getRoute().get(i));
					int s2 = segIDs.get(t.getRoute().get(i+1));
					String cbid2 = "cb"+cbIDs.get(t.getBoxRoute().get(i+1));
					String cbid3 = "cb"+cbIDs.get(t.getBoxRoute().get(i+2));
					
					//A train only enters a segment that is has the full reservation of
					//Note: Train only moves on reserved segments + Train only reserves segments on its route
					prop8 += "(doublePos("+tid+","+s1+","+s2+") -> (reserved("+tid+","+s2+","+cbid2+") & reserved("+tid+","+s2+","+cbid3+"))) & ";
					
					prop21 += "(doublePos("+tid+","+s1+","+s2+") -> connects("+cbid2+","+s1+","+s2+")) & ";
					
					//A train only passes a switch box if it has been locked for the train
					prop10 += "((doublePos("+tid+","+s1+","+s2+") & isSwitchBox("+cbid2+")) -> locked("+tid+","+cbid2+")) & ";
				}

				//A train never passes the last control box in its route
				if(i == t.getRoute().size()) {
					prop9 += "passing("+tid+","+cbid+") & ";	
				}
			}
			
			for(ControlBox cb : n.getControlBoxes()) {
				String cbid = "cb"+cbIDs.get(cb);
				if(!t.getBoxRoute().contains(cb) || cb == t.getBoxRoute().getFirst()) {
					//A TCC only requests locks at switch boxes on its route
					prop15 += "reqLock("+tid+","+cbid+") | ";
					//A TCC only reserves at control boxes on its route
					prop18 += "reqSegAt("+tid+","+cbid+") | ";
				}
				if(cb instanceof SwitchBox) {
					String pid = "p"+pointIDs.get(cb);
					abs += "  State: inState("+pid+".Switching) -> inSwitching("+pid+")\n";
				}
			}
			
			//A TCC never has more locks than allowed
			prop16 += "lockLimitExceeded("+tid+") | ";
			
			for(Segment s : n.getSegments()) {
				String sid = ""+segIDs.get(s);
				if(!t.getRoute().contains(s)) {
					//A TCC only reserves segments on its route
					prop19 += "reqSegS("+tid+","+sid+") | ";
					
					for(Segment s2 : n.getSegments()) {
						String sid2 = ""+segIDs.get(s2);
						//A TCC only requests switch/lock for connections of segments that are adjacent in its route
						prop20 += "reqLockingS("+tid+","+sid+","+sid2+") | ";
					}
				} else {
					int i = t.getRoute().indexOf(s);
					for(Segment s2 : n.getSegments()) {
						if(i < t.getRoute().size()-1 && t.getRoute().get(i+1) != s2) {
							String sid2 = ""+segIDs.get(s2);
							prop20 += "reqLockingS("+tid+","+sid+","+sid2+") | ";
						}
					}
				}
			}
		}

		
		for(ControlBox cb : n.getControlBoxes()) {
			String cbid = "cb"+cbIDs.get(cb);
			abs += "  State: "+cbid+".segments[0] = $s1 and "+cbid+".connected = $s2 -> connects("+cbid+",$s1,$s2)\n";
			abs += "  State: "+cbid+".segments[0] = $s1 and "+cbid+".connected = $s2 -> connects("+cbid+",$s2,$s1)\n";
			abs += "  State: "+cbid+".point /= null -> isSwitchBox("+cbid+")\n";
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				String pid = "p"+pointIDs.get(cb);
				int s1 = segIDs.get(sb.getStem());
				int s2 = segIDs.get(sb.getPlus());
				int s3 = segIDs.get(sb.getMinus());
				abs += "  State: "+pid+".inPlus = true -> inPlus("+pid+")\n";
				//Point consistency: A CB's connected information is consistent with its Point's position
				prop5 += "((connects("+cbid+","+s1+","+s2+") & ~inSwitching("+cbid+")) -> inPlus("+pid+")) & ((connects("+cbid+","+s1+","+s3+") & ~inSwitching("+cbid+")) -> ~inPlus("+pid+")) & ";
			}
			abs += "  State: "+cbid+".lockedBy = $t -> lockedBy("+cbid+",$t)\n";
			abs += "  State: inState("+cbid+".Switching) -> inSwitching("+cbid+")\n";
			abs += "  State: "+cbid+".segments[0] = $s1\n" + 
					"    and "+cbid+".segments[1] = $s2 -> canConnect("+cbid+",$s1,$s2)\n";
			abs += "  State: "+cbid+".segments[0] = $s1\n" + 
					"    and "+cbid+".segments[1] = $s2 -> canConnect("+cbid+",$s2,$s1)\n";
			if(cb instanceof SwitchBox) {
				abs += "  State: "+cbid+".segments[0] = $s1\n" + 
						"    and "+cbid+".segments[2] = $s2 -> canConnect("+cbid+",$s1,$s2)\n";
				abs += "  State: "+cbid+".segments[0] = $s1\n" + 
						"    and "+cbid+".segments[2] = $s2 -> canConnect("+cbid+",$s2,$s1)\n";
			}
			
			for(int i = 0; i <= 2; i++) {
				abs += "  State: "+cbid+".res["+i+"] /= null\n" +
						"    and "+cbid+".segments["+i+"] = $s\n" + 
						"    and "+cbid+".res["+i+"] = $t -> reservedBy("+cbid+",$s,$t)\n";
			}

			abs += "  State: inState("+cbid+".Switched) -> inSwitched("+cbid+")\n";
			
			//A lock is only ever given if it is available
			prop11 += "(inSwitched("+cbid+") -> lockedBy("+cbid+",null)) & ";
			
			//A control box only switches and locks its point if no train is in its critical section
			prop12 += "([switching("+cbid+")] (";
			for(Train t : n.getTrains()) {
				String tid = "t"+trainIDs.get(t);
				prop12 += "~inCrit("+cbid+","+tid+") & ";
			}
			prop12 = prop12.substring(0, prop12.length() - 3)+")) & ";
			
			abs += "  State: inState("+cbid+".SegmentChecked)\n" + 
					"    and "+cbid+".result >= 0 \n" + 
					"    and "+cbid+".result = $i\n" + 
					"    and "+cbid+".segments[$i] = $s -> resOK("+cbid+",$s)\n";
			for(int i = 0; i <= 2; i++) {
				abs += "  State: "+cbid+".segments["+i+"] /= null\n" + 
						"    and "+cbid+".segments["+i+"] = $s\n" + 
						"    and "+cbid+".res["+i+"] = null -> segFree("+cbid+",$s)\n";
			}
			for(Segment s : cb.getSegments()) {
				int sid = segIDs.get(s);
				//A reservation is only ever given if it is available, a control box only returns acknowledgement for reservations involving segments that it is associated with
				prop17 += "(resOK("+cbid+","+sid+") -> segFree("+cbid+","+sid+")) & ";
			}
		}
		prop2 = prop2.substring(0, prop2.length() - 3)+")\n";
		prop3 = prop3.substring(0, prop3.length() - 3)+")\n";
		prop4 = prop4.substring(0, prop4.length() - 3)+")\n";
		prop5 = prop5.substring(0, prop5.length() - 3)+")\n";		
		prop6 = prop6.substring(0, prop6.length() - 3)+")\n";
		prop7 = prop7.substring(0, prop7.length() - 3)+")\n";
		prop8 = prop8.substring(0, prop8.length() - 3)+")\n";
		prop9 = prop9.substring(0, prop9.length() - 3)+"}\n";
		prop10 = prop10.substring(0, prop10.length() - 3)+")\n";
		prop11 = prop11.substring(0, prop11.length() - 3)+")\n";
		prop12 = prop12.substring(0, prop12.length() - 3)+")\n";
		prop13 = prop13.substring(0, prop13.length() - 3)+")\n";
		prop14 = prop14.substring(0, prop14.length() - 3)+")\n";
		prop15 = prop15.substring(0, prop15.length() - 3)+"}\n";
		prop16 = prop16.substring(0, prop16.length() - 3)+")\n";
		prop17 = prop17.substring(0, prop17.length() - 3)+")\n";
		if(prop18.length() <= 4) {
			prop18 = "All boxes get reservation requests from all trains\n";
		} else {
			prop18 = prop18.substring(0, prop18.length() - 3)+"}\n";
		}
		prop19 = prop19.substring(0, prop19.length() - 3)+"}\n";
		prop20 = prop20.substring(0, prop20.length() - 3)+"}\n";
		prop21 = prop21.substring(0, prop21.length() - 3)+")\n";
		
		String props = "";
		props += "\n//NO COLLISION\n";
		props += prop2;
		
		props += "\n//NO DERAILMENT\n";
		props += "//If a train is in a critical section, the point in that section is not in the middle of switching\n"+prop4;
		props += "//If a train is in a critical section, then the segments that it is moving on are connected\n"+prop3;

		props += "\n//OPERATION REQUIREMENTS: RESERVE\n";
//		props += "//A train never has more reservations than the reservation limit\n";
		props += "//A reservation is only requested if the requested segment is a part of the requesting train's route\n"+prop19;
		props += "//A reservation is only requested if the control box that a train contacts is a part of the train's route\n"+prop18;
		props += "//A reservation is only successful if the requested segment is associated with the control box that receives the request and if it is not already reserved\n"+prop17;
		
		props += "\n//OPERATION REQUIREMENTS: LOCK\n";
		props += "//A train never has more locks than the lock limit\n"+prop16;
		props += "//A lock is only requested if the involved switch box is in the route of the requesting train\n"+prop15;
		props += "//A lock is only requested if the requesting train has the reservation for the two segments at the switch box\n"+prop14;
		props += "//A lock is only successful if the point involved in the request was unlocked prior to the request\n"+prop11;
		props += "//A switch is only requested if the requested connection is of segments that are adjacent in the train's route\n"+prop20;
		props += "//A switch is only successful if the requested conenction is of the stem segment and plus or minus segment of the switch box\n"+prop13;
		props += "//A control box only switches and locks its point if no train is in its critical section\n"+prop12;

		props += "\n//OPERATION REQUIREMENTS: PASS\n";
		props += "//A train only passes a switch box if it has been locked for the train\n"+prop10;
		props += "//A train never passes the last control box on its route\n"+prop9;
		props += "//A train only enters a segment that it has the full reservation of\n"+prop8;

		props += "\n//CONSISTENCY\n";
		props += "//Reservation consistency: The reservations saved in the state space of a Train are also saved in the state spaces of the involved CBs\n"+prop7;
		props += "//Lock consistency: The locks saved in the state space of a Train are also saved in the state spaces of the involved CBs\n"+prop6;		
//		props += "Lock consistency: The number of saved locks in the state space of a Train is the same number of locks that it believes that is has \n";
//		props += "Network array consistency: The position of a point in the network data is consistent with the actual position of the point\n";
		props += "//Point consistency: A CB's connected information is consistent with its Point's position\n"+prop5;
		props += "//Position consistency: The train position saved in a TCC is consistent with the train's actual position\n"+prop21;

		props += "//LIVENESS\n";
		props += prop1;
		printProperties(n.getName(), props);
		
		abs += "}";
		return abs;
	}
	
	private void printProperties(String name, String s) {
		if(s != null) {
			System.out.println(s);
			PrintWriter printWriter;
			try {
				printWriter = new PrintWriter(name+"_"+getFileNameDetails()+"_properties"+".xml", "UTF-8");
				printWriter.println(s);
				printWriter.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private String generatePointObjects(Network n) {
		String ps = "";
		for(ControlBox cb : n.getControlBoxes()) {
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				ps += "p"+pointIDs.get(sb)+":Point(inPlus => ";
				ps += (sb.getConnected() == PointSetting.PLUS) ? "true" : "false";
				
				ps += ", cb => cb"+cbIDs.get(sb)+");\n";
			}
		}
		return ps;
	}

	private String generateCBObjects(Network n) {
		String cbs = "";
		for(ControlBox cb : n.getControlBoxes()) {
			cbs += "cb"+cbIDs.get(cb)+":CB(segments => [";
			//segments
			Segment[] segments = controlBoxSegments.get(cb);
			cbs += segIDs.get(segments[0])+",";
			cbs += (segments[1] != null) ? segIDs.get(segments[1]) : -1;
			cbs += ",";
			cbs += (segments[2] != null) ? segIDs.get(segments[2]) : -1;
			cbs += "]";
					
			//res
			String[] res = {null,null,null};
			for(int i = 0; i < 3; i++) {
				for(Train t : n.getTrains()) {
					if(segIDs.get(t.getRoute().get(0)) == segIDs.get(segments[i]) && t.getBoxRoute().get(1) == cb) {					
						res[i] = "t"+trainIDs.get(t);
						break;
					}
				}	
			}
			cbs += ", res => ["+res[0]+","+res[1]+","+res[2]+"]";
			cbs += ", connected => ";
			//point
			if(!(cb instanceof SwitchBox)) {
				String sid = ""+segIDs.get(controlBoxSegments.get(cb)[1]);
				cbs += sid;
			} else {
				SwitchBox sb = (SwitchBox) cb;
				if(sb.getConnected() == PointSetting.PLUS) {
					cbs += segIDs.get(segments[1]);
				} else if (sb.getConnected() == PointSetting.MINUS) {
					cbs += segIDs.get(segments[2]);
				}
				cbs += ", point => p"+pointIDs.get(cb);
			}
			cbs += ");\n";
		}
		return cbs;
	}

	private String generateTrainObjects(Network n) {
		String ts = "";
		for(Train t : n.getTrains()) {
			//Segments
			ts += "t"+trainIDs.get(t)+":Train(segments=>[";
			for(int j = 0; j < t.getRoute().size()-1; j++) {
				ts += segIDs.get(t.getRoute().get(j))+",";
			}
			ts += segIDs.get(t.getRoute().get(t.getRoute().size()-1))+"], ";
			ts += "curSeg => "+segIDs.get(t.getRoute().get(0))+", ";
			
			//Control boxes
			String boxIDs = "boxIDs => [";
			String requiresLock = "requiresLock => [";
			ts += "boxes => [";
			for(int j = 0; j < t.getBoxRoute().size(); j++) {
				ControlBox cb = t.getBoxRoute().get(j);
				int cbID = cbIDs.get(cb);
				ts += "cb"+cbID+",";
				boxIDs += cbID+",";
				requiresLock += (cb instanceof SwitchBox)? "true," : "false,";
			}

			requiresLock = requiresLock.substring(0, requiresLock.length() - 1)+"]";
			boxIDs = boxIDs.substring(0,boxIDs.length()-1)+"]";
			ts = ts.substring(0, ts.length() - 1)+"], "+boxIDs+", "+requiresLock;
			
			if(t.getRoute().size() > 1) {
				ts += ", "+"lockIndex => ";
				int lockIndex = 0;
				for(int j = 1; j < t.getBoxRoute().size(); j++) {
					if(t.getBoxRoute().get(j) instanceof SwitchBox) {
						lockIndex = j;
						break;
					}
				}
				ts += lockIndex;
			}
			
			ts += ");\n";
		}
		return ts;
	}

	private String getPointClass() {
		return "class Point is\n" + 
				"Signals\n" + 
				"    switchPoint\n" + 
				"Vars\n" + 
				"    cb:CB;\n" + 
				"    inPlus:bool\n" + 
				"Transitions\n" + 
				"    Still -> Switching {switchPoint}\n" + 
				"    Switching -> Still { - /\n" + 
				"        inPlus = !inPlus;\n" + 
				"        cb.OKp;}        \n" + 
				"end Point;\n";
	}

	private String getCBClass() {
		return "Class CB is\n" + 
				"Signals\n" + 
				"    reqSeg(it:Train, s:int),\n" + 
				"    reqLock(it:Train, s1:int, s2:int),\n" + 
				"    pass,\n" + 
				"    passed,\n" + 
				"    OKp\n" + 
				"Vars\n" + 
				"    segments;\n" + 
				"    connected;\n" + 
				"    point:Point;\n" + 
				"    \n" + 
				"    res:Train[3];\n" + 
				"    result:int := -1;\n" + 
				"    t:Train := null;\n" + 
				"    lockedBy:Train;\n" + 
				"    \n" + 
				"    ERROR:int := 0;\n" + 
				"    NOSWITCH:int := 1;\n" + 
				"    DOSWITCH:int := 2;\n" + 
				"Transitions\n" + 
				"    Idle -> SegmentChecked {\n" + 
				"        reqSeg(it,s) /\n" + 
				"        \n" + 
				"        //checkSegment\n" + 
				"        for i in 0..2 {\n" + 
				"            if (segments[i] == s && res[i] == null) then {\n" + 
				"                result = i;\n" + 
				"            }\n" + 
				"        };\n" + 
				"        t = it;\n" + 
				"    }\n" + 
				"        \n" + 
				"    SegmentChecked -> Idle {\n" + 
				"        - [result > -1] /\n" + 
				"                \n" + 
				"        //updateResInfo\n" + 
				"        res[result] = t;\n" + 
				"        //resetVariables\n" + 
				"        result = -1;\n" + 
				"        \n" + 
				"        t.OK;\n" + 
				"        t = null;\n" + 
				"    }\n" + 
				"    \n" + 
				"    SegmentChecked -> Idle {\n" + 
				"        - [result == -1] /\n" + 
				"        \n" + 
				"        t.notOK;\n" + 
				"        \n" + 
				"        //resetVariables\n" + 
				"        t = null;\n" + 
				"        result = -1;\n" + 
				"    }\n" + 
				"    \n" + 
				"    Idle -> LockChecked {\n" + 
				"        reqLock(it,s1,s2) /\n" + 
				"        \n" + 
				"        //checkLock\n" + 
				"        result = ERROR;\n" + 
				"        if(lockedBy == null) then {\n" + 
				"            if ((segments[0] == s1 && (segments[1] == s2 || segments[2] == s2)) ||\n" + 
				"                (segments[0] == s2 && (segments[1] == s1 || segments[2] == s1))) then {\n" + 
				"                if((s1 == segments[0] && s2 == connected) || (s2 == segments[0] && s1 == connected)) then {\n" + 
				"                    result = NOSWITCH;\n" + 
				"                } else {\n" + 
				"                    result = DOSWITCH;\n" + 
				"                }\n" + 
				"            }\n" + 
				"        };\n" + 
				"        t = it;\n" + 
				"    }\n" + 
				"    \n" + 
				"    LockChecked -> Switching {\n" + 
				"        - [result == DOSWITCH] /\n" + 
				"        \n" + 
				"        point.switchPoint;\n" + 
				"    }\n" + 
				"    \n" + 
				"    LockChecked -> Idle {\n" + 
				"        - [result == ERROR] /\n" + 
				"        \n" + 
				"        t.notOK;\n" + 
				"        //resetVariables\n" + 
				"        t = null;\n" + 
				"        result = -1\n" + 
				"    }\n" + 
				"    \n" + 
				"    LockChecked -> Switched {- [result == NOSWITCH]}\n" + 
				"    \n" + 
				"    Switching -> Switched {\n" + 
				"        OKp /\n" + 
				"        //updateConnected\n" + 
				"        if(connected == segments[1]) {\n" + 
				"            connected = segments[2];\n" + 
				"        } else {\n" + 
				"            connected = segments[1];\n" + 
				"        }\n" + 
				"    }\n" + 
				"    \n" + 
				"    Switched -> Idle{\n" + 
				"        /\n" + 
				"        //updateLockInfo\n" + 
				"        lockedBy = t;\n" + 
				"        //resetVariables\n" + 
				"        result = -1;\n" + 
				"        \n" + 
				"        t.OK;\n" + 
				"        \n" + 
				"        //resetVariables\n" + 
				"        t = null;\n" + 
				"    }\n" + 
				"    \n" + 
				"    Idle -> Passing {pass}\n" + 
				"    Passing -> Idle {\n" + 
				"        passed /\n" + 
				"        \n" + 
				"        //clear\n" + 
				"        lockedBy = null;\n" + 
				"        res[0] = null;\n" + 
				"        if(connected == segments[1]){\n" + 
				"            res[1] = null;\n" + 
				"        } else {\n" + 
				"            res[2] = null;\n" + 
				"        }\n" + 
				"    }\n" + 
				"end CB;\n";
	}

	private String generateTrainClass(int resLimit, int lockLimit) {
		return "Class Train is\n" + 
				"Signals\n" + 
				"    OK, notOK\n" + 
				"Vars\n" + 
				"    segments;\n" + 
				"    boxes;\n" + 
				"    boxIDs;\n" + 
				"    \n" + 
				"    curSeg:int;\n" + 
				"\n" + 
				"    requiresLock;\n" + 
				"\n" + 
				"    lockIndex:int = 1;\n" + 
				"    index:int = 0;\n" + 
				"\n" + 
				"    resBit:int = 0;\n" + 
				"    resCBIndex:int = 1;\n" + 
				"    resSegIndex:int = 1;\n" + 
				"\n" + 
				"    headSeg:int = -1;\n" + 
				"    locks:int = 0;\n" + 
				"\n" + 
				"    //TEMP\n" + 
				"    resLimit = "+resLimit+";\n" + 
				"    lockLimit = "+lockLimit+";\n" + 
				"Transitions\n" + 
				"    SingleSegment -> Arrived {[index == segments.length-1]}\n" + 
				"    Arrived -> Arrived\n" + 
				"    \n" + 
				"    SingleSegment -> DoubleSegment {\n" + 
				"        [resSegIndex > index + 1 && lockIndex > index + 1 && index + 1 < segments.length] / \n" + 
				"         \n" + 
				"        //updateHeadInfo\n" + 
				"        boxes[index+1].pass;\n" + 
				"        headSeg = segments[index+1];    \n" + 
				"    }\n" + 
				"    \n" + 
				"    DoubleSegment -> SingleSegment {/ \n" + 
				"        //updateLocationInfo\n" + 
				"        curSeg = headSeg;\n" + 
				"        headSeg = -1;\n" + 
				"        if(requiresLock[index +1]){\n" + 
				"            locks--;\n" + 
				"        };\n" + 
				"        index++;\n" + 
				"        \n" + 
				"        boxes[index].passed; \n" + 
				"    }\n" + 
				"    \n" + 
				"    SingleSegment -> Reserving {\n" + 
				"        - [resSegIndex < segments.length && resSegIndex - 1 - index < resLimit] / \n" + 
				"        \n" + 
				"        boxes[resCBIndex].reqSeg(this, segments[resSegIndex])\n" + 
				"    }\n" + 
				"    \n" + 
				"    Reserving -> SingleSegment {\n" + 
				"        OK / \n" + 
				"        \n" + 
				"        //updateResInfo\n" + 
				"        if (resBit == 0) {\n" + 
				"            resBit = 1;\n" + 
				"            resCBIndex++;\n" + 
				"        } else {\n" + 
				"            resBit = 0;\n" + 
				"            resSegIndex++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"        \n" + 
				"    Reserving -> SingleSegment {notOK}\n" + 
				"    \n" + 
				"    SingleSegment -> Locking {\n" + 
				"        [lockIndex < segments.length && locks < lockLimit && ((resBit == 0 && resSegIndex > lockIndex) || (resBit == 1 && resSegIndex >= lockIndex))] / \n" + 
				"        \n" + 
				"        boxes[lockIndex].reqLock(this, segments[lockIndex-1],segments[lockIndex])\n" + 
				"    }\n" + 
				"        \n" + 
				"    Locking -> SingleSegment {\n" + 
				"        OK / \n" + 
				"        \n" + 
				"        //updateLockInfo\n" + 
				"        locks++;\n" + 
				"        lockIndex++;\n" + 
				"        //updateLockIndex\n" + 
				"        for i in lockIndex..segments.length {\n" + 
				"            if(!requiresLock[i]){\n" + 
				"                lockIndex++;\n" + 
				"            } else {\n" + 
				"                i = segments.length;\n" + 
				"            }\n" + 
				"        }\n" + 
				"    }\n" + 
				"    Locking -> SingleSegment {notOK}\n" + 
				"end Train;\n";
	}

	@Override
	protected String getFileNameDetails() {
		return "UMC";
	}

}

