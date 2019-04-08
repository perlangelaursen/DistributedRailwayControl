package s144449s144456.RailwayNetworkConfigurationGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import network.*;

public class UMCTranslator extends Translator {

	@Override
	protected String generateCode(Network n) {
		if(n != null) {
			String classes = createTrainClass(n.getReserveLimit(), n.getLockLimit())+createCBClass()+createPointClass();
			
			String objectString = "\r\n"+"Objects";
			
			//Create trains
			String trainsString = getTrainObjectsString(n);
			
			//Create control boxes
			String cbsString = getCBObjectsString(n);
			
			//Create points
			String pointsString = getPointObjectsString(n); 
			
			//Create abstractions
			String abstractionsString = getAbstractionsString(n);
			
			//Generate file
			PrintWriter writer;
			try {
				writer = new PrintWriter("RCS_UMC.txt", "UTF-8");
				writer.println(classes);
				writer.println(objectString + "\r\n" + trainsString + "\r\n" + cbsString + "\r\n" + pointsString + "\r\n" + abstractionsString);
//				System.out.println(trainsString + cbsString + pointsString + abstractionsString);
				writer.close();
				return "Model file successfully generated.";
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "An error occurred";
	}

	private String getAbstractionsString(Network n) {
		String abs = "Abstractions{\r\n  State: ";
		for(Train t : n.getTrains()) {
			abs += "inState(t"+trainIDs.get(t)+".Arrived) and ";
		}
		abs = abs.substring(0, abs.length() - 5)+" -> All_Trains_Arrive\r\n";

		String prop1 = "EF All_Trains_Arrive\r\n";
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

		abs += "  Action: $t:$cb.pass -> passing($t,$cb)\r\n";
		abs += "  Action: $t:$cb.reqLock($t,$s1,$s2) -> reqLocking($t,$cb,$s1,$s2)\r\n";
		abs += "  Action: $t:*.reqLock($t,$s1,$s2) -> reqLockingS($t,$s1,$s2)\r\n";
		abs += "  Action: $t:$cb.reqSeg($t,*) -> reqSegAt($t,$cb)\r\n";
		abs += "  Action: $t:*.reqSeg($t,$s) -> reqSegS($t,$s)\r\n";
		abs += "  Action: $cb:*.switchPoint -> switching($cb)\r\n";
		
		for(Train t : n.getTrains()) {
			String tid = "t"+trainIDs.get(t);
			abs += "  State: inState("+tid+".DoubleSegment)\r\n" + 
					"    and "+tid+".curSeg = $s1\r\n" + 
					"    and "+tid+".headSeg = $s2 -> doublePos("+tid+",$s1,$s2)\r\n";
			abs += "  State: "+tid+".locks > "+tid+".lockLimit -> lockLimitExceeded("+tid+")\r\n";
			
			for(Train t2: n.getTrains()) {
				if(t != t2) {
					String tid2 = "t"+trainIDs.get(t2);
					abs += "  State: "+tid+".curSeg = "+tid2+".curSeg -> ccCol("+tid+","+tid2+")\r\n";
					abs += "  State: inState("+tid+".DoubleSegment) \r\n" + 
							"    and "+tid+".headSeg = "+tid2+".curSeg -> hcCol("+tid+","+tid2+")\r\n";
					abs += "  State: inState("+tid+".DoubleSegment) \r\n" + 
							"    and inState("+tid2+".DoubleSegment) \r\n" + 
							"    and "+tid+".headSeg = "+tid2+".headSeg -> hhCol("+tid+","+tid2+")\r\n";
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
//					prop14 += "(locked("+tid+","+cbid+") -> (reserved("+tid+","+stem+","+cbid+") & (reserved("+tid+","+plus+","+cbid+") | reserved("+tid+","+minus+","+cbid+")))) & ";
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
					abs += "  State: "+tid+".curSeg = "+stem+" and "+tid+".headSeg = "+plus+" -> inCrit("+cbid+","+tid+")\r\n";
					abs += "  State: "+tid+".curSeg = "+plus+" and "+tid+".headSeg = "+stem+" -> inCrit("+cbid+","+tid+")\r\n";
					abs += "  State: "+tid+".curSeg = "+stem+" and "+tid+".headSeg = "+minus+" -> inCrit("+cbid+","+tid+")\r\n";
					abs += "  State: "+tid+".curSeg = "+minus+" and "+tid+".headSeg = "+stem+" -> inCrit("+cbid+","+tid+")\r\n";
				}
				
				abs += "  State: "+tid+".index < "+i+"\r\n" + 
						"    and "+tid+".lockIndex > "+i+"\r\n" + 
						"    and "+tid+".requiresLock["+i+"] = true\r\n" + 
						"    and "+tid+".boxes["+i+"] = $cb -> locked("+tid+",$cb)\r\n"; 
				
				//Lock consistency: A TCC's obtained locks are reflected in the relevant CBs
				//Note: Trains only lock CBs in route
				prop6 += "(locked("+tid+","+cbid+") -> lockedBy("+cbid+","+tid+")) & ";
				
				abs += "  State: "+tid+".index <= "+i+"\r\n" + 
						"    and "+tid+".resSegIndex > "+i+"\r\n" + 
						"    and "+tid+".segments["+i+"] = $s\r\n" + 
						"    and "+tid+".boxes["+(i+1)+"] = $cb -> reserved("+tid+",$s,$cb)\r\n"; 
				abs += "  State: "+tid+".index < "+i+"\r\n" + 
						"    and "+tid+".resCBIndex > "+i+"\r\n" + 
						"    and "+tid+".segments["+i+"] = $s\r\n" + 
						"    and "+tid+".boxes["+i+"] = $cb -> reserved("+tid+",$s,$cb)\r\n";			
				
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
					abs += "  State: inState("+pid+".Switching) -> inSwitching("+pid+")\r\n";
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
			abs += "  State: "+cbid+".segments[0] = $s1 and "+cbid+".connected = $s2 -> connects("+cbid+",$s1,$s2)\r\n";
			abs += "  State: "+cbid+".segments[0] = $s1 and "+cbid+".connected = $s2 -> connects("+cbid+",$s2,$s1)\r\n";
			abs += "  State: "+cbid+".point /= null -> isSwitchBox("+cbid+")\r\n";
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				String pid = "p"+pointIDs.get(cb);
				int s1 = segIDs.get(sb.getStem());
				int s2 = segIDs.get(sb.getPlus());
				int s3 = segIDs.get(sb.getMinus());
				abs += "  State: "+pid+".inPlus = true -> inPlus("+pid+")\r\n";
				//Point consistency: A CB's connected information is consistent with its Point's position
				prop5 += "((connects("+cbid+","+s1+","+s2+") & ~inSwitching("+cbid+")) -> inPlus("+pid+")) & ((connects("+cbid+","+s1+","+s3+") & ~inSwitching("+cbid+")) -> ~inPlus("+pid+")) & ";				
			}
			abs += "  State: "+cbid+".lockedBy = $t -> lockedBy("+cbid+",$t)\r\n";
			abs += "  State: inState("+cbid+".Switching) -> inSwitching("+cbid+")\r\n";
			abs += "  State: "+cbid+".segments[0] = $s1\r\n" + 
					"    and "+cbid+".segments[1] = $s2 -> canConnect("+cbid+",$s1,$s2)\r\n";
			abs += "  State: "+cbid+".segments[0] = $s1\r\n" + 
					"    and "+cbid+".segments[1] = $s2 -> canConnect("+cbid+",$s2,$s1)\r\n";
			if(cb instanceof SwitchBox) {
				abs += "  State: "+cbid+".segments[0] = $s1\r\n" + 
						"    and "+cbid+".segments[2] = $s2 -> canConnect("+cbid+",$s1,$s2)\r\n";
				abs += "  State: "+cbid+".segments[0] = $s1\r\n" + 
						"    and "+cbid+".segments[2] = $s2 -> canConnect("+cbid+",$s2,$s1)\r\n";
			}
			
			for(int i = 0; i <= 2; i++) {
				abs += "  State: "+cbid+".res["+i+"] /= null\r\n" +
						"    and "+cbid+".segments["+i+"] = $s\r\n" + 
						"    and "+cbid+".res["+i+"] = $t -> reservedBy("+cbid+",$s,$t)\r\n";
			}
			abs += "  State: inState("+cbid+".Switched) -> inSwitched("+cbid+")\r\n";
//			abs += "  State: "+cbid+".lockedBy = null -> unlocked("+cbid+")\r\n";
			
			//A lock is only ever given if it is available
			prop11 += "(inSwitched("+cbid+") -> lockedBy("+cbid+",null)) & ";
			
			//A control box only switches and locks its point if no train is in its critical section
//			prop12 += "((inSwitched("+cbid+") | inSwitching("+cbid+")) -> (";
			prop12 += "([switching("+cbid+")] (";
			for(Train t : n.getTrains()) {
				String tid = "t"+trainIDs.get(t);
				prop12 += "~inCrit("+cbid+","+tid+") & ";
			}
			prop12 = prop12.substring(0, prop12.length() - 3)+")) & ";
			
			abs += "  State: inState("+cbid+".SegmentChecked)\r\n" + 
					"    and "+cbid+".result >= 0 \r\n" + 
					"    and "+cbid+".result = $i\r\n" + 
					"    and "+cbid+".segments[$i] = $s -> resOK("+cbid+",$s)\r\n";
			for(int i = 0; i <= 2; i++) {
				abs += "  State: "+cbid+".segments["+i+"] /= null\r\n" + 
						"    and "+cbid+".segments["+i+"] = $s\r\n" + 
						"    and "+cbid+".res["+i+"] = null -> segFree("+cbid+",$s)\r\n";
			}
			for(Segment s : cb.getSegments()) {
				int sid = segIDs.get(s);
				//A reservation is only ever given if it is available, a control box only returns acknowledgement for reservations involving segments that it is associated with
				prop17 += "(resOK("+cbid+","+sid+") -> segFree("+cbid+","+sid+")) & ";
			}
		}
		prop2 = prop2.substring(0, prop2.length() - 3)+")\r\n";
		prop3 = prop3.substring(0, prop3.length() - 3)+")\r\n";
		prop4 = prop4.substring(0, prop4.length() - 3)+")\r\n";
		prop5 = prop5.substring(0, prop5.length() - 3)+")\r\n";		
		prop6 = prop6.substring(0, prop6.length() - 3)+")\r\n";
		prop7 = prop7.substring(0, prop7.length() - 3)+")\r\n";
		prop8 = prop8.substring(0, prop8.length() - 3)+")\r\n";
		prop9 = prop9.substring(0, prop9.length() - 3)+"}\r\n";
		prop10 = prop10.substring(0, prop10.length() - 3)+")\r\n";
		prop11 = prop11.substring(0, prop11.length() - 3)+")\r\n";
		prop12 = prop12.substring(0, prop12.length() - 3)+")\r\n";
		prop13 = prop13.substring(0, prop13.length() - 3)+")\r\n";
		prop14 = prop14.substring(0, prop14.length() - 3)+")\r\n";
		prop15 = prop15.substring(0, prop15.length() - 3)+"}\r\n";
		prop16 = prop16.substring(0, prop16.length() - 3)+")\r\n";
		prop17 = prop17.substring(0, prop17.length() - 3)+")\r\n";
		if(prop18.length() <= 4) {
			prop18 = "All boxes get reservation requests from all trains\r\n";
		} else {
			prop18 = prop18.substring(0, prop18.length() - 3)+"}\r\n";
		}
		prop19 = prop19.substring(0, prop19.length() - 3)+"}\r\n";
		prop20 = prop20.substring(0, prop20.length() - 3)+"}\r\n";
		prop21 = prop21.substring(0, prop21.length() - 3)+")\r\n";
		
		String props = "";
		props += "\r\n//NO COLLISION\r\n";
		props += prop2;
		
		props += "\r\n//NO DERAILMENT\r\n";
		props += "//If a train is in a critical section, the point in that section is not in the middle of switching\r\n"+prop4;
		props += "//If a train is in a critical section, then the segments that it is moving on are connected\r\n"+prop3;

		props += "\r\n//OPERATION REQUIREMENTS: RESERVE\r\n";
//		props += "//A train never has more reservations than the reservation limit\r\n";
		props += "//A reservation is only requested if the requested segment is a part of the requesting train's route\r\n"+prop19;
		props += "//A reservation is only requested if the control box that a train contacts is a part of the train's route\r\n"+prop18;
		props += "//A reservation is only successful if the requested segment is associated with the control box that receives the request and if it is not already reserved\r\n"+prop17;
		
		props += "\r\n//OPERATION REQUIREMENTS: LOCK\r\n";
		props += "//A train never has more locks than the lock limit\r\n"+prop16;
		props += "//A lock is only requested if the involved switch box is in the route of the requesting train\r\n"+prop15;
		props += "//A lock is only requested if the requesting train has the reservation for the two segments at the switch box\r\n"+prop14;
		props += "//A lock is only successful if the point involved in the request was unlocked prior to the request\r\n"+prop11;
		props += "//A switch is only requested if the requested connection is of segments that are adjacent in the train's route\r\n"+prop20;
		props += "//A switch is only successful if the requested conenction is of the stem segment and plus or minus segment of the switch box\r\n"+prop13;
		props += "//A control box only switches and locks its point if no train is in its critical section\r\n"+prop12;

		props += "\r\n//OPERATION REQUIREMENTS: PASS\r\n";
		props += "//A train only passes a switch box if it has been locked for the train\r\n"+prop10;
		props += "//A train never passes the last control box on its route\r\n"+prop9;
		props += "//A train only enters a segment that it has the full reservation of\r\n"+prop8;

		props += "\r\n//CONSISTENCY\r\n";
		props += "//Reservation consistency: The reservations saved in the state space of a Train are also saved in the state spaces of the involved CBs\r\n"+prop7;
		props += "//Lock consistency: The locks saved in the state space of a Train are also saved in the state spaces of the involved CBs\r\n"+prop6;		
//		props += "Lock consistency: The number of saved locks in the state space of a Train is the same number of locks that it believes that is has \r\n";
//		props += "Network array consistency: The position of a point in the network data is consistent with the actual position of the point\r\n";
//		props += "//Point consistency: A CB's connected information is consistent with its Point's position\r\n"+prop5;
		props += "//Position consistency: The train position saved in a TCC is consistent with the train's actual position\r\n"+prop21;

		props += "//LIVENESS\r\n";
		props += prop1;
		

		System.out.println(props);
		abs += "}";
		return abs;
	}
	
	private String getPointObjectsString(Network n) {
		String ps = "";
		for(ControlBox cb : n.getControlBoxes()) {
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				ps += "p"+pointIDs.get(sb)+":Point(inPlus => ";
				ps += (sb.getConnected() == PointSetting.PLUS) ? "true" : "false";
				
				ps += ", cb => cb"+cbIDs.get(sb)+");\r\n";
			}
		}
		return ps;
	}

	private String getCBObjectsString(Network n) {
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
			cbs += ");\r\n";
		}
		return cbs;
	}

