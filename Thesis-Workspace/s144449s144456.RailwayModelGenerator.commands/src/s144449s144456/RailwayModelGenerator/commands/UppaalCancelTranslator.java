package s144449s144456.RailwayModelGenerator.commands;

import network.Network;

public class UppaalCancelTranslator extends UppaalTranslator {

	@Override
	protected String computeTrainModel() {
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
				"cB_id lockIndex = 1;\n" + 
				"seg_id index = 0;\n" + 
				"\n" + 
				"int[0,1] resBit = 0;\n" + 
				"cB_id resCBIndex = 1;\n" + 
				"int[0,NSEG] resSegIndex = 0;\n" + 
				"\n" + 
				"segV_id headSeg = -1;\n" + 
				"cB_id locks = 0;\n" + 
				"\n" + 
				"\n" + 
				"void updateLockIndex(){\n" + 
				"    while(lockIndex &lt; NROUTELENGTH &amp;&amp; !requiresLock[lockIndex]){\n" + 
				"        lockIndex++;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"void initialize() {\n" + 
				"    //Segments\n" + 
				"    for(i : int[0,NROUTELENGTH-1]) {\n" + 
				"        segments[i] = segRoutes[id][i];\n" + 
				"        if(segments[i]&gt;-1) {\n" + 
				"            routeLength++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    curSeg = segments[0];\n" + 
				"\n" + 
				"    //Control boxes\n" + 
				"    for(i : int[0,NROUTELENGTH]) {\n" + 
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
				"bool possibleToCancel(){\n" + 
				"    return resCBIndex &gt; index + 1;\n" + 
				"}\n" + 
				"\n" + 
				"void updateCancel(){\n" + 
				"    resSegIndex = (resBit==0) ? resSegIndex - 1 : resSegIndex;\n" + 
				"    resCBIndex = (resBit==1) ? resCBIndex - 1 : resCBIndex;\n" + 
				"    resBit = resBit^1;\n" + 
				"\n" + 
				"    if(requiresLock[resCBIndex] &amp;&amp; lockIndex &gt; resCBIndex){\n" + 
				"        locks--;\n" + 
				"        lockIndex = resCBIndex;\n" + 
				"    }\n" + 
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
				"		<location id=\"id1\" x=\"-680\" y=\"-578\">\n" + 
				"			<name x=\"-656\" y=\"-595\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id2\" x=\"-680\" y=\"-1258\">\n" + 
				"			<name x=\"-748\" y=\"-1283\">Arrived</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id3\" x=\"-340\" y=\"-748\">\n" + 
				"			<name x=\"-323\" y=\"-765\">Reserving</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id4\" x=\"-680\" y=\"-952\">\n" + 
				"			<name x=\"-824\" y=\"-960\">SingleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id5\" x=\"-1020\" y=\"-748\">\n" + 
				"			<name x=\"-1044\" y=\"-732\">Locking</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id1\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1190\" y=\"-1003\">possibleToCancel() &amp;&amp; resBit == 1</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1190\" y=\"-986\">cancel[boxes[resCBIndex-1]][segments[resSegIndex]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1190\" y=\"-969\">updateCancel()</label>\n" + 
				"			<nail x=\"-1028\" y=\"-1011\"/>\n" + 
				"			<nail x=\"-1028\" y=\"-1156\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1045\" y=\"-1224\">possibleToCancel() &amp;&amp; resBit == 0</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1045\" y=\"-1207\">cancel[boxes[resCBIndex]][segments[resSegIndex - 1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1045\" y=\"-1190\">updateCancel()</label>\n" + 
				"			<nail x=\"-1020\" y=\"-1156\"/>\n" + 
				"			<nail x=\"-756\" y=\"-1156\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id1\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"guard\" x=\"-671\" y=\"-680\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-671\" y=\"-663\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-671\" y=\"-646\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-884\" y=\"-824\">notOK[id]?</label>\n" + 
				"			<nail x=\"-748\" y=\"-748\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-867\" y=\"-748\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-867\" y=\"-731\">updateLockInfo()</label>\n" + 
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
				"			<nail x=\"-714\" y=\"-1326\"/>\n" + 
				"			<nail x=\"-646\" y=\"-1326\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id0\"/>\n" + 
				"			<label kind=\"guard\" x=\"-544\" y=\"-1207\">possibleToPass()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-544\" y=\"-1190\">pass[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-544\" y=\"-1173\">updateHeadInfo()</label>\n" + 
				"			<nail x=\"-612\" y=\"-1156\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"guard\" x=\"-671\" y=\"-1224\">hasArrived()</label>\n" + 
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
				"			<label kind=\"guard\" x=\"-1147\" y=\"-875\">possibleToLock()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1232\" y=\"-858\">reqLock[boxes[lockIndex]][id]\n" + 
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
	}

	@Override
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
				"void updateCancel(seg_id s){\n" + 
				"    for(i:int[0,2]){\n" + 
				"        if(segments[i] == s){\n" + 
				"            if(res[i] == lockedBy){\n" + 
				"                lockedBy = -1;\n" + 
				"            }\n" + 
				"            res[i] = -1;\n" + 
				"        }\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"bool isWellFormed(){\n" + 
				"    return cBIsWellFormed(id) &amp;&amp; pointIsWellFormed(id);\n" + 
				"}</declaration>\n" + 
				"		<location id=\"id8\" x=\"-1139\" y=\"76\">\n" + 
				"			<name x=\"-1156\" y=\"42\">Initial</name>\n" + 
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
				"			<source ref=\"id14\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"select\" x=\"-918\" y=\"68\">i:seg_id</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-918\" y=\"85\">cancel[id][i]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-918\" y=\"102\">updateCancel(i)</label>\n" + 
				"			<nail x=\"-952\" y=\"68\"/>\n" + 
				"			<nail x=\"-816\" y=\"68\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1105\" y=\"51\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1105\" y=\"68\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1105\" y=\"85\">initialize()</label>\n" + 
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
				"			<label kind=\"synchronisation\" x=\"-790\" y=\"-127\">reqLock[id][i][j][k]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-790\" y=\"-110\">result = checkLock(j,k),\n" + 
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
				"			<nail x=\"-782\" y=\"34\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}

	@Override
	protected String generateChannels() {
		return "//Channels\n" + 
				"chan reqSeg[NCB][NTRAIN][NSEG];\n" + 
				"chan reqLock[NCB][NTRAIN][NSEG][NSEG];\n" + 
				"chan OK[NTRAIN];\n" + 
				"chan notOK[NTRAIN];\n" + 
				"chan pass[NCB];\n" + 
				"chan passed[NCB];\n" + 
				"chan switchPoint[NPOINT];\n" + 
				"chan OKp[NCB];\n" + 
				"urgent broadcast chan start;\n" + 
				"chan cancel[NCB][NSEG];\n\n";
	}

	
}
