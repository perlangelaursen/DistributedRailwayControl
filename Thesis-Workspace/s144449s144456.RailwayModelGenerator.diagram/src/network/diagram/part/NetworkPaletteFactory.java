
package network.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import network.diagram.providers.NetworkElementTypes;

/**
 * @generated
 */
public class NetworkPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createNetwork1Group());
	}

	/**
	* Creates "network" palette tool group
	* @generated
	*/
	private PaletteContainer createNetwork1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Network1Group_title);
		paletteContainer.setId("createNetwork1Group"); //$NON-NLS-1$
		paletteContainer.add(createSegmentOneWay1CreationTool());
		paletteContainer.add(createSegmentTwoWay2CreationTool());
		paletteContainer.add(createSwitchBox3CreationTool());
		paletteContainer.add(createRegularBox4CreationTool());
		paletteContainer.add(createTrain5CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createSegmentOneWay1CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.SegmentOneWay1CreationTool_title,
				Messages.SegmentOneWay1CreationTool_desc,
				Collections.singletonList(NetworkElementTypes.SegmentOneWay_4002));
		entry.setId("createSegmentOneWay1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.SegmentOneWay_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSegmentTwoWay2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.SegmentTwoWay2CreationTool_title,
				Messages.SegmentTwoWay2CreationTool_desc,
				Collections.singletonList(NetworkElementTypes.SegmentTwoWay_4003));
		entry.setId("createSegmentTwoWay2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.SegmentTwoWay_4003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSwitchBox3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.SwitchBox3CreationTool_title,
				Messages.SwitchBox3CreationTool_desc, Collections.singletonList(NetworkElementTypes.SwitchBox_2001));
		entry.setId("createSwitchBox3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.SwitchBox_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRegularBox4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.RegularBox4CreationTool_title,
				Messages.RegularBox4CreationTool_desc, Collections.singletonList(NetworkElementTypes.RegularBox_2002));
		entry.setId("createRegularBox4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.RegularBox_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTrain5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Train5CreationTool_title, null,
				Collections.singletonList(NetworkElementTypes.Train_2004));
		entry.setId("createTrain5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.Train_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
