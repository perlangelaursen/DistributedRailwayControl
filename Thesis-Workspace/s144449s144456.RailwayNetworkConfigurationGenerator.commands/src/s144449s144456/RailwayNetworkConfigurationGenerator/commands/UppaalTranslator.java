package s144449s144456.RailwayNetworkConfigurationGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import network.*;

public class UppaalTranslator extends Translator{
	private int id;
	private String start, end;
	
	@Override
	protected String generateCode(Network n) {
		if(n != null) {
//			setStartEndStrings(n);
//			
//			int nPoint = 0;
//			for(ControlBox s : n.getControlBoxes()) {
//				if(s instanceof SwitchBox) {
//					nPoint++;
//				}
//			}
//			String sizesString = "const int NTRAIN = "+n.getTrains().size()+";\n"+
//								 "const int NCB = "+n.getControlBoxes().size()+";\n"+
//								 "const int NPOINT = "+nPoint+";\n"+
//						  		 "const int NSEG = "+n.getSegments().size()+";\n\n";
//			
//			String typesString = "typedef int[0, NTRAIN-1] t_id;\n" + 
//								"typedef int[0, NCB-1]  cB_id;\n" + 
//								"typedef int[0, NPOINT-1] p_id;\n" + 
//								"typedef int[0, NSEG-1] seg_id;\n" + 
//								"typedef int[-1, NTRAIN-1] tV_id;\n" + 
//								"typedef int[-1, NCB-1] cBV_id;\n" + 
//								"typedef int[-1, NPOINT-1] pV_id;\n" + 
//								"typedef int[-1, NSEG-1] segV_id;\n"+
//								"typedef struct {\r\n" + 
//								"    cB_id cb;\r\n" + 
//								"    seg_id seg;\r\n" + 
//								"} reservations;\n\n";
//			
//			//Limits
//			String limitsString = "const int[1,NCB] lockLimit = "+n.getLockLimit()+";\n"+
//								  "const int[1,NSEG] resLimit = "+n.getReserveLimit()+";\n";
//			
//			//Route segments
//			String routesString = "const segV_id segRoutes[NTRAIN][NSEG] = {";
//			for(int i = 0; i < n.getTrains().size()-1; i++) {
//				routesString += trainRoute(n, n.getTrains().get(i))+", ";
//			}
//			routesString += trainRoute(n, n.getTrains().get(n.getTrains().size()-1))+"};\n";
//
//			//Route control boxes
//			String cbsString= "const cBV_id boxRoutes[NTRAIN][NCB] = {";
//			for(int i = 0; i < n.getTrains().size()-1; i++) {
//				cbsString += trainCBs(n, n.getTrains().get(i)) + ", ";
//			}
//			cbsString += trainCBs(n, n.getTrains().get(n.getTrains().size()-1))+"};\n";
//						
//			
//			String cbDetailsString = "const segV_id cBs[NCB][3] = {";
//			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
//				cbDetailsString += cbsDetails(n.getControlBoxes().get(i))+", ";
//			}
//			cbDetailsString += cbsDetails(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
//			
//			//Initial reservations
//			String initRes = "const reservations initialRes[NTRAIN] = {";
//			for(int i = 0; i < n.getTrains().size()-1; i++) {
//				int[] res = getRes(n.getTrains().get(i));
//				initRes += "{"+res[0]+", "+res[1]+"},";
//			}
//			int[] res = getRes(n.getTrains().get(n.getTrains().size()-1));
//			initRes += "{"+res[0]+", "+res[1]+"}};\n";
//			
//			//Points
//			String pointsString = "const pV_id points[NCB] = {";
//			id = 0;
//			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
//				ControlBox cb = n.getControlBoxes().get(i);
//				pointsString += pointID(cb)+", ";
//			} 
//			pointsString += pointID(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
//			
//			String pointSettingsString = "const bool pointInPlus[NPOINT] = {";
//			for(int i = 0; i < switchBoxes.size()-1; i++) {
//				pointSettingsString += (switchBoxes.get(i).getConnected() == PointSetting.PLUS)+", ";
//			}
//			pointSettingsString += (switchBoxes.get(switchBoxes.size()-1).getConnected() == PointSetting.PLUS)+"};\n\n";
			
			//Channels
			String channelsString = "chan resSeg[NCB][NTRAIN][NSEG];\r\n" + 
									"chan reqLock[NCB][NTRAIN][NSEG][NSEG];\r\n" + 
									"chan OK[NTRAIN];\r\n" + 
									"chan notOK[NTRAIN];\r\n" + 
									"chan pass[NCB];\r\n" + 
									"chan passed[NCB];\r\n" + 
									"chan switchPoint[NPOINT];\r\n" + 
									"chan OKp[NCB];\r\n" + 
									"urgent broadcast chan start;";
			
			//Generate file
			PrintWriter writer;
			try {
				writer = new PrintWriter("test.xml", "UTF-8");
				writer.println(start);
//				writer.println(sizesString + typesString + limitsString + routesString + cbsString + cbDetailsString + initRes + pointsString + pointSettingsString + channelsString);
				writer.println(end);
				writer.close();
				return "Model file successfully generated.";
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "An error occurred";
	}

//	private int[] getRes(Train t) {
//		Segment s1 = t.getRoute().get(0);
//		Segment s2 = t.getRoute().get(1);
//		ControlBox[] cbs = getSegmentBoxes(s1, s2);
//		int[] res = new int[2];
//		res[0] = cbIDs.get(cbs[1]);
//		res[1] = segIDs.get(s1);
//		return res;
//	}

	private String pointID(ControlBox cb) {
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

	private String trainRoute(Network n, Train t) {
		String routesString = "{";
		for(int j = 0; j < n.getSegments().size()-1; j++) {
			if (j < t.getRoute().size()) {
				routesString += segIDs.get(t.getRoute().get(j))+",";
			} else {
				routesString += "-1,";
			}
		}
		if(t.getRoute().size() < n.getSegments().size()) {
			routesString += "-1}";
		} else {
			routesString += segIDs.get(t.getRoute().get(t.getRoute().size()-1))+"}";
		}
		return routesString;
	}
	

//	private String trainCBs(Network n, Train t) {
//		String cbsString = "{";
//		for(int j = 0; j < t.getRoute().size()-1; j+=2) {
//			ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(j), t.getRoute().get(j+1));
//			cbsString += cbIDs.get(cbs[0])+", "+cbIDs.get(cbs[1])+", ";
//		}
//		ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(t.getRoute().size()-2), t.getRoute().get(t.getRoute().size()-1));
//		cbsString += cbIDs.get(cbs[2]);
//		for(int j = t.getRoute().size(); j < n.getControlBoxes().size()-1; j++) {
//			cbsString += ", -1";	
//		}
//		cbsString += "}";
//		return cbsString;
//	}

	private void setStartEndStrings(Network n) {		
		start = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"<!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat System 1.1//EN' 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_2.dtd'>\r\n" + 
				"<nta>\r\n" + 
				"	<declaration>";
		end = "</declaration>\n"+
				"<template>\r\n" + 
				"		<name x=\"5\" y=\"5\">TCC</name>\r\n" + 
				"		<parameter>t_id id</parameter>\r\n" + 
				"		<declaration>seg_id nextSegIndex = 1;\r\n" + 
				"segV_id curSeg;\r\n" + 
				"segV_id nextSeg;\r\n" + 
				"cBV_id nextCB;\r\n" + 
				"seg_id routeLength;\r\n" + 
				"\r\n" + 
				"int[0,1] resBit = 0;\r\n" + 
				"cB_id resCBIndex = 1;\r\n" + 
				"seg_id resSegIndex = 1;\r\n" + 
				"cB_id lockIndex = 1;\r\n" + 
				"\r\n" + 
				"segV_id segments[NSEG];\r\n" + 
				"cBV_id boxes[NCB];\r\n" + 
				"\r\n" + 
				"void copySegments() {\r\n" + 
				"    for(i : seg_id) {\r\n" + 
				"        segments[i] = segRoutes[id][i];\r\n" + 
				"        if(segments[i]&gt;-1) {\r\n" + 
				"            routeLength++;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    curSeg = segments[0];\r\n" + 
				"    nextSeg = segments[1];\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"void copyControlBoxes() {\r\n" + 
				"    for(i : cB_id) {\r\n" + 
				"        boxes[i] = boxRoutes[id][i];\r\n" + 
				"    }\r\n" + 
				"    nextCB = boxes[1];\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool possibleToLockSegment() {\r\n" + 
				"    return lockIndex &lt;  routeLength &amp;&amp; lockIndex - nextSegIndex &lt; lockLimit &amp;&amp; resSegIndex &gt; lockIndex;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool possibleToReserveSegment() {\r\n" + 
				"    return resSegIndex &lt; routeLength &amp;&amp;  resSegIndex - nextSegIndex &lt; resLimit;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool possibleToPassBox() {\r\n" + 
				"    return resSegIndex &gt; nextSegIndex &amp;&amp; lockIndex &gt; nextSegIndex;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool hasArrived() {\r\n" + 
				"    return nextSegIndex == routeLength;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool validRoute() {\r\n" + 
				"    bool result = true;\r\n" + 
				"    for(j : int [0, NSEG-2]){\r\n" + 
				"        result = result &amp;&amp; !(segments[j] == -1 &amp;&amp; segments[j+1] &gt; -1);\r\n" + 
				"    }\r\n" + 
				"    return result;\r\n" + 
				"}</declaration>\r\n" + 
				"		<location id=\"id0\" x=\"-340\" y=\"-1156\">\r\n" + 
				"			<name x=\"-323\" y=\"-1173\">DoubleSegment</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id1\" x=\"-1020\" y=\"-1156\">\r\n" + 
				"			<name x=\"-1030\" y=\"-1190\">Initial</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id2\" x=\"-680\" y=\"-1156\">\r\n" + 
				"			<name x=\"-748\" y=\"-1181\">Arrived</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id3\" x=\"-340\" y=\"-748\">\r\n" + 
				"			<name x=\"-323\" y=\"-765\">Reserving</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id4\" x=\"-680\" y=\"-952\">\r\n" + 
				"			<name x=\"-807\" y=\"-969\">SingleSegment</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id5\" x=\"-1020\" y=\"-748\">\r\n" + 
				"			<name x=\"-1044\" y=\"-732\">Locking</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id1\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id1\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-986\" y=\"-1096\">start?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-986\" y=\"-1079\">copySegments(),\r\n" + 
				"copyControlBoxes()</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id5\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-833\" y=\"-774\">notOK[id]?</label>\r\n" + 
				"			<nail x=\"-748\" y=\"-748\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id5\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-841\" y=\"-858\">OK[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-850\" y=\"-841\">lockIndex++</label>\r\n" + 
				"			<nail x=\"-850\" y=\"-850\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id3\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-323\" y=\"-875\">OK[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-323\" y=\"-858\">resBit = resBit^1,\r\n" + 
				"resSegIndex = (resBit==0) ? resSegIndex + 1 : resSegIndex,\r\n" + 
				"resCBIndex = (resBit==1) ? resCBIndex + 1 : resCBIndex</label>\r\n" + 
				"			<nail x=\"-340\" y=\"-884\"/>\r\n" + 
				"			<nail x=\"-510\" y=\"-918\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id2\"/>\r\n" + 
				"			<target ref=\"id2\"/>\r\n" + 
				"			<nail x=\"-714\" y=\"-1224\"/>\r\n" + 
				"			<nail x=\"-646\" y=\"-1224\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id4\"/>\r\n" + 
				"			<target ref=\"id0\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-544\" y=\"-1147\">possibleToPassBox()</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-544\" y=\"-1130\">pass[nextCB]!</label>\r\n" + 
				"			<nail x=\"-578\" y=\"-1156\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id4\"/>\r\n" + 
				"			<target ref=\"id2\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-765\" y=\"-1113\">hasArrived()</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id3\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-501\" y=\"-867\">notOK[id]?</label>\r\n" + 
				"			<nail x=\"-552\" y=\"-875\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id0\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-323\" y=\"-1130\">passed[nextCB]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-323\" y=\"-1113\">curSeg = nextSeg,\r\n" + 
				"nextSegIndex++,\r\n" + 
				"nextCB = boxes[nextSegIndex],\r\n" + 
				"nextSeg = segments[nextSegIndex]</label>\r\n" + 
				"			<nail x=\"-340\" y=\"-1020\"/>\r\n" + 
				"			<nail x=\"-595\" y=\"-969\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id4\"/>\r\n" + 
				"			<target ref=\"id5\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-1309\" y=\"-833\">possibleToLockSegment()</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1309\" y=\"-816\">reqLock[boxes[lockIndex]][id]\r\n" + 
				"[segments[lockIndex-1]][segments[lockIndex]]!</label>\r\n" + 
				"			<nail x=\"-1020\" y=\"-884\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id4\"/>\r\n" + 
				"			<target ref=\"id3\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-612\" y=\"-740\">possibleToReserveSegment()</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-612\" y=\"-722\">resSeg[boxes[resCBIndex]][id][segments[resSegIndex]]!</label>\r\n" + 
				"			<nail x=\"-629\" y=\"-790\"/>\r\n" + 
				"			<nail x=\"-612\" y=\"-748\"/>\r\n" + 
				"			<nail x=\"-527\" y=\"-748\"/>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<template>\r\n" + 
				"		<name>Initializer</name>\r\n" + 
				"		<location id=\"id6\" x=\"0\" y=\"136\">\r\n" + 
				"			<name x=\"8\" y=\"110\">Initialized</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id7\" x=\"0\" y=\"0\">\r\n" + 
				"			<name x=\"-10\" y=\"-34\">Uninitialized</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id7\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id7\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"8\" y=\"51\">start!</label>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<template>\r\n" + 
				"		<name>CB</name>\r\n" + 
				"		<parameter>cB_id id</parameter>\r\n" + 
				"		<declaration>segV_id stem = -1;\r\n" + 
				"segV_id plus = -1;\r\n" + 
				"segV_id minus = -1;\r\n" + 
				"segV_id connected =-1;\r\n" + 
				"pV_id point = -1;\r\n" + 
				"\r\n" + 
				"tV_id res[3] = {-1, -1, -1};\r\n" + 
				"int[0,2] result;\r\n" + 
				"tV_id tid = -1;\r\n" + 
				"tV_id lockedBy = -1;\r\n" + 
				"\r\n" + 
				"const int ERROR = 0;\r\n" + 
				"const int NOSWITCH = 1;\r\n" + 
				"const int DOSWITCH = 2;\r\n" + 
				"\r\n" + 
				"void setInfo() {\r\n" + 
				"    stem = cBs[id][0];\r\n" + 
				"    plus = cBs[id][1];\r\n" + 
				"    minus = cBs[id][2];\r\n" + 
				"    connected = plus;\r\n" + 
				"    point = points[id];\r\n" + 
				"\r\n" + 
				"    if(point != -1 &amp;&amp; !pointInPlus[point]){\r\n" + 
				"        connected = minus;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    for(i : t_id) {\r\n" + 
				"        if (initialRes[i].cb == id) {\r\n" + 
				"            seg_id s = initialRes[i].seg;\r\n" + 
				"            if(s == stem){\r\n" + 
				"                res[0] = i;\r\n" + 
				"            } else if (s == plus){\r\n" + 
				"                res[1] = i;\r\n" + 
				"            } else {\r\n" + 
				"                res[2] = i;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool reserve(t_id tid, seg_id sid){\r\n" + 
				"  if(stem == sid &amp;&amp; res[0] == -1){\r\n" + 
				"      res[0] = tid;\r\n" + 
				"      return true;\r\n" + 
				"  } else if (plus == sid &amp;&amp; res[1] == -1){\r\n" + 
				"      res[1] = tid;\r\n" + 
				"      return true;\r\n" + 
				"  } else if (minus == sid &amp;&amp; res[2] == -1){\r\n" + 
				"      res[2] = tid;\r\n" + 
				"      return true;\r\n" + 
				"  } else {\r\n" + 
				"    return false;\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int[0,2] lock(t_id tid, seg_id s1, seg_id s2){\r\n" + 
				"  if(lockedBy == -1 &amp;&amp; (s1 != s2) &amp;&amp; (s1 == stem || s2 == stem) &amp;&amp; (s1 == stem || s1 == plus || s1 == minus) &amp;&amp; (s2 == stem || s2 == plus || s2 == minus)){\r\n" + 
				"      if((s1 == stem &amp;&amp; s2 != connected) || (s2 == stem &amp;&amp; s1 != connected)){\r\n" + 
				"        return DOSWITCH;\r\n" + 
				"      } else if ((s1 == stem &amp;&amp; s2 == connected) || (s2 == stem &amp;&amp; s1 == connected)){\r\n" + 
				"        return NOSWITCH;\r\n" + 
				"      }\r\n" + 
				"   }\r\n" + 
				"   return ERROR;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"void clear(){\r\n" + 
				"     lockedBy = -1;\r\n" + 
				"\r\n" + 
				"     res[0] = -1;\r\n" + 
				"     if(connected == plus) {\r\n" + 
				"        res[1] = -1;\r\n" + 
				"     } else {\r\n" + 
				"        res[2] = -1;\r\n" + 
				"     }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"void updateConnected(){\r\n" + 
				"    if(connected == plus){\r\n" + 
				"        connected = minus;\r\n" + 
				"    } else {\r\n" + 
				"        connected = plus;\r\n" + 
				"    }\r\n" + 
				"}</declaration>\r\n" + 
				"		<location id=\"id8\" x=\"-986\" y=\"34\">\r\n" + 
				"			<name x=\"-1020\" y=\"8\">Initial</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id9\" x=\"-1139\" y=\"-68\">\r\n" + 
				"			<name x=\"-1164\" y=\"-102\">Passing</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id10\" x=\"-356\" y=\"-34\">\r\n" + 
				"			<name x=\"-366\" y=\"-68\">Switching</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id11\" x=\"-884\" y=\"-306\">\r\n" + 
				"			<name x=\"-940\" y=\"-338\">ReserveAttempted</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id12\" x=\"-170\" y=\"-34\">\r\n" + 
				"			<name x=\"-154\" y=\"-42\">Switched</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id13\" x=\"-646\" y=\"-34\">\r\n" + 
				"			<name x=\"-680\" y=\"-17\">LockAttempted</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id14\" x=\"-884\" y=\"-68\">\r\n" + 
				"			<name x=\"-858\" y=\"-93\">Idle</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id8\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id8\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-8\">start?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-960\" y=\"8\">setInfo()</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id9\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1079\" y=\"-136\">passed[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1079\" y=\"-119\">clear()</label>\r\n" + 
				"			<nail x=\"-1020\" y=\"-102\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id10\"/>\r\n" + 
				"			<target ref=\"id12\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-331\" y=\"-34\">OKp[point]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-331\" y=\"-17\">updateConnected()</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id13\"/>\r\n" + 
				"			<target ref=\"id12\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-458\" y=\"-153\">result == NOSWITCH</label>\r\n" + 
				"			<nail x=\"-646\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-322\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-170\" y=\"-136\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id14\"/>\r\n" + 
				"			<target ref=\"id9\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1105\" y=\"-34\">pass[id]?</label>\r\n" + 
				"			<nail x=\"-952\" y=\"-51\"/>\r\n" + 
				"			<nail x=\"-1020\" y=\"-34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id13\"/>\r\n" + 
				"			<target ref=\"id10\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-569\" y=\"-68\">result == DOSWITCH</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-569\" y=\"-51\">switchPoint[point]!</label>\r\n" + 
				"			<nail x=\"-373\" y=\"-34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id13\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-34\">result == ERROR</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-17\">notOK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-782\" y=\"0\">tid = -1</label>\r\n" + 
				"			<nail x=\"-816\" y=\"-34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id11\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-807\" y=\"-289\">!result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-807\" y=\"-272\">notOK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-807\" y=\"-255\">tid = -1</label>\r\n" + 
				"			<nail x=\"-816\" y=\"-272\"/>\r\n" + 
				"			<nail x=\"-816\" y=\"-136\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id11\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-926\" y=\"-238\">result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-221\">OK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-943\" y=\"-204\">tid = -1</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id14\"/>\r\n" + 
				"			<target ref=\"id13\"/>\r\n" + 
				"			<label kind=\"select\" x=\"-790\" y=\"-187\">i : t_id,\r\n" + 
				"j : seg_id,\r\n" + 
				"k : seg_id</label>\r\n" + 
				"			<label kind=\"guard\" x=\"-790\" y=\"-136\">lockedBy == -1</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-790\" y=\"-119\">reqLock[id][i][j][k]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-790\" y=\"-102\">result = lock(i,j,k),\r\n" + 
				"tid = i</label>\r\n" + 
				"			<nail x=\"-714\" y=\"-68\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id14\"/>\r\n" + 
				"			<target ref=\"id11\"/>\r\n" + 
				"			<label kind=\"select\" x=\"-1088\" y=\"-263\">i : t_id,\r\n" + 
				"j : seg_id</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1088\" y=\"-229\">resSeg[id][i][j]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1088\" y=\"-212\">result = reserve(i,j),\r\n" + 
				"tid = i</label>\r\n" + 
				"			<nail x=\"-952\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-952\" y=\"-272\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id12\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-722\" y=\"34\">OK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-722\" y=\"51\">lockedBy = tid,\r\n" + 
				"tid = -1</label>\r\n" + 
				"			<nail x=\"-170\" y=\"34\"/>\r\n" + 
				"			<nail x=\"-884\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<template>\r\n" + 
				"		<name>Point</name>\r\n" + 
				"		<parameter>p_id id</parameter>\r\n" + 
				"		<location id=\"id15\" x=\"136\" y=\"-85\">\r\n" + 
				"			<name x=\"102\" y=\"-119\">SwitchingPM</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id16\" x=\"136\" y=\"153\">\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id17\" x=\"136\" y=\"68\">\r\n" + 
				"			<name x=\"102\" y=\"34\">SwitchingMP</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id18\" x=\"340\" y=\"0\">\r\n" + 
				"			<name x=\"330\" y=\"-30\">Minus</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id19\" x=\"-68\" y=\"0\">\r\n" + 
				"			<name x=\"-78\" y=\"-30\">Plus</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id16\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id16\"/>\r\n" + 
				"			<target ref=\"id18\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"238\" y=\"119\">!pointInPlus[id]</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"289\" y=\"136\">start?</label>\r\n" + 
				"			<nail x=\"340\" y=\"153\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id16\"/>\r\n" + 
				"			<target ref=\"id19\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-51\" y=\"119\">pointInPlus[id]</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-50\" y=\"136\">start?</label>\r\n" + 
				"			<nail x=\"-68\" y=\"153\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id19\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-42\" y=\"-68\">switchPoint[id]?</label>\r\n" + 
				"			<nail x=\"34\" y=\"-42\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id18\"/>\r\n" + 
				"			<target ref=\"id17\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"212\" y=\"42\">switchPoint[id]?</label>\r\n" + 
				"			<nail x=\"238\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id15\"/>\r\n" + 
				"			<target ref=\"id18\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"221\" y=\"-68\">OKp[id]!</label>\r\n" + 
				"			<nail x=\"238\" y=\"-42\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id17\"/>\r\n" + 
				"			<target ref=\"id19\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"0\" y=\"42\">OKp[id]!</label>\r\n" + 
				"			<nail x=\"34\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<system>system Initializer, TCC, CB, Point;</system>\r\n" + 
				"	<queries>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) not(CB(i).Switching) &amp;&amp; CB(i).stem != -1 &amp;&amp; CB(i).plus != -1 &amp;&amp; CB(i).minus != -1 &amp;&amp; CB(i).connected != -1 &amp;&amp; points[i] != -1 imply (Point(points[i]).Plus &amp;&amp; CB(i).connected == cBs[i][1]) || (Point(points[i]).Minus &amp;&amp; CB(i).connected == cBs[i][2])\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>The local state information about connected segments of a switch box is consistent with the actual state of the switch.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (TCC(i).DoubleSegment imply (CB(TCC(i).nextCB).connected == TCC(i).curSeg &amp;&amp; CB(TCC(i).nextCB).stem == TCC(i).nextSeg) || (CB(TCC(i).nextCB).stem == TCC(i).curSeg &amp;&amp; CB(TCC(i).nextCB).connected == TCC(i).nextSeg))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train can only cross a point if the point's position is consistent with the segment that the trains is entering.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (TCC(i).DoubleSegment imply points[TCC(i).nextCB] == -1 || Point(points[TCC(i).nextCB]).Plus || Point(points[TCC(i).nextCB]).Minus)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No derailment.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (not(TCC(i).Initial) imply (cBs[TCC(i).nextCB][0] == TCC(i).curSeg imply (cBs[TCC(i).nextCB][1] == TCC(i).nextSeg || cBs[TCC(i).nextCB][2] == TCC(i).nextSeg)) &amp;&amp; (cBs[TCC(i).nextCB][0] == TCC(i).nextSeg imply (cBs[TCC(i).nextCB][1] == TCC(i).curSeg || cBs[TCC(i).nextCB][2] == TCC(i).curSeg)))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train's current segment is either the same as its next segment or connected to it.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] not deadlock\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>E&lt;&gt; forall(i:t_id) TCC(i).Arrived\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>All trains will eventually arrive at their destinations.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:t_id) i != j and not(TCC(i).Initial) and not(TCC(j).Initial) imply TCC(i).curSeg != TCC(j).curSeg\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Two trains are never on the same segment after the initialization step.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) TCC(i).validRoute()\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>All trains must have a valid route composition. This means that the actual route must be greater than -1 and the padding must be -1.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(id:t_id) forall(i:cB_id) TCC(id).Initial or (TCC(id).boxes[i] == boxRoutes[id][i] and not(TCC(id).Initial))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A trains internal notion of the control boxes will always be the same as its global counterpart after it has left the initial state\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(id:t_id) forall(i:seg_id) TCC(id).Initial or (TCC(id).segments[i] == segRoutes[id][i] and not(TCC(id).Initial))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A trains internal notion of its route will always be the same as its global counterpart after it has left the initial state\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"	</queries>\r\n" + 
				"</nta>\r\n";
	}

}