	private String getTrainObjectsString(Network n) {
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
			
			ts += ");\r\n";
		}
		return ts;
	}

	private String createPointClass() {
		return "class Point is\r\n" + 
				"Signals\r\n" + 
				"    switchPoint\r\n" + 
				"Vars\r\n" + 
				"    cb:CB;\r\n" + 
				"    inPlus:bool\r\n" + 
				"Transitions\r\n" + 
				"    Still -> Switching {switchPoint}\r\n" + 
				"    Switching -> Still { - /\r\n" + 
				"        inPlus = !inPlus;\r\n" + 
				"        cb.OKp;}        \r\n" + 
				"end Point;\r\n";
	}

	private String createCBClass() {
		return "Class CB is\r\n" + 
				"Signals\r\n" + 
				"    reqSeg(it:Train, s:int),\r\n" + 
				"    reqLock(it:Train, s1:int, s2:int),\r\n" + 
				"    pass,\r\n" + 
				"    passed,\r\n" + 
				"    OKp\r\n" + 
				"Vars\r\n" + 
				"    segments;\r\n" + 
				"    connected;\r\n" + 
				"    point:Point;\r\n" + 
				"    \r\n" + 
				"    res:Train[3];\r\n" + 
				"    result:int := -1;\r\n" + 
				"    t:Train := null;\r\n" + 
				"    lockedBy:Train;\r\n" + 
				"    \r\n" + 
				"    ERROR:int := 0;\r\n" + 
				"    NOSWITCH:int := 1;\r\n" + 
				"    DOSWITCH:int := 2;\r\n" + 
				"Transitions\r\n" + 
				"    Idle -> SegmentChecked {\r\n" + 
				"        reqSeg(it,s) /\r\n" + 
				"        \r\n" + 
				"        //checkSegment\r\n" + 
				"        for i in 0..2 {\r\n" + 
				"            if (segments[i] == s && res[i] == null) then {\r\n" + 
				"                result = i;\r\n" + 
				"            }\r\n" + 
				"        };\r\n" + 
				"        t = it;\r\n" + 
				"    }\r\n" + 
				"        \r\n" + 
				"    SegmentChecked -> Idle {\r\n" + 
				"        - [result > -1] /\r\n" + 
				"                \r\n" + 
				"        //updateResInfo\r\n" + 
				"        res[result] = t;\r\n" + 
				"        //resetVariables\r\n" + 
				"        result = -1;\r\n" + 
				"        \r\n" + 
				"        t.OK;\r\n" + 
				"        t = null;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    SegmentChecked -> Idle {\r\n" + 
				"        - [result == -1] /\r\n" + 
				"        \r\n" + 
				"        t.notOK;\r\n" + 
				"        \r\n" + 
				"        //resetVariables\r\n" + 
				"        t = null;\r\n" + 
				"        result = -1;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    Idle -> LockChecked {\r\n" + 
				"        reqLock(it,s1,s2) /\r\n" + 
				"        \r\n" + 
				"        //checkLock\r\n" + 
				"        result = ERROR;\r\n" + 
				"        if(lockedBy == null) then {\r\n" + 
				"            if ((segments[0] == s1 && (segments[1] == s2 || segments[2] == s2)) ||\r\n" + 
				"                (segments[0] == s2 && (segments[1] == s1 || segments[2] == s1))) then {\r\n" + 
				"                if((s1 == segments[0] && s2 == connected) || (s2 == segments[0] && s1 == connected)) then {\r\n" + 
				"                    result = NOSWITCH;\r\n" + 
				"                } else {\r\n" + 
				"                    result = DOSWITCH;\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"        };\r\n" + 
				"        t = it;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    LockChecked -> Switching {\r\n" + 
				"        - [result == DOSWITCH] /\r\n" + 
				"        \r\n" + 
				"        point.switchPoint;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    LockChecked -> Idle {\r\n" + 
				"        - [result == ERROR] /\r\n" + 
				"        \r\n" + 
				"        t.notOK;\r\n" + 
				"        //resetVariables\r\n" + 
				"        t = null;\r\n" + 
				"        result = -1\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    LockChecked -> Switched {- [result == NOSWITCH]}\r\n" + 
				"    \r\n" + 
				"    Switching -> Switched {\r\n" + 
				"        OKp /\r\n" + 
				"        //updateConnected\r\n" + 
				"        if(connected == segments[1]) {\r\n" + 
				"            connected = segments[2];\r\n" + 
				"        } else {\r\n" + 
				"            connected = segments[1];\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    Switched -> Idle{\r\n" + 
				"        /\r\n" + 
				"        //updateLockInfo\r\n" + 
				"        lockedBy = t;\r\n" + 
				"        //resetVariables\r\n" + 
				"        result = -1;\r\n" + 
				"        \r\n" + 
				"        t.OK;\r\n" + 
				"        \r\n" + 
				"        //resetVariables\r\n" + 
				"        t = null;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    Idle -> Passing {pass}\r\n" + 
				"    Passing -> Idle {\r\n" + 
				"        passed /\r\n" + 
				"        \r\n" + 
				"        //clear\r\n" + 
				"        lockedBy = null;\r\n" + 
				"        res[0] = null;\r\n" + 
				"        if(connected == segments[1]){\r\n" + 
				"            res[1] = null;\r\n" + 
				"        } else {\r\n" + 
				"            res[2] = null;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"end CB;\r\n";
	}

	private String createTrainClass(int resLimit, int lockLimit) {
		return "Class Train is\r\n" + 
				"Signals\r\n" + 
				"    OK, notOK\r\n" + 
				"Vars\r\n" + 
				"    segments;\r\n" + 
				"    boxes;\r\n" + 
				"    boxIDs;\r\n" + 
				"    \r\n" + 
				"    curSeg:int;\r\n" + 
				"\r\n" + 
				"    requiresLock;\r\n" + 
				"\r\n" + 
				"    lockIndex:int = 1;\r\n" + 
				"    index:int = 0;\r\n" + 
				"\r\n" + 
				"    resBit:int = 0;\r\n" + 
				"    resCBIndex:int = 1;\r\n" + 
				"    resSegIndex:int = 1;\r\n" + 
				"\r\n" + 
				"    headSeg:int = -1;\r\n" + 
				"    locks:int = 0;\r\n" + 
				"\r\n" + 
				"    //TEMP\r\n" + 
				"    resLimit = "+resLimit+";\r\n" + 
				"    lockLimit = "+lockLimit+";\r\n" + 
				"Transitions\r\n" + 
				"    SingleSegment -> Arrived {[index == segments.length-1]}\r\n" + 
				"    Arrived -> Arrived\r\n" + 
				"    \r\n" + 
				"    SingleSegment -> DoubleSegment {\r\n" + 
				"        [resSegIndex > index + 1 && lockIndex > index + 1 && index + 1 < segments.length] / \r\n" + 
				"         \r\n" + 
				"        //updateHeadInfo\r\n" + 
				"        boxes[index+1].pass;\r\n" + 
				"        headSeg = segments[index+1];    \r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    DoubleSegment -> SingleSegment {/ \r\n" + 
				"        //updateLocationInfo\r\n" + 
				"        curSeg = headSeg;\r\n" + 
				"        headSeg = -1;\r\n" + 
				"        if(requiresLock[index +1]){\r\n" + 
				"            locks--;\r\n" + 
				"        };\r\n" + 
				"        index++;\r\n" + 
				"        \r\n" + 
				"        boxes[index].passed; \r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    SingleSegment -> Reserving {\r\n" + 
				"        - [resSegIndex < segments.length && resSegIndex - 1 - index < resLimit] / \r\n" + 
				"        \r\n" + 
				"        boxes[resCBIndex].reqSeg(this, segments[resSegIndex])\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    Reserving -> SingleSegment {\r\n" + 
				"        OK / \r\n" + 
				"        \r\n" + 
				"        //updateResInfo\r\n" + 
				"        if (resBit == 0) {\r\n" + 
				"            resBit = 1;\r\n" + 
				"            resCBIndex++;\r\n" + 
				"        } else {\r\n" + 
				"            resBit = 0;\r\n" + 
				"            resSegIndex++;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"        \r\n" + 
				"    Reserving -> SingleSegment {notOK}\r\n" + 
				"    \r\n" + 
				"    SingleSegment -> Locking {\r\n" + 
				"        [lockIndex < segments.length && locks < lockLimit && ((resBit == 0 && resSegIndex > lockIndex) || (resBit == 1 && resSegIndex >= lockIndex))] / \r\n" + 
				"        \r\n" + 
				"        boxes[lockIndex].reqLock(this, segments[lockIndex-1],segments[lockIndex])\r\n" + 
				"    }\r\n" + 
				"        \r\n" + 
				"    Locking -> SingleSegment {\r\n" + 
				"        OK / \r\n" + 
				"        \r\n" + 
				"        //updateLockInfo\r\n" + 
				"        locks++;\r\n" + 
				"        lockIndex++;\r\n" + 
				"        //updateLockIndex\r\n" + 
				"        for i in lockIndex..segments.length {\r\n" + 
				"            if(!requiresLock[i]){\r\n" + 
				"                lockIndex++;\r\n" + 
				"            } else {\r\n" + 
				"                i = segments.length;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    Locking -> SingleSegment {notOK}\r\n" + 
				"end Train;\r\n";
	}

}