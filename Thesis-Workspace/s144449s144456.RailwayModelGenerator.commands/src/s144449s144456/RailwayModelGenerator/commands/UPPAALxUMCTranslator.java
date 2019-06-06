package s144449s144456.RailwayModelGenerator.commands;

public class UPPAALxUMCTranslator extends UPPAALTranslator{

	protected String getGlobalFunctions() {
		return  "";
	}
	
	@Override
	protected String getTrainTemplate() {
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
				"    headSeg = segments[index+1];\n" + 
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
				"		<location id=\"id0\" x=\"-858\" y=\"-850\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id1\" x=\"-535\" y=\"-875\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id2\" x=\"-748\" y=\"-748\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id3\" x=\"-493\" y=\"-918\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id4\" x=\"-340\" y=\"-1156\">\n" + 
				"			<name x=\"-323\" y=\"-1173\">DoubleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id5\" x=\"-1020\" y=\"-1156\">\n" + 
				"			<name x=\"-1030\" y=\"-1190\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id6\" x=\"-680\" y=\"-1156\">\n" + 
				"			<name x=\"-748\" y=\"-1181\">Arrived</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id7\" x=\"-340\" y=\"-748\">\n" + 
				"			<name x=\"-323\" y=\"-765\">Reserving</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id8\" x=\"-680\" y=\"-952\">\n" + 
				"			<name x=\"-807\" y=\"-969\">SingleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id9\" x=\"-1020\" y=\"-748\">\n" + 
				"			<name x=\"-1044\" y=\"-732\">Locking</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id5\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id1\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id0\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"			<label kind=\"assignment\" x=\"-840\" y=\"-901\">updateLockInfo()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id2\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"			<label kind=\"assignment\" x=\"-595\" y=\"-960\">updateResInfo()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1028\" y=\"-1079\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1028\" y=\"-1062\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id9\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-918\" y=\"-739\">notOK[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id9\"/>\n" + 
				"			<target ref=\"id0\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-807\">OK[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id7\"/>\n" + 
				"			<target ref=\"id3\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-323\" y=\"-875\">OK[id]?</label>\n" + 
				"			<nail x=\"-340\" y=\"-884\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id6\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<nail x=\"-714\" y=\"-1224\"/>\n" + 
				"			<nail x=\"-646\" y=\"-1224\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id4\"/>\n" + 
				"			<label kind=\"guard\" x=\"-544\" y=\"-1207\">possibleToPass()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-544\" y=\"-1190\">pass[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-544\" y=\"-1173\">updateHeadInfo()</label>\n" + 
				"			<nail x=\"-578\" y=\"-1156\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"guard\" x=\"-765\" y=\"-1113\">hasArrived()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id7\"/>\n" + 
				"			<target ref=\"id1\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-493\" y=\"-841\">notOK[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id8\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-450\" y=\"-994\">passed[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-450\" y=\"-977\">updateLocationInfo()</label>\n" + 
				"			<nail x=\"-340\" y=\"-1020\"/>\n" + 
				"			<nail x=\"-595\" y=\"-969\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id9\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1241\" y=\"-841\">possibleToLock()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1241\" y=\"-824\">reqLock[boxes[lockIndex]][id]\n" + 
				"[segments[lockIndex-1]]\n" + 
				"[segments[lockIndex]]!</label>\n" + 
				"			<nail x=\"-1020\" y=\"-884\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id8\"/>\n" + 
				"			<target ref=\"id7\"/>\n" + 
				"			<label kind=\"guard\" x=\"-612\" y=\"-740\">possibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-612\" y=\"-722\">reqSeg[boxes[resCBIndex]][id][segments[resSegIndex]]!</label>\n" + 
				"			<nail x=\"-612\" y=\"-748\"/>\n" + 
				"			<nail x=\"-527\" y=\"-748\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}
	
	protected String getCBTemplate() {
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
				"		<location id=\"id12\" x=\"-263\" y=\"-34\">\n" + 
				"			<name x=\"-273\" y=\"-68\">SwitchAux</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id13\" x=\"-722\" y=\"-68\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id14\" x=\"-1020\" y=\"-102\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id15\" x=\"-1020\" y=\"-42\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id16\" x=\"-1020\" y=\"-204\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id17\" x=\"-1045\" y=\"51\">\n" + 
				"			<name x=\"-1096\" y=\"42\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id18\" x=\"-1139\" y=\"-68\">\n" + 
				"			<name x=\"-1164\" y=\"-102\">Passing</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id19\" x=\"-356\" y=\"-34\">\n" + 
				"			<name x=\"-366\" y=\"-68\">Switching</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id20\" x=\"-884\" y=\"-306\">\n" + 
				"			<name x=\"-940\" y=\"-338\">SegmentChecked</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id21\" x=\"-170\" y=\"-34\">\n" + 
				"			<name x=\"-154\" y=\"-42\">Switched</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id22\" x=\"-646\" y=\"-34\">\n" + 
				"			<name x=\"-680\" y=\"-17\">LockChecked</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id23\" x=\"-884\" y=\"-68\">\n" + 
				"			<name x=\"-858\" y=\"-93\">Idle</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id17\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id12\"/>\n" + 
				"			<target ref=\"id21\"/>\n" + 
				"			<label kind=\"assignment\" x=\"-263\" y=\"-25\">updateConnected()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id13\"/>\n" + 
				"			<target ref=\"id22\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id14\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id15\"/>\n" + 
				"			<target ref=\"id18\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1139\" y=\"-51\">passed[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id16\"/>\n" + 
				"			<target ref=\"id20\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id17\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1011\" y=\"42\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1011\" y=\"59\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id18\"/>\n" + 
				"			<target ref=\"id14\"/>\n" + 
				"			<label kind=\"assignment\" x=\"-1105\" y=\"-119\">clear()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id19\"/>\n" + 
				"			<target ref=\"id12\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-357\" y=\"-25\">OKp[point]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id22\"/>\n" + 
				"			<target ref=\"id21\"/>\n" + 
				"			<label kind=\"guard\" x=\"-458\" y=\"-153\">result == NOSWITCH</label>\n" + 
				"			<nail x=\"-544\" y=\"-136\"/>\n" + 
				"			<nail x=\"-322\" y=\"-136\"/>\n" + 
				"			<nail x=\"-272\" y=\"-136\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id23\"/>\n" + 
				"			<target ref=\"id15\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-994\" y=\"-42\">pass[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id22\"/>\n" + 
				"			<target ref=\"id19\"/>\n" + 
				"			<label kind=\"guard\" x=\"-569\" y=\"-68\">result == DOSWITCH</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-569\" y=\"-51\">switchPoint[point]!</label>\n" + 
				"			<nail x=\"-373\" y=\"-34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id22\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-34\">result == ERROR</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-17\">notOK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-782\" y=\"0\">resetVariables()</label>\n" + 
				"			<nail x=\"-816\" y=\"-34\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id20\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"			<label kind=\"guard\" x=\"-807\" y=\"-289\">result == -1</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-807\" y=\"-272\">notOK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-807\" y=\"-255\">resetVariables()</label>\n" + 
				"			<nail x=\"-816\" y=\"-272\"/>\n" + 
				"			<nail x=\"-816\" y=\"-136\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id20\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"			<label kind=\"guard\" x=\"-943\" y=\"-238\">result &gt; -1</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-221\">OK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-943\" y=\"-204\">updateResInfo()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id23\"/>\n" + 
				"			<target ref=\"id13\"/>\n" + 
				"			<label kind=\"select\" x=\"-807\" y=\"-187\">i : t_id,\n" + 
				"j : seg_id,\n" + 
				"k : seg_id</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-807\" y=\"-136\">reqLock[id][i][j][k]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-807\" y=\"-119\">result = checkLock(j,k),\n" + 
				"tid = i</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id23\"/>\n" + 
				"			<target ref=\"id16\"/>\n" + 
				"			<label kind=\"select\" x=\"-1215\" y=\"-255\">i : t_id,\n" + 
				"j : seg_id</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1215\" y=\"-221\">reqSeg[id][i][j]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1215\" y=\"-204\">result = checkSegment(j),\n" + 
				"tid = i</label>\n" + 
				"			<nail x=\"-952\" y=\"-136\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id21\"/>\n" + 
				"			<target ref=\"id23\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-722\" y=\"34\">OK[tid]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-722\" y=\"51\">updateLockInfo()</label>\n" + 
				"			<nail x=\"-170\" y=\"34\"/>\n" + 
				"			<nail x=\"-884\" y=\"34\"/>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}
	
	protected String getPointTemplate() {
		return "<template>\n" + 
				"		<name>Point</name>\n" + 
				"		<parameter>p_id id</parameter>\n" + 
				"		<declaration>int inPlus;</declaration>\n" + 
				"		<location id=\"id24\" x=\"42\" y=\"-42\">\n" + 
				"		</location>\n" + 
				"		<location id=\"id25\" x=\"144\" y=\"0\">\n" + 
				"			<name x=\"110\" y=\"17\">Switching</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id26\" x=\"-272\" y=\"0\">\n" + 
				"			<name x=\"-281\" y=\"17\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id27\" x=\"-68\" y=\"0\">\n" + 
				"			<name x=\"-85\" y=\"17\">Idle</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id26\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id24\"/>\n" + 
				"			<target ref=\"id25\"/>\n" + 
				"			<label kind=\"assignment\" x=\"93\" y=\"-42\">inPlus = !inPlus</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id25\"/>\n" + 
				"			<target ref=\"id27\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"8\" y=\"42\">OKp[id]!</label>\n" + 
				"			<nail x=\"34\" y=\"42\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id26\"/>\n" + 
				"			<target ref=\"id27\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-246\" y=\"-42\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-246\" y=\"-25\">inPlus = pointInPlus[id]</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id27\"/>\n" + 
				"			<target ref=\"id24\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-85\" y=\"-51\">switchPoint[id]?</label>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
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
				"			<formula>A&lt;&gt; forall(i:t_id) not(Train(i).Initial)\n" + 
				"			</formula>\n" + 
				"			<comment>Well-formedness of Trains.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A&lt;&gt; forall(i:cB_id) not(CB(i).Initial)\n" + 
				"			</formula>\n" + 
				"			<comment>Well-formedness of CBs.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] !deadlock\n" + 
				"			</formula>\n" + 
				"			<comment>No deadlock.\n" + 
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
				"			<comment>Liveness:\n" + 
				"There exists a sequence of actions for which all Trains arrive at their destinations.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:t_id) Initializer.Initialized &amp;&amp; i != j imply\n" + 
				"	(Train(i).curSeg != Train(j).curSeg) &amp;&amp;\n" + 
				"	(Train(i).DoubleSegment imply Train(i).headSeg != Train(j).curSeg) &amp;&amp;\n" + 
				"	(Train(i).DoubleSegment &amp;&amp; Train(j).DoubleSegment imply Train(i).headSeg!= Train(j).headSeg)\n" + 
				"			</formula>\n" + 
				"			<comment>No collision.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply\n" + 
				"	(Train(i).headSeg == CB(Train(i).boxes[Train(i).index+1]).segments[0] &amp;&amp; Train(i).curSeg == CB(Train(i).boxes[Train(i).index+1]).connected) ||\n" + 
				"	(Train(i).curSeg == CB(Train(i).boxes[Train(i).index+1]).segments[0] &amp;&amp; Train(i).headSeg == CB(Train(i).boxes[Train(i).index+1]).connected)\n" + 
				"			</formula>\n" + 
				"			<comment>No derailment:\n" + 
				"If a train is in a critical section, then the segments that it is moving on are connected.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[Train(i).boxes[Train(i).index+1]] != -1 imply\n" + 
				"	!Point(points[Train(i).boxes[Train(i).index+1]]).Switching\n" + 
				"			</formula>\n" + 
				"			<comment>No derailment:\n" + 
				"If a train is in a critical section, the point in that section is not in the middle of switching.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>\n" + 
				"			</formula>\n" + 
				"			<comment>CONSISTENCY\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks == (sum(j:cBRoute_i) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; points[Train(i).boxes[j]] &gt; -1))\n" + 
				"			</formula>\n" + 
				"			<comment>Lock consistency:\n" + 
				"The number of saved locks in the state space of a Train is the same number of locks that it believes that it has.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) Initializer.Initialized &amp;&amp; points[i] &gt; -1 &amp;&amp; !CB(i).Switching &amp;&amp; !CB(i).SwitchAux imply \n" + 
				"	((CB(i).connected == CB(i).segments[1]) imply Point(points[i]).inPlus &amp;&amp; (CB(i).connected == CB(i).segments[2]) imply !Point(points[i]).inPlus)\n" + 
				"			</formula>\n" + 
				"			<comment>Point consistency:\n" + 
				"A CB's information about its associated Point's position is consistent with the Point's actual position.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cBRoute_i) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; Train(i).requiresLock[j] imply \n" + 
				"	CB(Train(i).boxes[j]).lockedBy == i)\n" + 
				"			</formula>\n" + 
				"			<comment>Lock consistency:\n" + 
				"The locks saved in the state space of a Train are also saved in the state spaces of the involved CBs.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:segRoute_i) (j &gt;= Train(i).index &amp;&amp; j &lt; Train(i).resSegIndex imply exists(l:int[0,2]) CB(Train(i).boxes[j+1]).segments[l] == Train(i).segments[j] &amp;&amp; CB(Train(i).boxes[j+1]).res[l] == i) &amp;&amp;\n" + 
				"		(j &gt; Train(i).index &amp;&amp; j &lt; Train(i).resCBIndex imply exists(l:int[0,2]) CB(Train(i).boxes[j]).segments[l] == Train(i).segments[j] &amp;&amp; CB(Train(i).boxes[j]).res[l] == i)\n" + 
				"			</formula>\n" + 
				"			<comment>Reservation consistency:\n" + 
				"The reservations saved in the state space of a Train are also saved in the state spaces of the involved CBs.\n" + 
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
				"			<comment>A train only enters a segment that it has the full reservation of.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply Train(i).boxes[Train(i).index+1] != Train(i).boxes[Train(i).routeLength]\n" + 
				"			</formula>\n" + 
				"			<comment>A train never passes the last control box on its route.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[Train(i).boxes[Train(i).index+1]] != -1 imply\n" + 
				"	CB(Train(i).boxes[Train(i).index+1]).lockedBy == i\n" + 
				"			</formula>\n" + 
				"			<comment>A train only passes a switch box if it has been locked for the train.\n" + 
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
				"			<comment>A lock is only successful if the point involved in the request was unlocked prior to the request.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).Switched || CB(i).Switching) imply !(exists(j:t_id) (Train(j).DoubleSegment &amp;&amp; Train(j).boxes[Train(j).index+1] == i))\n" + 
				"			</formula>\n" + 
				"			<comment>A control box only switches and locks its point if no train is in its critical section.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) (Train(i).Locking &amp;&amp; CB(j).Switched &amp;&amp; CB(j).tid == i) imply \n" + 
				"	(exists(k:int[1,2]) \n" + 
				"	(CB(j).segments[0] == Train(i).segments[Train(i).lockIndex-1] &amp;&amp; CB(j).segments[k] == Train(i).segments[Train(i).lockIndex]) ||\n" + 
				"	(CB(j).segments[0] == Train(i).segments[Train(i).lockIndex] &amp;&amp; CB(j).segments[k] == Train(i).segments[Train(i).lockIndex-1]))\n" + 
				"			</formula>\n" + 
				"			<comment>A switch is only successful if the requested connection is of segments that are adjacent in the train’s route and the stem segment and plus or minus segment of the switch box.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).lockedBy != -1 imply \n" + 
				"	CB(i).res[0] == CB(i).lockedBy &amp;&amp; \n" + 
				"	exists(j:int[0,2]) CB(i).segments[j] == CB(i).connected &amp;&amp; CB(i).res[j] == CB(i).lockedBy\n" + 
				"			</formula>\n" + 
				"			<comment>A lock is only successful if the requesting train has the reservation for the stem segment at the switch box and one other segment.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).lockedBy != -1) imply (exists(j:cBRoute_i) Train(CB(i).lockedBy).boxes[j] == i)\n" + 
				"			</formula>\n" + 
				"			<comment>A lock is only successful if the involved switch box is in the route of the requesting train.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks &lt;= lockLimit\n" + 
				"			</formula>\n" + 
				"			<comment>A train never has more locks than the lock limit.\n" + 
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
				"			<comment>A reservation is only successful if the requested segment is not already reserved.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) \n" + 
				"	(Train(i).Reserving &amp;&amp; CB(j).SegmentChecked &amp;&amp; CB(j).tid == i &amp;&amp; CB(j).result &gt; -1) imply \n" + 
				"	(exists(k:int[0,2]) CB(j).segments[k] == Train(i).segments[Train(i).resSegIndex])\n" + 
				"			</formula>\n" + 
				"			<comment>A reservation is only successful if the requested segment is associated with the control box that receives the request.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2]) \n" + 
				"	CB(i).res[j] != -1 imply \n" + 
				"	exists(k:cBRoute_i) Train(CB(i).res[j]).boxes[k] == i\n" + 
				"			</formula>\n" + 
				"			<comment>A reservation is only successful if the control box that a train contacts is a part of the train's route.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2])\n" + 
				"	CB(i).res[j] != -1 imply \n" + 
				"	exists(k:segRoute_i) Train(CB(i).res[j]).segments[k] == CB(i).segments[j]\n" + 
				"			</formula>\n" + 
				"			<comment>A reservation is only successful if the requested segment is a part of the requesting train's route.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"		<query>\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).resSegIndex - 1 - Train(i).index &lt;= resLimit\n" + 
				"			</formula>\n" + 
				"			<comment>A train never has more reservations than the reservation limit.\n" + 
				"			</comment>\n" + 
				"		</query>\n" + 
				"	</queries>\n";
	}
	
	@Override
	protected String getFileNameDetails() {
		return "UPPAAL_Revised";
	}
}
