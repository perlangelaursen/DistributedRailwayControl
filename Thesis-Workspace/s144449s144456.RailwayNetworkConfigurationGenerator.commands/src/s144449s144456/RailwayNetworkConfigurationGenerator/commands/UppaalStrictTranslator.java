package s144449s144456.RailwayNetworkConfigurationGenerator.commands;

public class UppaalStrictTranslator extends UppaalTranslator {

	@Override
	protected String computeTrainModel() {
		return "<template>\n" + 
				"		<name x=\"5\" y=\"5\">Train</name>\n" + 
				"		<parameter>t_id id</parameter>\n" + 
				"		<declaration>segV_id segments[NROUTELENGTH];\n" + 
				"cBV_id boxes[NROUTELENGTH+1];\n" + 
				"\n" + 
				"int[0,NSEG] routeLength;\n" + 
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
				"bool stillPossibleToReserve() {\n" + 
				"    int[0,1] tempBit = resBit^1;\n" + 
				"    int[0,NSEG]  tempSegIndex = (tempBit == 0) ? resSegIndex + 1 : resSegIndex;\n" + 
				"    return tempSegIndex &lt; routeLength &amp;&amp; tempSegIndex - 1 -index &lt; resLimit;\n" + 
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
				"bool hasReservations() {\n" + 
				"    return resSegIndex &gt; index + 1;\n" + 
				"}\n" + 
				"\n" + 
				"bool moreReservations() {\n" + 
				"    return resSegIndex &gt; index + 2;\n" + 
				"}\n" + 
				"\n" + 
				"bool stillAbleToPass() {\n" + 
				"    return resSegIndex &gt; index + 2 &amp;&amp; lockIndex &gt; index + 2 &amp;&amp; index + 2 &lt; routeLength;\n" + 
				"}\n" + 
				"\n" + 
				"bool isWellFormed(){\n" + 
				"	return segRouteIsWellFormed(segRoutes[id]) &amp;&amp;\n" + 
				"           boxRouteIsWellFormed(boxRoutes[id]) &amp;&amp; \n" + 
				"           routesAreConsistent(id) &amp;&amp; \n" + 
				"           reservationIsWellFormed(initialRes[id]) &amp;&amp; \n" + 
				"           initialResIsConsistent(id);\n" + 
				"}</declaration>\n" + 
				"		<location id=\"id0\" x=\"-578\" y=\"-714\">\n" + 
				"			<name x=\"-561\" y=\"-722\">DoubleSegment</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id1\" x=\"-272\" y=\"-1020\">\n" + 
				"			<name x=\"-255\" y=\"-1037\">Locking</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id2\" x=\"-578\" y=\"-1020\">\n" + 
				"			<name x=\"-680\" y=\"-1028\">PassOrLock</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id3\" x=\"-884\" y=\"-1020\">\n" + 
				"			<name x=\"-867\" y=\"-1028\">Reserving</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id4\" x=\"-1394\" y=\"-1020\">\n" + 
				"			<name x=\"-1404\" y=\"-1054\">Initial</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id5\" x=\"-1190\" y=\"-1224\">\n" + 
				"			<name x=\"-1258\" y=\"-1249\">Arrived</name>\n" + 
				"		</location>\n" + 
				"		<location id=\"id6\" x=\"-1190\" y=\"-1020\">\n" + 
				"			<name x=\"-1309\" y=\"-1054\">SingleSegment</name>\n" + 
				"		</location>\n" + 
				"		<init ref=\"id4\"/>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id6\"/>\n" + 
				"			<target ref=\"id3\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1105\" y=\"-952\">possibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1105\" y=\"-935\">reqSeg[boxes[resCBIndex]]\n" + 
				"[id][segments[resSegIndex]]!</label>\n" + 
				"			<nail x=\"-1088\" y=\"-952\"/>\n" + 
				"			<nail x=\"-986\" y=\"-952\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1105\" y=\"-1037\">!hasReservations()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1105\" y=\"-1020\">notOK[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id1\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-408\" y=\"-1037\">notOK[id]?</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id2\"/>\n" + 
				"			<target ref=\"id1\"/>\n" + 
				"			<label kind=\"guard\" x=\"-467\" y=\"-1147\">possibleToLock()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-467\" y=\"-1130\">reqLock[boxes[lockIndex]][id]\n" + 
				"[segments[lockIndex-1]][segments[lockIndex]]!</label>\n" + 
				"			<nail x=\"-476\" y=\"-1088\"/>\n" + 
				"			<nail x=\"-374\" y=\"-1088\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1096\" y=\"-1147\">stillPossibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1096\" y=\"-1130\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1096\" y=\"-1113\">updateResInfo()</label>\n" + 
				"			<nail x=\"-986\" y=\"-1088\"/>\n" + 
				"			<nail x=\"-1088\" y=\"-1088\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-986\">hasReservations()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-969\">notOK[id]?</label>\n" + 
				"			<nail x=\"-782\" y=\"-952\"/>\n" + 
				"			<nail x=\"-680\" y=\"-952\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id3\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"guard\" x=\"-782\" y=\"-1147\">!stillPossibleToReserve()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-782\" y=\"-1130\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-782\" y=\"-1113\">updateResInfo()</label>\n" + 
				"			<nail x=\"-790\" y=\"-1088\"/>\n" + 
				"			<nail x=\"-680\" y=\"-1088\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id1\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"synchronisation\" x=\"-425\" y=\"-952\">OK[id]?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-425\" y=\"-935\">updateLockInfo()</label>\n" + 
				"			<nail x=\"-374\" y=\"-952\"/>\n" + 
				"			<nail x=\"-476\" y=\"-952\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id0\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"guard\" x=\"-943\" y=\"-705\">!stillAbleToPass() &amp;&amp; !moreReservations()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-943\" y=\"-688\">passed[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-943\" y=\"-671\">updateLocationInfo()</label>\n" + 
				"			<nail x=\"-1190\" y=\"-714\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id0\"/>\n" + 
				"			<target ref=\"id2\"/>\n" + 
				"			<label kind=\"guard\" x=\"-926\" y=\"-858\">stillAbleToPass() || moreReservations()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-833\" y=\"-841\">passed[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-799\" y=\"-824\">updateLocationInfo()</label>\n" + 
				"			<nail x=\"-646\" y=\"-816\"/>\n" + 
				"			<nail x=\"-646\" y=\"-918\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id2\"/>\n" + 
				"			<target ref=\"id0\"/>\n" + 
				"			<label kind=\"guard\" x=\"-501\" y=\"-858\">possibleToPass() &amp;&amp; !(possibleToLock())</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-501\" y=\"-841\">pass[boxes[index+1]]!</label>\n" + 
				"			<label kind=\"assignment\" x=\"-501\" y=\"-824\">updateHeadInfo()</label>\n" + 
				"			<nail x=\"-510\" y=\"-918\"/>\n" + 
				"			<nail x=\"-510\" y=\"-816\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id4\"/>\n" + 
				"			<target ref=\"id6\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1351\" y=\"-1003\">isWellFormed()</label>\n" + 
				"			<label kind=\"synchronisation\" x=\"-1351\" y=\"-986\">start?</label>\n" + 
				"			<label kind=\"assignment\" x=\"-1351\" y=\"-969\">initialize()</label>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id5\"/>\n" + 
				"			<target ref=\"id5\"/>\n" + 
				"			<nail x=\"-1224\" y=\"-1292\"/>\n" + 
				"			<nail x=\"-1156\" y=\"-1292\"/>\n" + 
				"		</transition>\n" + 
				"		<transition>\n" + 
				"			<source ref=\"id6\"/>\n" + 
				"			<target ref=\"id5\"/>\n" + 
				"			<label kind=\"guard\" x=\"-1275\" y=\"-1181\">hasArrived()</label>\n" + 
				"		</transition>\n" + 
				"	</template>\n";
	}

}
