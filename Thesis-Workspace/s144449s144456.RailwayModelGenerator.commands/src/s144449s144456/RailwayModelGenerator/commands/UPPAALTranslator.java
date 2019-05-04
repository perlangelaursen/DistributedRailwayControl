package s144449s144456.RailwayModelGenerator.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.eclipse.emf.common.util.EList;

import network.*;

public class UPPAALTranslator extends Translator {
	
	@Override
	protected String generateCode(Network n) {
		if(n != null) {
			String result =  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
					"<!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat System 1.1//EN' 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_2.dtd'>\n" + 
					"<nta>" +
					generateDeclarations(n) + 
					getTrainModel() +
					getInitializerModel() + 
					getCBModel() +
					getPointModel() +
					"<system>system Initializer, Train, CB, Point;</system>\n" + 
					getQueries() + 
					"</nta>\n";

			return result;
		}
		return null;
	}

	protected String getTrainModel() {
		return "<template>\n" + 
				"		<name x=\"5\" y=\"5\">Train</name>\n" + 
				"		<parameter>t_id id</parameter>\n" + 
				"		<declaration>segV_id segments[NROUTELENGTH];\n" + 
				"cBV_id boxes[NROUTELENGTH+1];\n" + 
				"\n" + 
				"int[0,NROUTELENGTH] routeLength;\n" + 
				"segV_id curSeg;\n" + 
				"\n" + 
				"bool requiresLock[NROUTELENGTH+1];\n" + 
				"\n" + 
				"cBRoute_i lockIndex = 1;\n" + 
				"segRoute_i index = 0;\n" + 
				"\n" + 
				"int[0,1] resBit = 0;\n" + 
				"cBRoute_i resCBIndex = 1;\n" + 
				"cBRoute_i resSegIndex = 0;\n" + 
				"\n" + 
				"segV_id headSeg = -1;\n" + 
				"cB_id locks = 0;\n" + 
				"\n" + 
				"void updateLockIndex(){\n" + 
				"    while(lockIndex &lt; NROUTELENGTH &amp;&amp; !requiresLock[lockIndex]){\n" + 
				"        lockIndex++;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"void initialize() {\n" + 
				"    //Segments\n" + 
				"    for(i : segRoute_i) {\n" + 
				"        segments[i] = segRoutes[id][i];\n" + 
				"        if(segments[i]&gt;-1) {\n" + 
				"            routeLength++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    curSeg = segments[0];\n" + 
				"\n" + 
				"    //Control boxes\n" + 
				"    for(i : cBRoute_i) {\n" + 
				"        boxes[i] = boxRoutes[id][i];\n" + 
				"        if(boxes[i] &gt; -1){\n" + 
				"            requiresLock[i] = points[boxes[i]] &gt; -1;\n" + 
				"        }\n" + 
				"    }\n" + 
				"\n" + 
				"    //Locks and reservations\n" + 
				"    resSegIndex = 1;\n" + 
				"    updateLockIndex();\n" + 
				"}\n" + 
				"\n" + 
				"bool possibleToLock() {\n" + 
				"    return lockIndex &lt; routeLength &amp;&amp; locks &lt; lockLimit &amp;&amp; ((resBit == 0 &amp;&amp; resSegIndex &gt; lockIndex) || (resBit == 1 &amp;&amp; resSegIndex &gt;= lockIndex));\n" + 
				"}\n" + 
				"\n" + 
				"bool hasArrived() {\n" + 
				"    return index == routeLength-1;\n" + 
				"}\n" + 
				"\n" + 
				"bool possibleToReserve() {\n" + 
				"    return resSegIndex &lt; routeLength &amp;&amp; resSegIndex - 1 - index &lt; resLimit;\n" + 
				"}\n" + 
				"\n" + 
				"bool possibleToPass() {\n" + 
				"    return resSegIndex &gt; index + 1 &amp;&amp; lockIndex &gt; index + 1 &amp;&amp; index + 1 &lt; routeLength;\n" + 
				"}\n" + 
				"\n" + 
				"void updateResInfo(){\n" + 
				"    resBit = resBit^1;\n" + 
				"    resSegIndex = (resBit==0) ? resSegIndex + 1 : resSegIndex;\n" + 
				"    resCBIndex = (resBit==1) ? resCBIndex + 1 : resCBIndex;\n" + 
				"}\n" + 
				"\n" + 
				"void updateLocationInfo(){\n" + 
				"    curSeg = headSeg;\n" + 
				"    headSeg = -1;\n" + 
				"    if(requiresLock[index + 1]){\n" + 
				"        locks--;\n" + 
				"    }\n" + 
				"    index++;\n" + 
				"}\n" + 
				"\n" + 
				"void updateHeadInfo(){\n" + 
				"    headSeg = nextSegment(boxes[index+1], curSeg);\n" + 
				"}\n" + 
				"\n" + 
				"void updateLockInfo(){\n" + 
				"    locks++;\n" + 
				"    lockIndex++;\n" + 
				"    updateLockIndex();\n" + 
				"}\n" + 
				"\n" + 
				"bool isWellFormed(){\n" + 
				"	return segRouteIsWellFormed(segRoutes[id]) &amp;&amp;\n" + 
				"           boxRouteIsWellFormed(boxRoutes[id]) &amp;&amp; \n" + 
				"           routesAreConsistent(id) &amp;&amp; \n" + 
				"           reservationIsWellFormed(initialRes[id]) &amp;&amp; \n" + 
				"           initialResIsConsistent(id);\n" + 
				"}</declaration>\n" + 
				"		<location id=\"id0\" x=\"-340\" y=\"-1156\">\n" + 
				"			<name x=\"-323\" y=\"-1173\">DoubleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id1\" x=\"-1020\" y=\"-1156\">\n" + 
				"			<name x=\"-1030\" y=\"-1190\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id2\" x=\"-680\" y=\"-1156\">\n" + 
				"			<name x=\"-748\" y=\"-1181\">Arrived</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id3\" x=\"-340\" y=\"-748\">\n" + 
				"			<name x=\"-323\" y=\"-765\">Reserving</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id4\" x=\"-680\" y=\"-952\">\n" + 
				"			<name x=\"-807\" y=\"-969\">SingleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id5\" x=\"-1020\" y=\"-748\">\n" + 
				"			<name x=\"-1044\" y=\"-732\">Locking</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id1\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id1\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1028\" y=\"-1096\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1028\" y=\"-1079\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1028\" y=\"-1062\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-833\" y=\"-739\">notOK[id]?</label>\n" + 
				"			<nail x=\"-748\" y=\"-748\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-841\" y=\"-858\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-841\" y=\"-841\">updateLockInfo()</label>\n" + 
				"			<nail x=\"-850\" y=\"-850\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-323\" y=\"-875\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-323\" y=\"-858\">updateResInfo()</label>\n" + 
				"			<nail x=\"-340\" y=\"-884\"/>\n" + 
				"			<nail x=\"-510\" y=\"-918\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id2\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<nail x=\"-714\" y=\"-1224\"/>\n" + 
				"			<nail x=\"-646\" y=\"-1224\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id0\"/>\n" + 
				"			<label kind=\"guard\" x=\"-544\" y=\"-1207\">possibleToPass()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-544\" y=\"-1190\">pass[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-544\" y=\"-1173\">updateHeadInfo()</label>\n" + 
				"			<nail x=\"-578\" y=\"-1156\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"guard\" x=\"-765\" y=\"-1113\">hasArrived()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-501\" y=\"-867\">notOK[id]?</label>\n" + 
				"			<nail x=\"-552\" y=\"-875\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id0\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-450\" y=\"-994\">passed[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-450\" y=\"-977\">updateLocationInfo()</label>\n" + 
				"			<nail x=\"-340\" y=\"-1020\"/>\n" + 
				"			<nail x=\"-595\" y=\"-969\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id5\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1241\" y=\"-841\">possibleToLock()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1241\" y=\"-824\">reqLock[boxes[lockIndex]][id]\n" + 
				"[segments[lockIndex-1]]\n" + 
				"[segments[lockIndex]]!</label>\n" + 
				"			<nail x=\"-1020\" y=\"-884\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id3\"/>\n" + 
				"			<label kind=\"guard\" x=\"-612\" y=\"-740\">possibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-612\" y=\"-722\">reqSeg[boxes[resCBIndex]][id][segments[resSegIndex]]!</label>\n" + 
				"			<nail x=\"-612\" y=\"-748\"/>\n" + 
				"			<nail x=\"-527\" y=\"-748\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	};
	
	protected String getCBModel() {
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
	
	protected String getPointModel() {
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
	
	private String getInitializerModel() {
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
	
	private String generateDeclarations(Network n) {
		int NPOINT = (pointIDs.size() > 0) ? pointIDs.size() : 1;
		int routeLength = computeLongestRouteLength(n);
		String sizesString = "const int NTRAIN = "+trainIDs.size()+";\n"+
							 "const int NCB = "+cbIDs.size()+";\n"+
							 "const int NPOINT = "+NPOINT+";\n"+
					  		 "const int NSEG = "+segIDs.size()+";\n"+ 
							 "const int NROUTELENGTH = "+ routeLength +";\n\n";
		
		String typesString = "typedef int[0, NTRAIN-1] t_id;\n" + 
							"typedef int[0, NCB-1]  cB_id;\n" + 
							"typedef int[0, NPOINT-1] p_id;\n" + 
							"typedef int[0, NSEG-1] seg_id;\n" + 
							"typedef int[-1, NTRAIN-1] tV_id;\n" + 
							"typedef int[-1, NCB-1] cBV_id;\n" + 
							"typedef int[-1, NPOINT-1] pV_id;\n" + 
							"typedef int[-1, NSEG-1] segV_id;\n"+
							"typedef int[0, NROUTELENGTH] cBRoute_i;\n" + 
							"typedef int[0, NROUTELENGTH-1] segRoute_i;\r\n" +
							"typedef struct {\r\n" + 
							"    cB_id cb;\r\n" + 
							"    seg_id seg;\r\n" + 
							"} reservation;\n\n";
		
		//Limits
		String limitsString = "const int[1,NCB] lockLimit = "+n.getLockLimit()+";\n"+
							  "const int[1,NSEG] resLimit = "+n.getReserveLimit()+";\n";
		
		//Route segments	
		String segRouteString = "const segV_id segRoutes[NTRAIN][NROUTELENGTH] = {";
		for(Train t : n.getTrains()) {
			segRouteString += trainRoute(t, routeLength)+", ";
		}
		segRouteString = segRouteString.substring(0, segRouteString.length() - 2)+"};\n";
		
		//Route control boxes
		String cbRouteString= "const cBV_id boxRoutes[NTRAIN][NROUTELENGTH+1] = {";
		for(Train t : n.getTrains()) {
			cbRouteString += trainBoxes(t, routeLength + 1) + ", ";
		}
		cbRouteString = cbRouteString.substring(0, cbRouteString.length() - 2)+"};\n";				
		
		//Control box definitions
		String cbsString = "const segV_id cBs[NCB][3] = {";
		for(ControlBox cb : n.getControlBoxes()) {
			cbsString += cbsDetails(cb)+", ";
		}
		cbsString = cbsString.substring(0, cbsString.length() - 2)+"};\n";	
		
		//Initial reservations
		String initResString = "const reservation initialRes[NTRAIN] = {";
		for(Train t : n.getTrains()) {
			int[] res = getRes(t);
			initResString += "{"+res[0]+", "+res[1]+"}, ";
		}
		initResString = initResString.substring(0, initResString.length() - 2)+"};\n";
		
		//Points
		String pointsString = "const pV_id points[NCB] = {";
		for(ControlBox cb : n.getControlBoxes()) {
			pointsString += Integer.toString(pointIDs.getOrDefault(cb, -1))+", ";
		} 
		pointsString = pointsString.substring(0, pointsString.length() - 2)+"};\n";
		
		String pointSettingsString = "bool pointInPlus[NPOINT] = {";
		pointSettingsString += (pointIDs.size() > 0) ? "" : "true";
		for(ControlBox cb : n.getControlBoxes()) {
			if(cb instanceof SwitchBox) {
				SwitchBox sb = (SwitchBox) cb;
				pointSettingsString += (sb.getConnected() == PointSetting.PLUS)+", ";	
			}
		}
		if(pointIDs.size() > 0) {
			pointSettingsString = pointSettingsString.substring(0, pointSettingsString.length() - 2);
		}
		pointSettingsString += "};\n\n";
		
		return "<declaration>\n" + "//"+n.getName()+"\n" +  sizesString + 
				typesString + limitsString + segRouteString + 
				cbRouteString + cbsString + initResString + 
				pointsString + pointSettingsString + 
				getChannels() + getWellFormednessFunctions();
	}
	
	private String getWellFormednessFunctions() {
		return "int nextSegment(cB_id cb, seg_id s){\n" + 
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
				"////////////////////////////////////\r\n" + 
				"//Well-formedness Functions\r\n" + 
				"bool initialResIsConsistent(t_id id){\r\n" + 
				"    return initialRes[id].cb == boxRoutes[id][1] &amp;&amp; initialRes[id].seg == segRoutes[id][0];\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool reservationIsWellFormed(reservation res){\r\n" + 
				"    return cBs[res.cb][0] == res.seg || cBs[res.cb][1] == res.seg || cBs[res.cb][2] == res.seg;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"bool sharesSegmentS(cB_id i, cB_id j, seg_id s){\r\n" + 
				"    return  (i != j) &amp;&amp;\r\n" + 
				"            (cBs[i][0] == s || cBs[i][1] == s || cBs[i][2] == s) &amp;&amp; \r\n" + 
				"            (cBs[j][0] == s || cBs[j][1] == s || cBs[j][2] == s);\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool routesAreConsistent(t_id id){\r\n" + 
				"    cBV_id bRoute[NROUTELENGTH+1] = boxRoutes[id];\r\n" + 
				"    segV_id sRoute[NROUTELENGTH] = segRoutes[id];\r\n" + 
				"\r\n" + 
				"    for(i:int[0,NROUTELENGTH-1]){\r\n" + 
				"        if((bRoute[i+1] != -1) == (sRoute[i] == -1)){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"        if(bRoute[i+1] != -1 &amp;&amp; !sharesSegmentS(bRoute[i], bRoute[i+1], sRoute[i])){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    return true; \r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool sharesSegment(cB_id i, cB_id j){\r\n" + 
				"    return (i != j) &amp;&amp;\r\n" + 
				"            ((cBs[i][0] != -1 &amp;&amp; (cBs[i][0] == cBs[j][0] || cBs[i][0] == cBs[j][1] || cBs[i][0] == cBs[j][2])) ||\r\n" + 
				"            (cBs[i][1] != -1 &amp;&amp; (cBs[i][1] == cBs[j][0] || cBs[i][1] == cBs[j][1] || cBs[i][1] == cBs[j][2])) ||\r\n" + 
				"            (cBs[i][2] != -1 &amp;&amp; (cBs[i][2] == cBs[j][0] || cBs[i][2] == cBs[j][1] || cBs[i][2] == cBs[j][2])));\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool boxRouteIsWellFormed(cBV_id route[NROUTELENGTH+1]){\r\n" + 
				"    for(i:int[0,NROUTELENGTH-1]){\r\n" + 
				"        if(route[i] == -1 &amp;&amp; route[i+1] != -1){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"        if(route[i+1] != -1 &amp;&amp; !sharesSegment(route[i], route[i+1])){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"    }\r\n" + 
				"    return true; \r\n" + 
				"}\r\n" + 
				"bool canConnect(seg_id s1, seg_id s2){\r\n" + 
				"    for(i:cB_id){\r\n" + 
				"        if(cBs[i][0] == s1 &amp;&amp; (cBs[i][1] == s2 || cBs[i][2] == s2)){\r\n" + 
				"            return true;\r\n" + 
				"        }\r\n" + 
				"        if (cBs[i][0] == s2 &amp;&amp; (cBs[i][1] == s1 || cBs[i][2] == s1)){\r\n" + 
				"            return true;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    return false;   \r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"bool segRouteIsWellFormed(segV_id route[NROUTELENGTH]){\r\n" + 
				"    int i = 0;\r\n" + 
				"    if(route[0] == -1){\r\n" + 
				"        return false;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    for(i:segRoute_i){\r\n" + 
				"        for(j:segRoute_i){\r\n" + 
				"            if(j != i &amp;&amp; route[i] == route[j] &amp;&amp; route[i] != -1){\r\n" + 
				"                return false;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    while(i &lt;= NROUTELENGTH - 2){\r\n" + 
				"        if(route[i] == -1 &amp;&amp; route[i+1] != -1){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"        if(route[i+1] != -1 &amp;&amp; !canConnect(route[i], route[i+1])){\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"        i++;\r\n" + 
				"    }\r\n" + 
				"    return true; \r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int pointIsWellFormed(cBV_id id){\r\n" + 
				"    if(points[id] != -1){\r\n" + 
				"        for(i : cB_id){\r\n" + 
				"            if(i != id &amp;&amp; points[i] == points[id]){\r\n" + 
				"                return false;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    return (points[id] == -1) == (cBs[id][2] == -1);\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int otherBoxes(cB_id id, segV_id s){\r\n" + 
				"    segV_id cB[3] = cBs[id];\r\n" + 
				"    int found = 0;\r\n" + 
				"    for(i:cB_id){\r\n" + 
				"        if(id != i &amp;&amp; (cBs[i][0] == s || cBs[i][1] == s || cBs[i][2] == s)){\r\n" + 
				"            found++;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"    return found;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
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
				"bool cBIsWellFormed(cB_id id){\r\n" + 
				"    segV_id cB[3] = cBs[id];\r\n" + 
				"\r\n" + 
				"    //Invalid definitions\r\n" + 
				"    if(cB[0] == -1 || (cB[1] == -1 &amp;&amp; cB[2] != -1) || (cB[0] == -1 &amp;&amp; cB[1] == -1)){\r\n" + 
				"        return false;\r\n" + 
				"    }\r\n" + 
				"    if((cB[0] != -1 &amp;&amp; (cB[0] == cB[1] || cB[0] == cB[2])) ||\r\n" + 
				"        (cB[1] != -1 &amp;&amp; (cB[1] == cB[0] || cB[1] == cB[2])) ||\r\n" + 
				"        (cB[2] != -1 &amp;&amp; (cB[2] == cB[0] || cB[2] == cB[1]))){\r\n" + 
				"        return false;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //Case: []--x--\r\n" + 
				"    if(cB[1] == -1){\r\n" + 
				"        return otherBoxes(id, cB[0]) == 1;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //Case: --x--[]--y--\r\n" + 
				"    if(cB[2] == -1){\r\n" + 
				"        return otherBoxes(id, cB[0]) == 1 &amp;&amp; otherBoxes(id, cB[1]) == 1;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //Case: Switch box\r\n" + 
				"    for(i:cB_id){\r\n" + 
				"        if (i != id &amp;&amp; sharedSegments(i,id) &gt; 1 &amp;&amp; !(cBs[i][0] != cB[0] &amp;&amp; cBs[i][1] == cB[1] &amp;&amp; cBs[i][2] == cB[2])){                \r\n" + 
				"            return false;\r\n" + 
				"        } \r\n" + 
				"    }\r\n" + 
				"    return otherBoxes(id, cB[0]) == 1 &amp;&amp; otherBoxes(id, cB[1]) == 1 &amp;&amp; otherBoxes(id, cB[2]) == 1;\r\n" + 
				"}\r\n" +
				"</declaration>\n";
	}
	
	protected String getChannels() {
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

	protected String getQueries() {
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

	protected String getFileNameDetails() {
		return "UPPAAL";
	};

	private int computeLongestRouteLength(Network n) {
		int routeLength = 0;
		for(Train t : n.getTrains()) {
			if (t.getRoute().size() > routeLength) {
				routeLength = t.getRoute().size();
			}
		}
		return routeLength;
	}

	private int[] getRes(Train t) {
		Segment s1 = t.getRoute().get(0);
		ControlBox box = t.getBoxRoute().get(1);
		int[] res = {cbIDs.get(box), segIDs.get(s1)};
		return res;
	}
	
	private String cbsDetails(ControlBox cb) {
		String cbsDetails = "{";
		Segment[] cbSegments = controlBoxSegments.get(cb);
		cbsDetails += segIDs.get(cbSegments[0]);
		for(int i = 1; i < controlBoxSegments.get(cb).length; i++) {
			cbsDetails += ", " + segIDs.getOrDefault(cbSegments[i], -1) ;
		}
		return cbsDetails+"}";
	}
		
	private String trainBoxes(Train t, int routeLength) {
		String routeString = "{";
		for(int i = 0; i < routeLength; i++) {
			if (i < t.getBoxRoute().size()) {
				routeString += cbIDs.get(t.getBoxRoute().get(i))+",";
			} else {
				routeString += "-1,";
			}
		}
		routeString = routeString.substring(0, routeString.length() - 1)+"}";
		return routeString;
	}
	
	private String trainRoute(Train t, int routeLength) {
		String routeString = "{";
		for(int j = 0; j < routeLength; j++) {
			if (j < t.getRoute().size()) {
				routeString += segIDs.get(t.getRoute().get(j))+",";
			} else {
				routeString += "-1,";
			}
		}
		routeString = routeString.substring(0, routeString.length() - 1)+"}";
		return routeString;
	}
}