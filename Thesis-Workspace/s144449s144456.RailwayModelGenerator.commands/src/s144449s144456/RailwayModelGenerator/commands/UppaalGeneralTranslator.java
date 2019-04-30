package s144449s144456.RailwayModelGenerator.commands;

public class UppaalGeneralTranslator extends UppaalTranslator {
	
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
				"bool requiresLock[NROUTELENGTH];\n" + 
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
	}

	protected String getFileNameDetails() {
		return "UPPAAL";
	}
}
