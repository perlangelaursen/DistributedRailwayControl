
package network.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
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
		paletteContainer.add(createSegment1CreationTool());
		paletteContainer.add(createSwitchBox2CreationTool());
		paletteContainer.add(createRegularBox3CreationTool());
		paletteContainer.add(createTrain4CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createSegment1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Segment1CreationTool_title,
				Messages.Segment1CreationTool_desc, Collections.singletonList(NetworkElementTypes.SwitchBox_2005));
		entry.setId("createSegment1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.SwitchBox_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSwitchBox2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.SwitchBox2CreationTool_title,
				Messages.SwitchBox2CreationTool_desc, Collections.singletonList(NetworkElementTypes.RegularBox_2006));
		entry.setId("createSwitchBox2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.RegularBox_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRegularBox3CreationTool() {
		ToolEntry entry = new ToolEntry(Messages.RegularBox3CreationTool_title, Messages.RegularBox3CreationTool_desc,
				null, null) {
		};
		entry.setId("createRegularBox3CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTrain4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Train4CreationTool_title, null,
				Collections.singletonList(NetworkElementTypes.Train_2007));
		entry.setId("createTrain4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes.getImageDescriptor(NetworkElementTypes.Train_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
