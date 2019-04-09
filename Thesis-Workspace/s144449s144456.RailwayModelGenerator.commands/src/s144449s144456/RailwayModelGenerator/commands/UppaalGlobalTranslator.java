package s144449s144456.RailwayModelGenerator.commands;

public class UppaalGlobalTranslator extends UppaalTranslator {

	@Override
	protected String computeTrainModel() {
		return "<template>\n" + 
				"		<name x=\"5\" y=\"5\">Train</name>\n" + 
				"		<parameter>t_id id</parameter>\n" + 
				"		<declaration>//segV_id segments[NROUTELENGTH];\n" + 
				"//cBV_id boxes[NROUTELENGTH+1];\n" + 
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
				"void updateLockIndex(){\n" + 
				"    while(lockIndex &lt; NROUTELENGTH &amp;&amp; !requiresLock[lockIndex]){\n" + 
				"        lockIndex++;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"void initialize() {\n" + 
				"    //Segments\n" + 
				"    for(i : int[0,NROUTELENGTH-1]) {\n" + 
				"        if(segRoutes[id][i]&gt;-1) {\n" + 
				"            routeLength++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    curSeg = segRoutes[id][0];\n" + 
				"\n" + 
				"    //Control boxes\n" + 
				"    for(i : int[0,NROUTELENGTH]) {\n" + 
				"        if(boxRoutes[id][i] &gt; -1){\n" + 
				"            requiresLock[i] = points[boxRoutes[id][i]] &gt; -1;\n" + 
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
				"    return curSeg == segRoutes[id][routeLength-1];\n" + 
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
				"    headSeg = nextSegment(boxRoutes[id][index+1], curSeg);\n" + 
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
				"			<label kind=\"guard\" x=\"-1011\" y=\"-1096\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1011\" y=\"-1079\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1011\" y=\"-1062\">initialize()</label>\n" + 
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
				"			<label kind=\"synchronisation\" x=\"-544\" y=\"-1190\">pass[boxRoutes[id][index+1]]!</label>\n" + 
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
				"			<label kind=\"synchronisation\" x=\"-450\" y=\"-994\">passed[boxRoutes[id][index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-450\" y=\"-977\">updateLocationInfo()</label>\n" + 
				"			<nail x=\"-340\" y=\"-1020\"/>\n" + 
				"			<nail x=\"-595\" y=\"-969\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id5\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1317\" y=\"-833\">possibleToLock()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1317\" y=\"-816\">reqLock[boxRoutes[id][lockIndex]][id]\n" + 
				"[segRoutes[id][lockIndex-1]][segRoutes[id][lockIndex]]!</label>\n" + 
				"			<nail x=\"-1020\" y=\"-884\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id3\"/>\n" + 
				"			<label kind=\"guard\" x=\"-612\" y=\"-740\">possibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-612\" y=\"-722\">reqSeg[boxRoutes[id][resCBIndex]][id][segRoutes[id][resSegIndex]]!</label>\n" + 
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
				"		<declaration>//segV_id segments[3];\n" + 
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
				"    point = points[id];\n" + 
				"    connected = cBs[id][1];\n" + 
				"\n" + 
				"    if(point != -1 &amp;&amp; !pointInPlus[point]){\n" + 
				"        connected = cBs[id][2];\n" + 
				"    }\n" + 
				"\n" + 
				"    for(i : t_id) {\n" + 
				"        if (initialRes[i].cb == id) {\n" + 
				"            seg_id s = initialRes[i].seg;\n" + 
				"            if(s == cBs[id][0]){\n" + 
				"                res[0] = i;\n" + 
				"            } else if (s == cBs[id][1]){\n" + 
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
				"        if(cBs[id][i] == sid &amp;&amp; res[i] == -1) {\n" + 
				"            return i;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    return -1;\n" + 
				"}\n" + 
				"\n" + 
				"int[0,2] checkLock(seg_id s1, seg_id s2){\n" + 
				"  if(lockedBy == -1 &amp;&amp; (cBs[id][0] == s1 &amp;&amp; exists(i:int[1,2]) cBs[id][i] == s2) || (cBs[id][0] == s2 &amp;&amp; exists(i:int[1,2]) cBs[id][i] == s1)){\n" + 
				"    if ((s1 == cBs[id][0] &amp;&amp; s2 == connected) || (s2 == cBs[id][0] &amp;&amp; s1 == connected)){\n" + 
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
				"     if(connected == cBs[id][1]) {\n" + 
				"        res[1] = -1;\n" + 
				"     } else {\n" + 
				"        res[2] = -1;\n" + 
				"     }\n" + 
				"}\n" + 
				"\n" + 
				"void updateConnected(){\n" + 
				"    if(connected == cBs[id][1]){\n" + 
				"        connected = cBs[id][2];\n" + 
				"    } else {\n" + 
				"        connected = cBs[id][1];\n" + 
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
				"			<nail x=\"-646\" y=\"-136\"/>\n" + 
				"			<nail x=\"-322\" y=\"-136\"/>\n" + 
				"			<nail x=\"-170\" y=\"-136\"/>\n" + 
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
	
	

}
