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
import network.diagram.edit.parts.SegmentOneWayEditPart;
import network.diagram.edit.parts.SegmentOneWayIdEditPart;
import network.diagram.edit.parts.SegmentTwoWayEditPart;
import network.diagram.edit.parts.SegmentTwoWayIdEditPart;
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
			return getImage("Navigator?Diagram?http://s144449s144456.railwaymodelgenerator/network?Network", //$NON-NLS-1$
					NetworkElementTypes.Network_1000);
		case SwitchBoxEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://s144449s144456.railwaymodelgenerator/network?SwitchBox", //$NON-NLS-1$
					NetworkElementTypes.SwitchBox_2001);
		case RegularBoxEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://s144449s144456.railwaymodelgenerator/network?RegularBox", //$NON-NLS-1$
					NetworkElementTypes.RegularBox_2002);
		case TrainEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://s144449s144456.railwaymodelgenerator/network?Train", //$NON-NLS-1$
					NetworkElementTypes.Train_2004);
		case SegmentOneWayEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://s144449s144456.railwaymodelgenerator/network?SegmentOneWay", //$NON-NLS-1$
					NetworkElementTypes.SegmentOneWay_4002);
		case SegmentTwoWayEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://s144449s144456.railwaymodelgenerator/network?SegmentTwoWay", //$NON-NLS-1$
					NetworkElementTypes.SegmentTwoWay_4003);
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
			return getSwitchBox_2001Text(view);
		case RegularBoxEditPart.VISUAL_ID:
			return getRegularBox_2002Text(view);
		case TrainEditPart.VISUAL_ID:
			return getTrain_2004Text(view);
		case SegmentOneWayEditPart.VISUAL_ID:
			return getSegmentOneWay_4002Text(view);
		case SegmentTwoWayEditPart.VISUAL_ID:
			return getSegmentTwoWay_4003Text(view);
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
	private String getSwitchBox_2001Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.SwitchBox_2001,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(SwitchBoxIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRegularBox_2002Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.RegularBox_2002,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(RegularBoxIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTrain_2004Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.Train_2004,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(TrainIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSegmentOneWay_4002Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.SegmentOneWay_4002,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(SegmentOneWayIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSegmentTwoWay_4003Text(View view) {
		IParser parser = NetworkParserProvider.getParser(NetworkElementTypes.SegmentTwoWay_4003,
				view.getElement() != null ? view.getElement() : view,
				NetworkVisualIDRegistry.getType(SegmentTwoWayIdEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NetworkDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6002); //$NON-NLS-1$
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
