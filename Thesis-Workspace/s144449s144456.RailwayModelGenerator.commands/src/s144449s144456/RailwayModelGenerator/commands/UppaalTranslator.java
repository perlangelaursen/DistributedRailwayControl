package s144449s144456.RailwayModelGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import network.*;

public class UppaalTranslator extends Translator{
	private Map<ControlBox, Integer> cbIDs;
	private Map<Segment, Integer> segIDs;
	private LinkedList<SwitchBox> points;
	private int id;
	private String start, end;
	
	@Override
	protected void generateCode(Network n) {
		if(n != null) {
			initialize(n);
			
			int nPoint = 0;
			for(ControlBox s : n.getControlBoxes()) {
				if(s instanceof SwitchBox) {
					nPoint++;
				}
			}
			String sizesString = "const int NTRAIN = "+n.getTrains().size()+";\n"+
								 "const int NCB = "+n.getControlBoxes().size()+";\n"+
								 "const int NPOINT = "+nPoint+";\n"+
						  		 "const int NSEG = "+n.getSegments().size()+";\n";
			
			String typesString = "typedef int[0, NTRAIN-1] t_id;\n" + 
								"typedef int[0, NCB-1]  cB_id;\n" + 
								"typedef int[0, NPOINT-1] p_id;\n" + 
								"typedef int[0, NSEG-1] seg_id;\n" + 
								"typedef int[-1, NTRAIN-1] tV_id;\n" + 
								"typedef int[-1, NCB-1] cBV_id;\n" + 
								"typedef int[-1, NPOINT-1] pV_id;\n" + 
								"typedef int[-1, NSEG-1] segV_id;\n"+
								"typedef struct {\r\n" + 
								"    cB_id cb;\r\n" + 
								"    tV_id res[3];\r\n" + 
								"} InitialReserves;";
			
			//Limits
			String limitsString = "const int[1,NCB] lockLimit = "+n.getLockLimit()+";\n"+
								  "const int[1,NSEG] resLimit = "+n.getReserveLimit()+";\n";
			
			//Route segments
			String routesString = "const segV_id segRoutes[NTRAIN][NSEG] = {";
			for(int i = 0; i < n.getTrains().size()-1; i++) {
				routesString += trainRoute(n, n.getTrains().get(i))+", ";
			}
			routesString += trainRoute(n, n.getTrains().get(n.getTrains().size()-1))+"};\n";

			//Route control boxes
			String cbsString= "const cBV_id boxRoutes[NTRAIN][NCB] = {";
			for(int i = 0; i < n.getTrains().size()-1; i++) {
				cbsString += trainCBs(n, n.getTrains().get(i)) + ", ";
			}
			cbsString += trainCBs(n, n.getTrains().get(n.getTrains().size()-1))+"};\n";
						
			
			String cbDetailsString = "const segV_id cBs[NCB][3] = {";
			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
				cbDetailsString += cbsDetails(n.getControlBoxes().get(i))+", ";
			}
			cbDetailsString += cbsDetails(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
			
			//Points
			String pointsString = "const pV_id points[NCB] = {";
			id = 0;
			for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
				ControlBox cb = n.getControlBoxes().get(i);
				pointsString += pointID(cb)+", ";
			} 
			pointsString += pointID(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
			
			String pointSettingsString = "const bool pointInPlus[NPOINT] = {";
			for(int i = 0; i < points.size()-1; i++) {
				pointSettingsString += (points.get(i).getConnected() == PointSetting.PLUS)+", ";
			}
			pointSettingsString += (points.get(points.size()-1).getConnected() == PointSetting.PLUS)+"};\n";
			
			//Channels
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
				writer = new PrintWriter("test.xml", "UTF-8");
				writer.println(start);
				writer.println(sizesString + typesString + limitsString + routesString + cbsString + cbDetailsString + pointsString + pointSettingsString + channelsString);
				writer.println(end);
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
	}

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
	

	private String trainCBs(Network n, Train t) {
		String cbsString = "{";
		for(int j = 0; j < t.getRoute().size()-1; j+=2) {
			ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(j), t.getRoute().get(j+1));
			cbsString += cbIDs.get(cbs[0])+", "+cbIDs.get(cbs[1])+", ";
		}
		ControlBox[] cbs = getSegmentBoxes(t.getRoute().get(t.getRoute().size()-2), t.getRoute().get(t.getRoute().size()-1));
		cbsString += cbIDs.get(cbs[2]);
		for(int j = t.getRoute().size(); j < n.getControlBoxes().size()-1; j++) {
			cbsString += ", -1";	
		}
		cbsString += "}";
		return cbsString;
	}

