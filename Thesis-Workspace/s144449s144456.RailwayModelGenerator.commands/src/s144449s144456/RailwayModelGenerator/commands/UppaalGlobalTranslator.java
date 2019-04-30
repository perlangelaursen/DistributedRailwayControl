package s144449s144456.RailwayModelGenerator.commands;

public class UppaalGlobalTranslator extends UppaalTranslator {
	
	@Override
	protected String computeTrainModel() {
		return "<template>\n" + 
				"		<name x=\"5\" y=\"5\">Train</name>\n" + 
				"		<parameter>t_id id</parameter>\n" + 
				"		<declaration>\n" +  
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
				"        if(segRoutes[id][i]&gt;-1) {\n" + 
				"            routeLength++;\n" + 
				"        }\n" + 
				"    }\n" + 
				"    curSeg = segRoutes[id][0];\n" + 
				"\n" + 
				"    //Control boxes\n" + 
				"    for(i : cBRoute_i) {\n" + 
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

	protected String computeQueries() {
		return "<queries>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>MISCELLANEOUS\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A&lt;&gt; forall(i:t_id) not(Train(i).Initial)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Well-formedness of Trains.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A&lt;&gt; forall(i:cB_id) not(CB(i).Initial)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Well-formedness of CBs.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] !deadlock\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No deadlock.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>LIVENESS AND SAFETY\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>E&lt;&gt; forall(i:t_id) Train(i).Arrived\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Liveness:\r\n" + 
				"There exists a sequence of actions for which all Trains arrive at their destinations.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:t_id) Initializer.Initialized &amp;&amp; i != j imply\r\n" + 
				"	(Train(i).curSeg != Train(j).curSeg) &amp;&amp;\r\n" + 
				"	(Train(i).DoubleSegment imply Train(i).headSeg != Train(j).curSeg) &amp;&amp;\r\n" + 
				"	(Train(i).DoubleSegment &amp;&amp; Train(j).DoubleSegment imply Train(i).headSeg!= Train(j).headSeg)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No collision.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply\r\n" + 
				"	(Train(i).headSeg == cBs[boxRoutes[i][Train(i).index+1]][0] &amp;&amp; Train(i).curSeg == CB(boxRoutes[i][Train(i).index+1]).connected) ||\r\n" + 
				"	(Train(i).curSeg == cBs[boxRoutes[i][Train(i).index+1]][0] &amp;&amp; Train(i).headSeg == CB(boxRoutes[i][Train(i).index+1]).connected)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No derailment:\r\n" + 
				"If a train is in a critical section, then the segments that it is moving on are connected.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[boxRoutes[i][Train(i).index+1]] != -1 imply\r\n" + 
				"	!Point(points[boxRoutes[i][Train(i).index+1]]).SwitchingPM &amp;&amp; !Point(points[boxRoutes[i][Train(i).index+1]]).SwitchingMP\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>No derailment:\r\n" + 
				"If a train is in a critical section, the point in that section is not in themiddle of switching.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>CONCISTENCY\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks == (sum(j:cB_id) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; points[boxRoutes[i][j]] &gt; -1))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Lock consistency:\r\n" + 
				"The number of saved locks in the state space of a Train is the same number of locks that it believes that it has.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:p_id) Initializer.Initialized &amp;&amp; !Point(i).SwitchingPM &amp;&amp; !Point(i).SwitchingMP imply (pointInPlus[i] imply Point(i).Plus) &amp;&amp; (!pointInPlus[i] imply Point(i).Minus)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Network array consistency:\r\n" + 
				"The position of a point in the network data is consistent with the actual position of the point.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) Initializer.Initialized &amp;&amp; points[i] &gt; -1 &amp;&amp; !CB(i).Switching imply \r\n" + 
				"	((CB(i).connected == cBs[i][1]) imply Point(points[i]).Plus &amp;&amp; (CB(i).connected == cBs[i][2]) imply Point(points[i]).Minus)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Point consistency:\r\n" + 
				"A CB's information about its associated Point's position is consistent with the Point's actual position.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) (j &gt; Train(i).index &amp;&amp; j &lt; Train(i).lockIndex &amp;&amp; Train(i).requiresLock[j] imply \r\n" + 
				"	CB(boxRoutes[i][j]).lockedBy == i)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Lock consistency:\r\n" + 
				"The locks saved in the state space of a Train are also saved in the state spaces of the involved CBs.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:seg_id) (j &gt;= Train(i).index &amp;&amp; j &lt; Train(i).resSegIndex imply exists(l:int[0,2]) cBs[boxRoutes[i][j+1]][l] == segRoutes[i][j] &amp;&amp; CB(boxRoutes[i][j+1]).res[l] == i) &amp;&amp;\r\n" + 
				"		(j &gt; Train(i).index &amp;&amp; j &lt; Train(i).resCBIndex imply exists(l:int[0,2]) cBs[boxRoutes[i][j]][l] == segRoutes[i][j] &amp;&amp; CB(boxRoutes[i][j]).res[l] == i)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>Reservation consistency:\r\n" + 
				"The reservations saved in the state space of a Train are also saved in the state spaces of the involved CBs.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>PASS\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply \r\n" + 
				"	Train(i).index+1 &lt; Train(i).resSegIndex\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train only enters a segment that it has the full reservation of.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment imply boxRoutes[i][Train(i).index+1] != boxRoutes[i][Train(i).routeLength]\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train never passes the last control box on its route.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).DoubleSegment &amp;&amp; points[boxRoutes[i][Train(i).index+1]] != -1 imply\r\n" + 
				"	CB(boxRoutes[i][Train(i).index+1]).lockedBy == i\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train only passes a switch box if it has been locked for the train.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>LOCKS\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).Switched imply CB(i).lockedBy == -1\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A lock is only successful if the point involved in the request was unlocked prior to the request.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).Switched || CB(i).Switching) imply !(exists(j:t_id) (Train(j).DoubleSegment &amp;&amp; boxRoutes[j][Train(j).index+1] == i))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A control box only switches and locks its point if no train is in its critical section.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) (Train(i).Locking &amp;&amp; CB(j).Switched &amp;&amp; CB(j).tid == i) imply \r\n" + 
				"	(exists(k:int[1,2]) \r\n" + 
				"	(cBs[j][0] == segRoutes[i][Train(i).lockIndex-1] &amp;&amp; cBs[j][k] == segRoutes[i][Train(i).lockIndex]) ||\r\n" + 
				"	(cBs[j][0] == segRoutes[i][Train(i).lockIndex] &amp;&amp; cBs[j][k] == segRoutes[i][Train(i).lockIndex-1]))\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A switch is only successful if the requested connection is of segments that are adjacent in the train’s route and the stem segment and plus or minus segment of the switch box.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) CB(i).lockedBy != -1 imply \r\n" + 
				"	CB(i).res[0] == CB(i).lockedBy &amp;&amp; \r\n" + 
				"	exists(j:int[0,2]) cBs[i][j] == CB(i).connected &amp;&amp; CB(i).res[j] == CB(i).lockedBy\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A lock is only successful if the requesting train has the reservation for the stem segment at the switch box and one other segment.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) (CB(i).lockedBy != -1) imply (exists(j:cB_id) boxRoutes[CB(i).lockedBy][j] == i)\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A lock is only successful if the involved switch box is in the route of the requesting train.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).locks &lt;= lockLimit\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train never has more locks than the lock limit.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>RESERVATIONS\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) \r\n" + 
				"	(Train(i).Reserving &amp;&amp; CB(j).SegmentChecked &amp;&amp; CB(j).tid == i &amp;&amp; CB(j).result &gt; -1) imply \r\n" + 
				"	CB(j).res[CB(j).result] == -1\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A reservation is only successful if the requested segment is not already reserved.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) forall(j:cB_id) \r\n" + 
				"	(Train(i).Reserving &amp;&amp; CB(j).SegmentChecked &amp;&amp; CB(j).tid == i &amp;&amp; CB(j).result &gt; -1) imply \r\n" + 
				"	(exists(k:int[0,2]) cBs[j][k] == segRoutes[i][Train(i).resSegIndex])\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A reservation is only successful if the requested segment is associated with the control box that receives the request.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2]) \r\n" + 
				"	CB(i).res[j] != -1 imply \r\n" + 
				"	exists(k:cB_id) boxRoutes[CB(i).res[j]][k] == i\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A reservation is only successful if the control box that a train contacts is a part of the train’s route.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:cB_id) forall(j:int[0,2])\r\n" + 
				"	CB(i).res[j] != -1 imply \r\n" + 
				"	exists(k:seg_id) segRoutes[CB(i).res[j]][k] == cBs[i][j]\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A reservation is only successful if the requested segment is a part of the requesting train’s route.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"		<query>\r\n" + 
				"			<formula>A[] forall(i:t_id) Train(i).resSegIndex - 1 - Train(i).index &lt;= resLimit\r\n" + 
				"			</formula>\r\n" + 
				"			<comment>A train never has more reservations than the reservation limit.\r\n" + 
				"			</comment>\r\n" + 
				"		</query>\r\n" + 
				"	</queries>\r\n";
		
	}
	@Override
	protected String getFileNameDetails() {
		return "UPPAAL_Global";
	}
	
	

}
