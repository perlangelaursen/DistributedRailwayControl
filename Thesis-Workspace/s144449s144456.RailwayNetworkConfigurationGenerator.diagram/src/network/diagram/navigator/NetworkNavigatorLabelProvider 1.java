package network.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import network.Network;
import network.diagram.edit.parts.NetworkEditPart;
import network.diagram.edit.parts.RegularBoxEditPart;
import network.diagram.edit.parts.RegularBoxIdEditPart;
import network.diagram.edit.parts.SegmentEditPart;
import network.diagram.edit.parts.SegmentIdEditPart;
import network.diagram.edit.parts.SwitchBoxEditPart;
import network.diagram.edit.parts.SwitchBoxIdEditPart;
import network.diagram.edit.parts.TrainEditPart;
import network.diagram.edit.parts.TrainIdEditPart;
import network.diagram.part.NetworkDiagramEditorPlugin;
import network.diagram.part.NetworkVisualIDRegistry;
import network.diagram.providers.NetworkElementTypes;
import network.diagram.providers.NetworkParserProvider;

/**
 * @generated
 */
public class NetworkNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		NetworkDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		NetworkDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof NetworkNavigatorItem && !isOwnView(((NetworkNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof NetworkNavigatorGroup) {
			NetworkNavigatorGroup group = (NetworkNavigatorGroup) element;
			return NetworkDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof NetworkNavigatorItem) {
			NetworkNavigatorItem navigatorItem = (NetworkNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	* @generated
	*/
	public Image getImage(View view) {
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case NetworkEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://s144449s144456.railwaynetworkconfigurationgenerator/network?Network", //$NON-NLS-1$
					NetworkElementTypes.Network_1000);
		case SwitchBoxEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://s144449s144456.railwaynetworkconfigurationgenerator/network?SwitchBox", //$NON-NLS-1$
					NetworkElementTypes.SwitchBox_2005);
		case RegularBoxEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://s144449s144456.railwaynetworkconfigurationgenerator/network?RegularBox", //$NON-NLS-1$
					NetworkElementTypes.RegularBox_2006);
		case TrainEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://s144449s144456.railwaynetworkconfigurationgenerator/network?Train", //$NON-NLS-1$
					NetworkElementTypes.Train_2007);
		case SegmentEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://s144449s144456.railwaynetworkconfigurationgenerator/network?Segment", //$NON-NLS-1$
					NetworkElementTypes.Segment_4004);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = NetworkDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && NetworkElementTypes.isKnownElementType(elementType)) {
			image = NetworkElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	* @generated
	*/
	public String getText(Object element) {
		if (element instanceof NetworkNavigatorGroup) {
			NetworkNavigatorGroup group = (NetworkNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof NetworkNavigatorItem) {
			NetworkNavigatorItem navigatorItem = (NetworkNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	* @generated
	*/
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case NetworkEditPart.VISUAL_ID:
			return getNetwork_1000Text(view);
		case SwitchBoxEditPart.VISUAL_ID:
			return getSwitchBox_2005Text(view);
		case RegularBoxEditPart.VISUAL_ID:
			return getRegularBox_2006Text(view);
		case TrainEditPart.VISUAL_ID:
			return getTrain_2007Text(view);
		case SegmentEditPart.VISUAL_ID:
			return getSegment_4004Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getNetwork_1000Text(View view) {
		Network domainModelElement = (Network) view.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getLockLimit());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSwitchBox_2005Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.SwitchBox_2005,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(SwitchBoxIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRegularBox_2006Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.RegularBox_2006,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(RegularBoxIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTrain_2007Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.Train_2007,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(TrainIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSegment_4004Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.Segment_4004,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(SegmentIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	* @generated
	*/
	public void restoreState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public void saveState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	* @generated
	*/
	private boolean isOwnView(View view) {
		return NetworkEditPart.MODEL_ID.equals(NetworkVisualIDRegistry.getModelID(view));
	}

}