	private void initialize(Network n) {
		int i = 0;
		
		cbIDs = new HashMap<>();
		points = new LinkedList<>();
		for(ControlBox cb : n.getControlBoxes()) {
			cbIDs.put(cb, i);
			i++;
			if(cb instanceof SwitchBox) {
				points.add((SwitchBox) cb);
			}
		}
		
		segIDs = new HashMap<>();
		i = 0;
		for(Segment s : n.getSegments()) {
			segIDs.put(s, i);
			i++;
		}
		
		start = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"<!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat System 1.1//EN' 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_2.dtd'>\r\n" + 
				"<nta>\r\n" + 
				"	<declaration>";
		end = "</declaration>\r\n" + 
				"	<template>\r\n" + 
				"		<name x=\"5\" y=\"5\">TCC</name>\r\n" + 
				"		<parameter>t_id id</parameter>\r\n" + 
				"		<declaration>int i = 1;\r\n" + 
				"int curSeg;\r\n" + 
				"int nextSeg;\r\n" + 
				"int nextSB;\r\n" + 
				"\r\n" + 
				"int cb = 0;\r\n" + 
				"int ci = 1;\r\n" + 
				"int ri = 1;\r\n" + 
				"int cl = 1;\r\n" + 
				"</declaration>\r\n" + 
				"		<location id=\"id0\" x=\"-442\" y=\"34\">\r\n" + 
				"			<name x=\"-425\" y=\"17\">CrossingPoint</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id1\" x=\"-1122\" y=\"-374\">\r\n" + 
				"			<name x=\"-1132\" y=\"-408\">Initial</name>\r\n" + 
				"			<committed/>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id2\" x=\"-1122\" y=\"-204\">\r\n" + 
				"			<name x=\"-1105\" y=\"-229\">Initial5</name>\r\n" + 
				"			<committed/>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id3\" x=\"-1122\" y=\"-276\">\r\n" + 
				"			<name x=\"-1105\" y=\"-285\">Initial4</name>\r\n" + 
				"			<committed/>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id4\" x=\"-782\" y=\"-374\">\r\n" + 
				"			<name x=\"-850\" y=\"-399\">Arrived</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id5\" x=\"-442\" y=\"-374\">\r\n" + 
				"			<name x=\"-425\" y=\"-391\">Reserving</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id6\" x=\"-782\" y=\"-170\">\r\n" + 
				"			<name x=\"-909\" y=\"-187\">SingleSegment</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id7\" x=\"-1122\" y=\"34\">\r\n" + 
				"			<name x=\"-1146\" y=\"50\">Locking</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id1\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id0\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-705\" y=\"8\">notOK[id]?</label>\r\n" + 
				"			<nail x=\"-714\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id7\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-935\" y=\"8\">notOK[id]?</label>\r\n" + 
				"			<nail x=\"-850\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id7\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-76\">OK[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-952\" y=\"-59\">cl++</label>\r\n" + 
				"			<nail x=\"-952\" y=\"-68\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id5\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-493\" y=\"-221\">OK[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-493\" y=\"-204\">cb:=cb^1,\r\n" + 
				"ri:=(cb==0) ? ri+1 : ri,\r\n" + 
				"ci:=(cb==1) ? ci+1 : ci</label>\r\n" + 
				"			<nail x=\"-442\" y=\"-238\"/>\r\n" + 
				"			<nail x=\"-612\" y=\"-204\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id1\"/>\r\n" + 
				"			<target ref=\"id3\"/>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1292\" y=\"-357\">curSeg := routes[id][0],\r\n" + 
				"nextSeg := routes[id][1],\r\n" + 
				"nextSB:=controlBoxes[id][ri]</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id4\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<nail x=\"-816\" y=\"-442\"/>\r\n" + 
				"			<nail x=\"-748\" y=\"-442\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id3\"/>\r\n" + 
				"			<target ref=\"id2\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1343\" y=\"-255\">resSeg[nextSB][id][curSeg]!</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id2\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-986\" y=\"-229\">OK[id]?</label>\r\n" + 
				"			<nail x=\"-841\" y=\"-204\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id6\"/>\r\n" + 
				"			<target ref=\"id0\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-722\" y=\"-76\">ri &gt; i &amp;&amp; cl &gt; i</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-722\" y=\"-59\">pass[nextSB][id]!</label>\r\n" + 
				"			<nail x=\"-569\" y=\"-42\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id6\"/>\r\n" + 
				"			<target ref=\"id4\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-1028\" y=\"-331\">i&gt;=NSEG || routes[id][i] == -1</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id5\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-688\" y=\"-297\">notOK[id]?</label>\r\n" + 
				"			<nail x=\"-612\" y=\"-272\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id0\"/>\r\n" + 
				"			<target ref=\"id6\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-433\" y=\"-110\">OK[id]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-433\" y=\"-93\">curSeg := nextSeg,\r\n" + 
				"i++,\r\n" + 
				"nextSB:=controlBoxes[id][i],\r\n" + 
				"nextSeg := routes[id][i]</label>\r\n" + 
				"			<nail x=\"-442\" y=\"-102\"/>\r\n" + 
				"			<nail x=\"-476\" y=\"-110\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id6\"/>\r\n" + 
				"			<target ref=\"id7\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-1300\" y=\"-119\">cl&lt;NSEG &amp;&amp;\r\n" + 
				"cl-i &lt; lockLimit &amp;&amp;\r\n" + 
				"routes[id][cl] != -1</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1394\" y=\"-51\">reqLock[controlBoxes[id][cl]][id]\r\n" + 
				"[routes[id][cl-1]][routes[id][cl]]!</label>\r\n" + 
				"			<nail x=\"-1122\" y=\"-102\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id6\"/>\r\n" + 
				"			<target ref=\"id5\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-697\" y=\"-433\">ri-i &lt; resLimit &amp;&amp; ri &lt; NSEG &amp;&amp; routes[id][ri] != -1</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-697\" y=\"-416\">resSeg[controlBoxes[id][ci]][id][routes[id][ri]]!</label>\r\n" + 
				"			<nail x=\"-714\" y=\"-374\"/>\r\n" + 
				"			<nail x=\"-612\" y=\"-374\"/>\r\n" + 
				"			<nail x=\"-578\" y=\"-374\"/>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<template>\r\n" + 
				"		<name>CB</name>\r\n" + 
				"		<parameter>CB_id id</parameter>\r\n" + 
				"		<declaration>int res[3] = {-1, -1, -1};\r\n" + 
				"int[0,2] result;\r\n" + 
				"int tid = -1;\r\n" + 
				"int lockedBy = -1;\r\n" + 
				"int stem;\r\n" + 
				"int connected;\r\n" + 
				"\r\n" + 
				"bool reserve(int tid, int sid){\r\n" + 
				"  for(i : int[0,2]) {\r\n" + 
				"    if (CBs[id][i] == sid &amp;&amp; res[i] == -1) {\r\n" + 
				"      res[i] = tid;\r\n" + 
				"      return true;\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"  return false;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int[0,2] lock(int tid, int s1, int s2){\r\n" + 
				"  if(lockedBy == -1 &amp;&amp; (s1 != s2) &amp;&amp; (s1 == CBs[id][0] || s2 == CBs[id][0])){\r\n" + 
				"      int s = 0;\r\n" + 
				"      for (i:int[0,2]){\r\n" + 
				"        if(CBs[id][i] == s1){\r\n" + 
				"           if(res[i] == tid) {\r\n" + 
				"               s++;\r\n" + 
				"           }\r\n" + 
				"        }\r\n" + 
				"        if(CBs[id][i] == s2 ){\r\n" + 
				"            if(res[i] == tid){\r\n" + 
				"               s++;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"      if (s==2  &amp;&amp; connected != s1 &amp;&amp;  connected != s2){\r\n" + 
				"        return 2;\r\n" + 
				"      } else if (s==2) {\r\n" + 
				"        return 1;\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"    return 0;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"bool clear(int tid){\r\n" + 
				"  if (lockedBy == tid){\r\n" + 
				"      lockedBy = -1;\r\n" + 
				"\r\n" + 
				"      for (i : int[0,2]) \r\n" + 
				"      {\r\n" + 
				"        if(res[i] == tid){\r\n" + 
				"            res[i] = -1;\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"      return true;\r\n" + 
				"    }\r\n" + 
				"    return false;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"void updateConnected(){\r\n" + 
				"    if(connected == CBs[id][1]){\r\n" + 
				"        connected := CBs[id][2];\r\n" + 
				"    } else {\r\n" + 
				"        connected := CBs[id][1];\r\n" + 
				"    }\r\n" + 
				"}</declaration>\r\n" + 
				"		<location id=\"id8\" x=\"-994\" y=\"68\">\r\n" + 
				"			<name x=\"-1036\" y=\"42\">Initial</name>\r\n" + 
				"			<committed/>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id9\" x=\"-1139\" y=\"-68\">\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id10\" x=\"-314\" y=\"-34\">\r\n" + 
				"			<name x=\"-324\" y=\"-68\">Switching</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id11\" x=\"-510\" y=\"-34\">\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id12\" x=\"-884\" y=\"-306\">\r\n" + 
				"			<name x=\"-940\" y=\"-338\">ReserveAttempted</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id13\" x=\"-136\" y=\"-34\">\r\n" + 
				"			<name x=\"-120\" y=\"-42\">Switched</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id14\" x=\"-680\" y=\"-34\">\r\n" + 
				"			<name x=\"-671\" y=\"-59\">LockAttempted</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id15\" x=\"-884\" y=\"-68\">\r\n" + 
				"			<name x=\"-858\" y=\"-93\">Idle</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id8\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id8\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"assignment\" x=\"-977\" y=\"51\">stem = CBs[id][0],\r\n" + 
				"connected = CBs[id][1]</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id9\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-1037\" y=\"-119\">result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1037\" y=\"-102\">OK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1037\" y=\"-85\">tid:=-1</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id9\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-1122\" y=\"-153\">!result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1122\" y=\"-136\">notOK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1122\" y=\"-119\">tid:=-1</label>\r\n" + 
				"			<nail x=\"-1011\" y=\"-136\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id10\"/>\r\n" + 
				"			<target ref=\"id13\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-289\" y=\"-34\">OKsb[switches[id]]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-289\" y=\"-17\">updateConnected()</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id11\"/>\r\n" + 
				"			<target ref=\"id13\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-314\" y=\"-153\">result == 1</label>\r\n" + 
				"			<nail x=\"-510\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-280\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-136\" y=\"-136\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id15\"/>\r\n" + 
				"			<target ref=\"id9\"/>\r\n" + 
				"			<label kind=\"select\" x=\"-1130\" y=\"-17\">i : int[0,NT-1]</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1130\" y=\"0\">pass[id][i]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1130\" y=\"17\">result:=clear(i),\r\n" + 
				"tid:=i</label>\r\n" + 
				"			<nail x=\"-918\" y=\"-51\"/>\r\n" + 
				"			<nail x=\"-1011\" y=\"0\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id11\"/>\r\n" + 
				"			<target ref=\"id10\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-476\" y=\"-68\">result == 2</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-476\" y=\"-51\">switchh[switches[id]]!</label>\r\n" + 
				"			<nail x=\"-331\" y=\"-34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id14\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-34\">result==0</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-17\">notOK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-782\" y=\"0\">tid:=-1</label>\r\n" + 
				"			<nail x=\"-816\" y=\"-34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id12\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-807\" y=\"-289\">!result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-807\" y=\"-272\">notOK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-807\" y=\"-255\">tid := -1</label>\r\n" + 
				"			<nail x=\"-816\" y=\"-272\"/>\r\n" + 
				"			<nail x=\"-816\" y=\"-136\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id12\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-926\" y=\"-238\">result</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-221\">OK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-943\" y=\"-204\">tid := -1</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id15\"/>\r\n" + 
				"			<target ref=\"id14\"/>\r\n" + 
				"			<label kind=\"select\" x=\"-773\" y=\"-221\">i : int[0,NT-1],\r\n" + 
				"j : int[0,NSEG-1],\r\n" + 
				"k : int[0,NSEG-1]</label>\r\n" + 
				"			<label kind=\"guard\" x=\"-773\" y=\"-144\">lockedBy == -1</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-773\" y=\"-127\">reqLock[id][i][j][k]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-773\" y=\"-110\">result:=lock(i,j,k),\r\n" + 
				"tid:=i</label>\r\n" + 
				"			<nail x=\"-748\" y=\"-68\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id15\"/>\r\n" + 
				"			<target ref=\"id12\"/>\r\n" + 
				"			<label kind=\"select\" x=\"-1088\" y=\"-263\">i : int[0,NT-1],\r\n" + 
				"j : int[0,NSEG-1]</label>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-1088\" y=\"-229\">resSeg[id][i][j]?</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-1088\" y=\"-212\">result:=reserve(i,j),\r\n" + 
				"tid:=i</label>\r\n" + 
				"			<nail x=\"-952\" y=\"-136\"/>\r\n" + 
				"			<nail x=\"-952\" y=\"-272\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id13\"/>\r\n" + 
				"			<target ref=\"id15\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"-680\" y=\"34\">OK[tid]!</label>\r\n" + 
				"			<label kind=\"assignment\" x=\"-680\" y=\"51\">lockedBy:=tid,\r\n" + 
				"tid:=-1</label>\r\n" + 
				"			<nail x=\"-136\" y=\"34\"/>\r\n" + 
				"			<nail x=\"-884\" y=\"34\"/>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id14\"/>\r\n" + 
				"			<target ref=\"id11\"/>\r\n" + 
				"			<label kind=\"guard\" x=\"-654\" y=\"-34\">result &gt; 0</label>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<template>\r\n" + 
				"		<name>Point</name>\r\n" + 
				"		<parameter>SW_id id</parameter>\r\n" + 
				"		<location id=\"id16\" x=\"136\" y=\"68\">\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id17\" x=\"136\" y=\"-68\">\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id18\" x=\"280\" y=\"0\">\r\n" + 
				"			<name x=\"270\" y=\"-30\">Minus</name>\r\n" + 
				"		</location>\r\n" + 
				"		<location id=\"id19\" x=\"0\" y=\"0\">\r\n" + 
				"			<name x=\"-10\" y=\"-30\">Plus</name>\r\n" + 
				"		</location>\r\n" + 
				"		<init ref=\"id19\"/>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id19\"/>\r\n" + 
				"			<target ref=\"id17\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"0\" y=\"-59\">switchh[id]?</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id18\"/>\r\n" + 
				"			<target ref=\"id16\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"204\" y=\"42\">switchh[id]?</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id17\"/>\r\n" + 
				"			<target ref=\"id18\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"204\" y=\"-59\">OKsb[id]!</label>\r\n" + 
				"		</transition>\r\n" + 
				"		<transition>\r\n" + 
				"			<source ref=\"id16\"/>\r\n" + 
				"			<target ref=\"id19\"/>\r\n" + 
				"			<label kind=\"synchronisation\" x=\"0\" y=\"42\">OKsb[id]!</label>\r\n" + 
				"		</transition>\r\n" + 
				"	</template>\r\n" + 
				"	<system>system TCC, CB, Point;</system>\r\n" + 
				"	<queries>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:CB_id) (not(CB(i).Initial) &amp;&amp; not(CB(i).Switching) &amp;&amp; switches[i] != -1) imply (Point(switches[i]).Plus &amp;&amp; CB(i).connected == CBs[i][1]) || (Point(switches[i]).Minus &amp;&amp; CB(i).connected == CBs[i][2])\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>The local state information about connected segments of a switch box is consistent with the actual state of the switch.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (TCC(i).CrossingPoint imply (CB(TCC(i).nextSB).connected == TCC(i).curSeg &amp;&amp; CB(TCC(i).nextSB).stem == TCC(i).nextSeg) || (CB(TCC(i).nextSB).stem == TCC(i).curSeg &amp;&amp; CB(TCC(i).nextSB).connected == TCC(i).nextSeg))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train can only cross a point if the point's position is consistent with the segment that the trains is entering.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (TCC(i).CrossingPoint imply switches[TCC(i).nextSB] == -1 || Point(switches[TCC(i).nextSB]).Plus || Point(switches[TCC(i).nextSB]).Minus)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No derailment.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) (not(TCC(i).Initial) imply (CBs[TCC(i).nextSB][0] == TCC(i).curSeg imply (CBs[TCC(i).nextSB][1] == TCC(i).nextSeg || CBs[TCC(i).nextSB][2] == TCC(i).nextSeg)) &amp;&amp; (CBs[TCC(i).nextSB][0] == TCC(i).nextSeg imply (CBs[TCC(i).nextSB][1] == TCC(i).curSeg || CBs[TCC(i).nextSB][2] == TCC(i).curSeg)))\r\n" + 
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
				"	</queries>\r\n" + 
				"</nta>\r\n";
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
