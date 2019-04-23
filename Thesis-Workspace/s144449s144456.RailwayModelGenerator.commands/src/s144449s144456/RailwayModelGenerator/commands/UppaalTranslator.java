package s144449s144456.RailwayModelGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.eclipse.emf.common.util.EList;

import network.*;

public abstract class UppaalTranslator extends Translator {
	protected abstract String computeTrainModel();
	
	protected String computeCBModel() {
		return "<template>\n" + 
				"		<name>CB</name>\n" + 
				"		<parameter>cB_id id</parameter>\n" + 
				"		<declaration>segV_id segments[3];\n" + 
				"segV_id connected =-1;\n" + 
				"pV_id point = -1;\n" + 
				"\n" + 
				"tV_id res[3] = {-1, -1, -1};\n" + 
				"int[-1,2] result = -1;\n" + 
				"tV_id tid = -1;\n" + 
				"tV_id lockedBy = -1;\n" + 
				"\n" + 
				"const int ERROR = 0;\n" + 
				"const int NOSWITCH = 1;\n" + 
				"const int DOSWITCH = 2;\n" + 
				"\n" + 
				"void initialize() {\n" + 
				"    segments = cBs[id];\n" + 
				"    point = points[id];\n" + 
				"    connected = segments[1];\n" + 
				"\n" + 
				"    if(point != -1 &amp;&amp; !pointInPlus[point]){\n" + 
				"        connected = segments[2];\n" + 
				"    }\n" + 
				"\n" + 
				"    for(i : t_id) {\n" + 
				"        if (initialRes[i].cb == id) {\n" + 
				"            seg_id s = initialRes[i].seg;\n" + 
				"            if(s == segments[0]){\n" + 
				"                res[0] = i;\n" + 
				"            } else if (s == segments[1]){\n" + 
				"                res[1] = i;\n" + 
				"            } else {\n" + 
				"                res[2] = i;\n" + 
				"            }\n" + 
				"        }\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"int[-1,2] checkSegment(seg_id sid) {\n" + 
				"    for(i:int[0,2]) {\n" + 
				"        if(segments[i] == sid &amp;&amp; res[i] == -1) {\n" + 
				"            return i;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return -1;\n" + 
				"}\n" + 
				"\n" + 
				"int[0,2] checkLock(seg_id s1, seg_id s2){\n" + 
				"  if(lockedBy == -1 &amp;&amp; (segments[0] == s1 &amp;&amp; exists(i:int[1,2]) segments[i] == s2) || (segments[0] == s2 &amp;&amp; exists(i:int[1,2]) segments[i] == s1)){\n" + 
				"    if ((s1 == segments[0] &amp;&amp; s2 == connected) || (s2 == segments[0] &amp;&amp; s1 == connected)){\n" + 
				"        return NOSWITCH;\n" + 
				"    } else\n" + 
				"        return DOSWITCH;\n" + 
				"    }\n" + 
				"    return ERROR;\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				"void clear(){\n" + 
				"     lockedBy = -1;\n" + 
				"\n" + 
				"     res[0] = -1;\n" + 
				"     if(connected == segments[1]) {\n" + 
				"        res[1] = -1;\n" + 
				"     } else {\n" + 
				"        res[2] = -1;\n" + 
				"     }\n" + 
				"}\n" + 
				"\n" + 
				"void updateConnected(){\n" + 
				"    if(connected == segments[1]){\n" + 
				"        connected = segments[2];\n" + 
				"    } else {\n" + 
				"        connected = segments[1];\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"void resetVariables(){\n" + 
				"    tid = -1;\n" + 
				"    result = -1;\n" + 
				"}\n" + 
				"\n" + 
				"void updateLockInfo(){\n" + 
				"    lockedBy = tid;\n" + 
				"    resetVariables();\n" + 
				"}\n" + 
				"\n" + 
				"void updateResInfo(){\n" + 
				"    res[result]=tid;\n" + 
				"    resetVariables();\n" + 
				"}\n" + 
				"\n" + 
				"bool isWellFormed(){\n" + 
				"    return cBIsWellFormed(id) &amp;&amp; pointIsWellFormed(id);\n" + 
				"}</declaration>\n" + 
				"		<location id=\"id8\" x=\"-1045\" y=\"51\">\n" + 
				"			<name x=\"-1096\" y=\"42\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id9\" x=\"-1139\" y=\"-68\">\n" + 
				"			<name x=\"-1164\" y=\"-102\">Passing</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id10\" x=\"-356\" y=\"-34\">\n" + 
				"			<name x=\"-366\" y=\"-68\">Switching</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id11\" x=\"-884\" y=\"-306\">\n" + 
				"			<name x=\"-940\" y=\"-338\">SegmentChecked</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id12\" x=\"-170\" y=\"-34\">\n" + 
				"			<name x=\"-154\" y=\"-42\">Switched</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id13\" x=\"-646\" y=\"-34\">\n" + 
				"			<name x=\"-680\" y=\"-17\">LockChecked</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id14\" x=\"-884\" y=\"-68\">\n" + 
				"			<name x=\"-858\" y=\"-93\">Idle</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id8\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1011\" y=\"25\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1011\" y=\"42\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1011\" y=\"59\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id9\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1079\" y=\"-136\">passed[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1079\" y=\"-119\">clear()</label>\n" + 
				"			<nail x=\"-1020\" y=\"-102\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id10\"/>\n" + 
				"			<target ref=\"id12\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-331\" y=\"-34\">OKp[point]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-331\" y=\"-17\">updateConnected()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id13\"/>\n" + 
				"			<target ref=\"id12\"/>\n" + 
				"			<label kind=\"guard\" x=\"-458\" y=\"-153\">result == NOSWITCH</label>\n" + 
				"			<nail x=\"-544\" y=\"-136\"/>\n" + 
				"			<nail x=\"-322\" y=\"-136\"/>\n" + 
				"			<nail x=\"-272\" y=\"-136\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id14\"/>\n" + 
				"			<target ref=\"id9\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1105\" y=\"-34\">pass[id]?</label>\n" + 
				"			<nail x=\"-952\" y=\"-51\"/>\n" + 
				"			<nail x=\"-1020\" y=\"-34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id13\"/>\n" + 
				"			<target ref=\"id10\"/>\n" + 
				"			<label kind=\"guard\" x=\"-569\" y=\"-68\">result == DOSWITCH</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-569\" y=\"-51\">switchPoint[point]!</label>\n" + 
				"			<nail x=\"-373\" y=\"-34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id13\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-34\">result == ERROR</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-17\">notOK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-782\" y=\"0\">resetVariables()</label>\n" + 
				"			<nail x=\"-816\" y=\"-34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id11\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"guard\" x=\"-807\" y=\"-289\">result == -1</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-807\" y=\"-272\">notOK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-807\" y=\"-255\">resetVariables()</label>\n" + 
				"			<nail x=\"-816\" y=\"-272\"/>\n" + 
				"			<nail x=\"-816\" y=\"-136\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id11\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"guard\" x=\"-943\" y=\"-238\">result &gt; -1</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-221\">OK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-943\" y=\"-204\">updateResInfo()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id14\"/>\n" + 
				"			<target ref=\"id13\"/>\n" + 
				"			<label kind=\"select\" x=\"-790\" y=\"-187\">i : t_id,\n" + 
				"j : seg_id,\n" + 
				"k : seg_id</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-790\" y=\"-136\">reqLock[id][i][j][k]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-790\" y=\"-119\">result = checkLock(j,k),\n" + 
				"tid = i</label>\n" + 
				"			<nail x=\"-714\" y=\"-68\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id14\"/>\n" + 
				"			<target ref=\"id11\"/>\n" + 
				"			<label kind=\"select\" x=\"-1173\" y=\"-263\">i : t_id,\n" + 
				"j : seg_id</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1173\" y=\"-229\">reqSeg[id][i][j]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1173\" y=\"-212\">result = checkSegment(j),\n" + 
				"tid = i</label>\n" + 
				"			<nail x=\"-952\" y=\"-136\"/>\n" + 
				"			<nail x=\"-952\" y=\"-272\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id12\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-722\" y=\"34\">OK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-722\" y=\"51\">updateLockInfo()</label>\n" + 
				"			<nail x=\"-170\" y=\"34\"/>\n" + 
				"			<nail x=\"-884\" y=\"34\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}
	
	protected String computePointModel() {
		return "<template>\n" + 
				"		<name>Point</name>\n" + 
				"		<parameter>p_id id</parameter>\n" + 
				"		<location id=\"id15\" x=\"136\" y=\"-85\">\n" + 
				"			<name x=\"102\" y=\"-119\">SwitchingPM</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id16\" x=\"136\" y=\"153\">\n" + 
				"			<name x=\"127\" y=\"170\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id17\" x=\"136\" y=\"68\">\n" + 
				"			<name x=\"102\" y=\"34\">SwitchingMP</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id18\" x=\"340\" y=\"0\">\n" + 
				"			<name x=\"330\" y=\"-30\">Minus</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id19\" x=\"-68\" y=\"0\">\n" + 
				"			<name x=\"-78\" y=\"-30\">Plus</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id16\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id16\"/>\n" + 
				"			<target ref=\"id18\"/>\n" + 
				"			<label kind=\"guard\" x=\"238\" y=\"119\">!pointInPlus[id]</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"289\" y=\"136\">start?</label>\n" + 
				"			<nail x=\"340\" y=\"153\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id16\"/>\n" + 
				"			<target ref=\"id19\"/>\n" + 
				"			<label kind=\"guard\" x=\"-51\" y=\"119\">pointInPlus[id]</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-50\" y=\"136\">start?</label>\n" + 
				"			<nail x=\"-68\" y=\"153\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id19\"/>\n" + 
				"			<target ref=\"id15\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-42\" y=\"-68\">switchPoint[id]?</label>\n" + 
				"			<nail x=\"34\" y=\"-42\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id18\"/>\n" + 
				"			<target ref=\"id17\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"212\" y=\"42\">switchPoint[id]?</label>\n" + 
				"			<nail x=\"238\" y=\"34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id15\"/>\n" + 
				"			<target ref=\"id18\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"246\" y=\"-76\">OKp[id]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"246\" y=\"-59\">pointInPlus[id] = false</label>\n" + 
				"			<nail x=\"238\" y=\"-42\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id17\"/>\n" + 
				"			<target ref=\"id19\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-42\" y=\"42\">OKp[id]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-42\" y=\"59\">pointInPlus[id] = true</label>\n" + 
				"			<nail x=\"34\" y=\"34\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}
	
	private String computeInitializerModel() {
		return "<template>\n" + 
				"		<name>Initializer</name>\n" + 
				"		<location id=\"id6\" x=\"0\" y=\"136\">\n" + 
				"			<name x=\"8\" y=\"110\">Initialized</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id7\" x=\"0\" y=\"0\">\n" + 
				"			<name x=\"-10\" y=\"-34\">Uninitialized</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id7\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id7\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"8\" y=\"51\">start!</label>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}
	
	protected String computeDeclarations(Network n) {
		int routeLength = computeLongestRouteLength(n.getTrains());
		String sizesString = "const int NTRAIN = "+trainIDs.size()+";\n"+
							 "const int NCB = "+cbIDs.size()+";\n"+
							 "const int NPOINT = "+pointIDs.size()+";\n"+
					  		 "const int NSEG = "+segIDs.size()+";\n"+ 
							 "const int NROUTELENGTH ="+ routeLength +";\n\n";
		
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
							"    seg_id seg;\r\n" + 
							"} reservation;\n\n";
		
		//Limits
		String limitsString = "const int[1,NCB] lockLimit = "+n.getLockLimit()+";\n"+
							  "const int[1,NSEG] resLimit = "+n.getReserveLimit()+";\n";
		
		//Route segments
		
		String routesString = "const segV_id segRoutes[NTRAIN][NROUTELENGTH] = {";
		for(int i = 0; i < n.getTrains().size()-1; i++) {
			routesString += trainRoute(n, n.getTrains().get(i), routeLength)+", ";
		}
		routesString += trainRoute(n, n.getTrains().get(n.getTrains().size()-1), routeLength)+"};\n";

		//Route control boxes
		String cbsString= "const cBV_id boxRoutes[NTRAIN][NROUTELENGTH+1] = {";
		for(int i = 0; i < n.getTrains().size()-1; i++) {
			cbsString += trainBoxes(n.getTrains().get(i), routeLength + 1) + ", ";
		}
		cbsString += trainBoxes(n.getTrains().get(n.getTrains().size()-1), routeLength + 1)+"};\n";
					
		
		String cbDetailsString = "const segV_id cBs[NCB][3] = {";
		for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
			cbDetailsString += cbsDetails(n.getControlBoxes().get(i))+", ";
		}
		cbDetailsString += cbsDetails(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
		
		//Initial reservations
		String initRes = "const reservation initialRes[NTRAIN] = {";
		for(int i = 0; i < n.getTrains().size()-1; i++) {
			int[] res = getRes(n.getTrains().get(i));
			initRes += "{"+res[0]+", "+res[1]+"},";
		}
		int[] res = getRes(n.getTrains().get(n.getTrains().size()-1));
		initRes += "{"+res[0]+", "+res[1]+"}};\n";
		
		//Points
		String pointsString = "const pV_id points[NCB] = {";
		for(int i = 0; i < n.getControlBoxes().size()-1; i++) {
			ControlBox cb = n.getControlBoxes().get(i);
			pointsString += pointID(cb)+", ";
		} 
		pointsString += pointID(n.getControlBoxes().get(n.getControlBoxes().size()-1))+"};\n";
		
		String pointSettingsString = "bool pointInPlus[NPOINT] = {";
		pointSettingsString += (pointIDs.size() >= 1) ? "true" : "";
		for(int i = 1; i < pointIDs.size(); i++) {
			pointSettingsString += ", true";
		}
		pointSettingsString += "};\n\n";
		
		return "<declaration>\n" + sizesString + 
				typesString + limitsString + routesString + 
				cbsString + cbDetailsString + initRes + 
				pointsString + pointSettingsString + 
				generateChannels() + 
				"int nextSegment(cB_id cb, seg_id s){\n" + 
				"    int s1 = cBs[cb][0];\n" + 
				"    int s2 = cBs[cb][1];\n" + 
				"    if(points[cb] &gt; -1 &amp;&amp; !pointInPlus[points[cb]]){\n" + 
				"        s2 = cBs[cb][2];\n" + 
				"    }\n" + 
				"\n" + 
				"    if(s == s1){\n" + 
				"        return s2;\n" + 
				"    } else {\n" + 
				"        return s1;\n" + 
				"    }    \n" + 
				"}\n" + 
				"\n" + 
				"////////////////////////////////////\n" + 
				"//Well-formedness Functions\n" + 
				"bool initialResIsConsistent(t_id id){\n" + 
				"    return initialRes[id].cb == boxRoutes[id][1] &amp;&amp; initialRes[id].seg == segRoutes[id][0];\n" + 
				"}\n" + 
				"\n" + 
				"bool reservationIsWellFormed(reservation res){\n" + 
				"    return cBs[res.cb][0] == res.seg || cBs[res.cb][1] == res.seg || cBs[res.cb][2] == res.seg;\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				"bool sharesSegmentS(cB_id i, cB_id j, seg_id s){\n" + 
				"    return  (i != j) &amp;&amp;\n" + 
				"            (cBs[i][0] == s || cBs[i][1] == s || cBs[i][2] == s) &amp;&amp; \n" + 
				"            (cBs[j][0] == s || cBs[j][1] == s || cBs[j][2] == s);\n" + 
				"}\n" + 
				"\n" + 
				"bool routesAreConsistent(t_id id){\n" + 
				"    cBV_id bRoute[NROUTELENGTH+1] = boxRoutes[id];\n" + 
				"    segV_id sRoute[NROUTELENGTH] = segRoutes[id];\n" + 
				"\n" + 
				"    for(i:int[0,NROUTELENGTH-1]){\n" + 
				"        if((bRoute[i+1] != -1) == (sRoute[i] == -1)){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"        if(bRoute[i+1] != -1 &amp;&amp; !sharesSegmentS(bRoute[i], bRoute[i+1], sRoute[i])){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return true; \n" + 
				"}\n" + 
				"\n" + 
				"bool sharesSegment(cB_id i, cB_id j){\n" + 
				"    return (i != j) &amp;&amp;\n" + 
				"            ((cBs[i][0] != -1 &amp;&amp; (cBs[i][0] == cBs[j][0] || cBs[i][0] == cBs[j][1] || cBs[i][0] == cBs[j][2])) ||\n" + 
				"            (cBs[i][1] != -1 &amp;&amp; (cBs[i][1] == cBs[j][0] || cBs[i][1] == cBs[j][1] || cBs[i][1] == cBs[j][2])) ||\n" + 
				"            (cBs[i][2] != -1 &amp;&amp; (cBs[i][2] == cBs[j][0] || cBs[i][2] == cBs[j][1] || cBs[i][2] == cBs[j][2])));\n" + 
				"}\n" + 
				"\n" + 
				"bool boxRouteIsWellFormed(cBV_id route[NROUTELENGTH+1]){\n" + 
				"    for(i:int[0,NROUTELENGTH-1]){\n" + 
				"        if(route[i] == -1 &amp;&amp; route[i+1] != -1){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"        if(route[i+1] != -1 &amp;&amp; !sharesSegment(route[i], route[i+1])){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"\n" + 
				"    }\n" + 
				"    return true; \n" + 
				"}\n" + 
				"bool canConnect(seg_id s1, seg_id s2){\n" + 
				"    for(i:cB_id){\n" + 
				"        if(cBs[i][0] == s1 &amp;&amp; (cBs[i][1] == s2 || cBs[i][2] == s2)){\n" + 
				"            return true;\n" + 
				"        }\n" + 
				"        if (cBs[i][0] == s2 &amp;&amp; (cBs[i][1] == s1 || cBs[i][2] == s1)){\n" + 
				"            return true;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return false;   \n" + 
				"}\n" + 
				"\n" + 
				"bool segRouteIsWellFormed(segV_id route[NROUTELENGTH]){\n" + 
				"    int i = 0;\n" + 
				"    if(route[0] == -1){\n" + 
				"        return false;\n" + 
				"    }\n" + 
				"    while(i &lt;= NROUTELENGTH - 2){\n" + 
				"        if(route[i] == -1 &amp;&amp; route[i+1] != -1){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"        if(route[i+1] != -1 &amp;&amp; !canConnect(route[i], route[i+1])){\n" + 
				"            return false;\n" + 
				"        }\n" + 
				"        i++;\n" + 
				"    }\n" + 
				"    return true; \n" + 
				"}\n" + 
				"\n" + 
				"int pointIsWellFormed(cBV_id id){\n" + 
				"    if(points[id] != -1){\n" + 
				"        for(i : cB_id){\n" + 
				"            if(i != id &amp;&amp; points[i] == points[id]){\n" + 
				"                return false;\n" + 
				"            }\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return (points[id] == -1) == (cBs[id][2] == -1);\n" + 
				"}\n" + 
				"\n" + 
				"int otherBoxes(cB_id id, segV_id s){\n" + 
				"    segV_id cB[3] = cBs[id];\n" + 
				"    int found = 0;\n" + 
				"    for(i:cB_id){\n" + 
				"        if(id != i &amp;&amp; (cBs[i][0] == s || cBs[i][1] == s || cBs[i][2] == s)){\n" + 
				"            found++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return found;\n" + 
				"}\n" + 
				"\n" + 
				"int sharedSegments(cB_id i, cB_id j){\r\n" + 
				"    int count = 0;\r\n" + 
				"    if (cBs[i][0] != -1 &amp;&amp; (cBs[i][0] == cBs[j][0] || cBs[i][0] == cBs[j][1] || cBs[i][0] == cBs[j][2])){\r\n" + 
				"        count++;\r\n" + 
				"    }\r\n" + 
				"    if (cBs[i][1] != -1 &amp;&amp; (cBs[i][1] == cBs[j][0] || cBs[i][1] == cBs[j][1] || cBs[i][1] == cBs[j][2])){\r\n" + 
				"        count++;\r\n" + 
				"    }\r\n" + 
				"    if (cBs[i][2] != -1 &amp;&amp; (cBs[i][2] == cBs[j][0] || cBs[i][2] == cBs[j][1] || cBs[i][2] == cBs[j][2])){\r\n" + 
				"         count++;\r\n" + 
				"    }\r\n" + 
				"    return count;\r\n" + 
				"}\r\n" + 
				"\r\n" +
				"bool cBIsWellFormed(cB_id id){\n" + 
				"    segV_id cB[3] = cBs[id];\n" + 
				"\n" + 
				"    //Invalid definitions\n" + 
				"    if(cB[0] == -1 || (cB[1] == -1 &amp;&amp; cB[2] != -1) || (cB[0] == -1 &amp;&amp; cB[1] == -1)){\n" + 
				"        return false;\n" + 
				"    }\n" + 
				"    if((cB[0] != -1 &amp;&amp; (cB[0] == cB[1] || cB[0] == cB[2])) ||\n" + 
				"        (cB[1] != -1 &amp;&amp; (cB[1] == cB[0] || cB[1] == cB[2])) ||\n" + 
				"        (cB[2] != -1 &amp;&amp; (cB[2] == cB[0] || cB[2] == cB[1]))){\n" + 
				"        return false;\n" + 
				"    }\n" + 
				"\n" + 
				"    //Case: []--x--\n" + 
				"    if(cB[1] == -1){\n" + 
				"        return otherBoxes(id, cB[0]) == 1;\n" + 
				"    }\n" + 
				"\n" + 
				"    //Case: --x--[]--y--\n" + 
				"    if(cB[2] == -1){\n" + 
				"        return otherBoxes(id, cB[0]) == 1 &amp;&amp; otherBoxes(id, cB[1]) == 1;\n" + 
				"    }\n" + 
				"\n" +  
				"    //Case: Switch box\r\n" + 
				"    for(i:cB_id){\r\n" + 
				"        if (i != id &amp;&amp; sharedSegments(i,id) &gt; 1 &amp;&amp; !(cBs[i][0] != cB[0] &amp;&amp; cBs[i][1] == cB[1] &amp;&amp; cBs[i][2] == cB[2])){                \r\n" + 
				"            return false;\r\n" + 
				"        } \r\n" + 
				"    }\r\n" +  
				"    return otherBoxes(id, cB[0]) == 1 &amp;&amp; otherBoxes(id, cB[1]) == 1 &amp;&amp; otherBoxes(id, cB[2]) == 1;\n" + 
				"}\n" + 
				"</declaration>\n";
	}
	
	protected String generateChannels() {
		return "//Channels\n" + 
				"chan reqSeg[NCB][NTRAIN][NSEG];\n" + 
				"chan reqLock[NCB][NTRAIN][NSEG][NSEG];\n" + 
				"chan OK[NTRAIN];\n" + 
				"chan notOK[NTRAIN];\n" + 
				"chan pass[NCB];\n" + 
				"chan passed[NCB];\n" + 
				"chan switchPoint[NPOINT];\n" + 
				"chan OKp[NPOINT];\n" + 
				"urgent broadcast chan start;\n\n";
	}

	protected String computeQueries() {
		return "<queries>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>MISCELLANEOUS\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>E&lt;&gt; forall(i:t_id) not(Train(i).Initial)\n" + 
				"			</formula>\n" + 
				"			<comment>Well-formedness of Train\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>E&lt;&gt; forall(i:cB_id) not(CB(i).Initial)\n" + 
				"			</formula>\n" + 
				"			<comment>Well-formedness of CB\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] !deadlock\n" + 
				"			</formula>\n" + 
				"			<comment>\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>LIVENESS AND SAFETY\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>E&lt;&gt; forall(i:t_id) Train(i).Arrived\n" + 
				"			</formula>\n" + 
				"			<comment>Liveness\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:t_id) Initializer.Initialized &amp;&amp; i != j imply\n" + 
				"	(Train(i).curSeg != Train(j).curSeg) &amp;&amp;\n" + 
				"	(Train(i).DoubleSegment imply Train(i).headSeg != Train(j).curSeg) &amp;&amp;\n" + 
				"	(Train(i).DoubleSegment &amp;&amp; Train(j).DoubleSegment imply Train(i).headSeg!= Train(j).headSeg)\n" + 
				"			</formula>\n" + 
				"			<comment>No collision\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:int[0,1]) Train(i).DoubleSegment imply\n" + 
				"	(Train(i).headSeg == CB(Train(i).boxes[Train(i).index+1]).segments[0] &amp;&amp; Train(i).curSeg == CB(Train(i).boxes[Train(i).index+1]).connected) ||\n" + 
				"	(Train(i).curSeg == CB(Train(i).boxes[Train(i).index+1]).segments[0] &amp;&amp; Train(i).headSeg == CB(Train(i).boxes[Train(i).index+1]).connected)\n" + 
				"			</formula>\n" + 
				"			<comment>No derailment:\n" + 
				"A train passing a point always moves between the connected segments\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[Train(i).boxes[Train(i).index+1]] != -1 imply\n" + 
				"	!Point(points[Train(i).boxes[Train(i).index+1]]).SwitchingPM &amp;&amp; !Point(points[Train(i).boxes[Train(i).index+1]]).SwitchingMP\n" + 
				"			</formula>\n" + 
				"			<comment>No derailment:\n" + 
				"A train is never in a critical section while the point there is switching\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>CONCISTENCY\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks == (sum(j:cB_id) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; points[Train(i).boxes[j]] &gt; -1))\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC's locks variable is always the true number of locks\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:p_id) (Point(i).Plus imply pointInPlus[i]) &amp;&amp; (Point(i).Minus imply !pointInPlus[i])\n" + 
				"			</formula>\n" + 
				"			<comment>Network array consistency:\n" + 
				"The pointInPlus array reflects the true state of all Points\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) points[i] &gt; -1 imply\n" + 
				"	(Point(points[i]).Plus imply CB(i).connected == cBs[i][1]) &amp;&amp; (Point(points[i]).Minus imply CB(i).connected == cBs[i][2])\n" + 
				"			</formula>\n" + 
				"			<comment>Point consistency:\n" + 
				"A CB's connected information is consistent with its Point's position\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).lockedBy &gt; -1 imply\n" + 
				"	(exists(k:cB_id) Train(CB(i).lockedBy).boxes[k] == i &amp;&amp; k &gt; Train(CB(i).lockedBy).index &amp;&amp; k &lt; Train(CB(i).lockedBy).lockIndex)\n" + 
				"			</formula>\n" + 
				"			<comment>Lock consistency:\n" + 
				"A CB's lock information is also reflected in the state space of the relevant TCC\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; Train(i).requiresLock[j] imply \n" + 
				"	CB(Train(i).boxes[j]).lockedBy == i)\n" + 
				"			</formula>\n" + 
				"			<comment>Lock consistency:\n" + 
				"A TCC's obtained locks are reflected in the relevant CBs\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:seg_id) (j &gt;= Train(i).index &amp;&amp; j &lt; Train(i).resSegIndex imply exists(l:int[0,2]) CB(Train(i).boxes[j+1]).segments[l] == Train(i).segments[j] &amp;&amp; CB(Train(i).boxes[j+1]).res[l] == i) &amp;&amp;\n" + 
				"		(j &gt; Train(i).index &amp;&amp; j &lt; Train(i).resCBIndex imply exists(l:int[0,2]) CB(Train(i).boxes[j]).segments[l] == Train(i).segments[j] &amp;&amp; CB(Train(i).boxes[j]).res[l] == i)\n" + 
				"			</formula>\n" + 
				"			<comment>Reservation consistency:\n" + 
				"All segment reservations obtained by a TCC are also saved in the state space of the relevant CBs\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2]) CB(i).res[j] &gt; -1 imply\n" + 
				"	exists(k:seg_id) Train(CB(i).res[j]).segments[k] == CB(i).segments[j] &amp;&amp; k &gt;= Train(CB(i).res[j]).index &amp;&amp;\n" + 
				"		(Train(CB(i).res[j]).resCBIndex == Train(CB(i).res[j]).resSegIndex imply (k &lt; Train(CB(i).res[j]).resSegIndex)) &amp;&amp;\n" + 
				"		(Train(CB(i).res[j]).resCBIndex != Train(CB(i).res[j]).resSegIndex imply (k &lt; Train(CB(i).res[j]).resCBIndex))\n" + 
				"			</formula>\n" + 
				"			<comment>Reservation consistency: \n" + 
				"All reservations at a CB are also saved in the state space of the relevant TCCs\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>PASS\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply \n" + 
				"	Train(i).index+1 &lt; Train(i).resSegIndex\n" + 
				"			</formula>\n" + 
				"			<comment>A train only enters a segment that is has the full reservation of\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply Train(i).boxes[Train(i).index+1] != Train(i).boxes[Train(i).routeLength]\n" + 
				"			</formula>\n" + 
				"			<comment>A train never passes the last control box in its route\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[Train(i).boxes[Train(i).index+1]] != -1 imply\n" + 
				"	CB(Train(i).boxes[Train(i).index+1]).lockedBy == i\n" + 
				"			</formula>\n" + 
				"			<comment>A train only passes a switch box if it has been locked for the train\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>LOCKS\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).Switched imply CB(i).lockedBy == -1\n" + 
				"			</formula>\n" + 
				"			<comment>A CB only returns acknowledgement for a lock request if its point was unlocked prior to the request\n" + 
				"A TCC only requests locks for switch boxes that it has not already obtained the lock at\n" + 
				"\n" + 
				"A lock is only ever given if it was available\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).Switched || CB(i).Switching) imply !(exists(j:t_id) (Train(j).DoubleSegment &amp;&amp; Train(j).boxes[Train(j).index+1] == i))\n" + 
				"			</formula>\n" + 
				"			<comment>A control box only switches if no train is in its critical section\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) (Train(i).Locking &amp;&amp; CB(j).Switched &amp;&amp; CB(j).tid == i) imply \n" + 
				"	(exists(k:int[1,2]) \n" + 
				"	(CB(j).segments[0] == Train(i).segments[Train(i).lockIndex-1] &amp;&amp; CB(j).segments[k] == Train(i).segments[Train(i).lockIndex]) ||\n" + 
				"	(CB(j).segments[0] == Train(i).segments[Train(i).lockIndex] &amp;&amp; CB(j).segments[k] == Train(i).segments[Train(i).lockIndex-1]))\n" + 
				"			</formula>\n" + 
				"			<comment>A CB only returns acknowledgement for a switch/lock request if the requested segments are its stem and one of its other segments\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).lockedBy != -1 imply \n" + 
				"	CB(i).res[0] == CB(i).lockedBy &amp;&amp; \n" + 
				"	exists(j:int[0,2]) CB(i).segments[j] == CB(i).connected &amp;&amp; CB(i).res[j] == CB(i).lockedBy\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC only has locks for CB's that it has the stem reservation at and one other segment reservation\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).lockedBy != -1) imply (exists(j:cB_id) Train(CB(i).lockedBy).boxes[j] == i)\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC only requests locks at switch boxes on its route\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks &lt;= lockLimit\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC never has more locks than allowed\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>RESERVATIONS\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) \n" + 
				"	(Train(i).Reserving &amp;&amp; CB(j).SegmentChecked &amp;&amp; CB(j).tid == i &amp;&amp; CB(j).result &gt; -1) imply \n" + 
				"	CB(j).res[CB(j).result] == -1\n" + 
				"			</formula>\n" + 
				"			<comment>A CB only returns acknowledgement for reservation requests of available segments\n" + 
				"A TCC only reserves segments that it does not already have a reservation for\n" + 
				"\n" + 
				"A reservation is only ever given if it was available\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) \n" + 
				"	(Train(i).Reserving &amp;&amp; CB(j).SegmentChecked &amp;&amp; CB(j).tid == i &amp;&amp; CB(j).result &gt; -1) imply \n" + 
				"	(exists(k:int[0,2]) CB(j).segments[k] == Train(i).segments[Train(i).resSegIndex])\n" + 
				"			</formula>\n" + 
				"			<comment>A control box only returns acknowledgement for reservations involving segments that it is associated with\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2]) \n" + 
				"	CB(i).res[j] != -1 imply \n" + 
				"	exists(k:cB_id) Train(CB(i).res[j]).boxes[k] == i\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC only reserves at control boxes on its route\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2])\n" + 
				"	CB(i).res[j] != -1 imply \n" + 
				"	exists(k:seg_id) Train(CB(i).res[j]).segments[k] == CB(i).segments[j]\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC only reserves segments on its route\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).resSegIndex - 1 - Train(i).index &lt;= resLimit\n" + 
				"			</formula>\n" + 
				"			<comment>A TCC never has more reservations than allowed\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"	</queries>\n";
	}
	
	@Override
	protected String generateCode(Network n) {

		if(n != null) {
			String result =  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
					"<!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat System 1.1//EN' 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_2.dtd'>\n" + 
					"<nta>\n" + computeDeclarations(n) + computeTrainModel() +  
					computeInitializerModel() + computeCBModel() + computePointModel() +
					"<system>system Initializer, Train, CB, Point;</system>\n" + 
					computeQueries() + 
					"</nta>\n";

			//Generate file
			PrintWriter writer;
			try {
				writer = new PrintWriter(n.getName()+"_"+getFileNameDetails()+".xml", "UTF-8");
				writer.println(result);
				writer.close();
				return "Model file successfully generated.";
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "An error occurred";
	}

	protected abstract String getFileNameDetails();

	private int computeLongestRouteLength(EList<Train> trains) {
		int routeLength = 0;
		for(Train train : trains) {
			if (train.getRoute().size() > routeLength) {
				routeLength = train.getRoute().size();
			}
		}
		return routeLength;
	}
	
	private boolean isSegmentConnectedToControlBox(ControlBox box, Segment seg) {
		boolean found = false;
		for(int i = 0; i < controlBoxSegments.get(box).length; i++) {
			found = found || controlBoxSegments.get(box)[i] == seg;
		}
		return found;
	}
	
	private ControlBox findConnectingBox(Segment s1, Segment s2) {
		ControlBox box = null;
		for(ControlBox b : controlBoxSegments.keySet()) {
			if (isSegmentConnectedToControlBox(b, s1) && isSegmentConnectedToControlBox(b, s2)) {
				box = b;
			}
		}
		return box;
	}
	
	private ControlBox findEdgeControlBox(Segment edge, Segment neighbour) {
		ControlBox box = null;
		for(ControlBox b : controlBoxSegments.keySet()) {
			if (isSegmentConnectedToControlBox(b, edge) && !(isSegmentConnectedToControlBox(b, neighbour))) {
				box = b;
			}
		}
		return box;
	}
	
	private String trainBoxes(Train t, int routeLength) {
		String res = "{";
		res += cbIDs.get(findEdgeControlBox(t.getRoute().get(0), t.getRoute().get(1)));
		int i = 0;
		for(; i < t.getRoute().size()-1; i++) {
			res += ", " + cbIDs.get(findConnectingBox(t.getRoute().get(i), t.getRoute().get(i+1)));
		}
		res += ", " + cbIDs.get(findEdgeControlBox(t.getRoute().get(t.getRoute().size()-1), 
				t.getRoute().get(t.getRoute().size()-2)));
		i+=2;
		for(; i < routeLength; i++) {
			res += ", -1";
		}
		return res + "}";
	}

	private int[] getRes(Train t) {
		Segment s1 = t.getRoute().get(0);
		Segment s2 = t.getRoute().get(1);
		ControlBox box = findConnectingBox(s1, s2);
		int[] res = {cbIDs.get(box), segIDs.get(s1)};
		return res;
	}

	private String pointID(ControlBox cb) {
		return Integer.toString(pointIDs.getOrDefault(cb, -1));
	}

	
	private String cbsDetails(ControlBox cb) {
		String cbsDetails = "{";
		cbsDetails += segIDs.get(controlBoxSegments.get(cb)[0]);
		for(int i = 1; i < controlBoxSegments.get(cb).length; i++) {
			cbsDetails += ", " + segIDs.getOrDefault(controlBoxSegments.get(cb)[i], -1) ;
		}
		return cbsDetails+"}";
	}

	private String trainRoute(Network n, Train t, int routeLength) {
		String routesString = "{";
		for(int j = 0; j < routeLength-1; j++) {
			if (j < t.getRoute().size()) {
				routesString += segIDs.get(t.getRoute().get(j))+",";
			} else {
				routesString += "-1,";
			}
		}
		if(t.getRoute().size() < routeLength) {
			routesString += "-1}";
		} else {
			routesString += segIDs.get(t.getRoute().get(t.getRoute().size()-1))+"}";
		}
		return routesString;
	}
}