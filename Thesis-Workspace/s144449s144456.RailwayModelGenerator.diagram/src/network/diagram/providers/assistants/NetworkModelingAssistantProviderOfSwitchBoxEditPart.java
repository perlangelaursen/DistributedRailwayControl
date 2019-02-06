package network.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import network.diagram.edit.parts.RegularBoxEditPart;
import network.diagram.edit.parts.SwitchBoxEditPart;
import network.diagram.providers.NetworkElementTypes;
import network.diagram.providers.NetworkModelingAssistantProvider;

/**
 * @generated
 */
public class NetworkModelingAssistantProviderOfSwitchBoxEditPart extends NetworkModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((SwitchBoxEditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(SwitchBoxEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(NetworkElementTypes.SegmentOneWay_4002);
		types.add(NetworkElementTypes.SegmentTwoWay_4003);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((SwitchBoxEditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(SwitchBoxEditPart source,
			IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof SwitchBoxEditPart) {
			types.add(NetworkElementTypes.SegmentOneWay_4002);
		}
		if (targetEditPart instanceof RegularBoxEditPart) {
			types.add(NetworkElementTypes.SegmentOneWay_4002);
		}
		if (targetEditPart instanceof SwitchBoxEditPart) {
			types.add(NetworkElementTypes.SegmentTwoWay_4003);
		}
		if (targetEditPart instanceof RegularBoxEditPart) {
			types.add(NetworkElementTypes.SegmentTwoWay_4003);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((SwitchBoxEditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(SwitchBoxEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == NetworkElementTypes.SegmentOneWay_4002) {
			types.add(NetworkElementTypes.SwitchBox_2001);
			types.add(NetworkElementTypes.RegularBox_2002);
		} else if (relationshipType == NetworkElementTypes.SegmentTwoWay_4003) {
			types.add(NetworkElementTypes.SwitchBox_2001);
			types.add(NetworkElementTypes.RegularBox_2002);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((SwitchBoxEditPart) targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnTarget(SwitchBoxEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(NetworkElementTypes.SegmentOneWay_4002);
		types.add(NetworkElementTypes.SegmentTwoWay_4003);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((SwitchBoxEditPart) targetEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForSource(SwitchBoxEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == NetworkElementTypes.SegmentOneWay_4002) {
			types.add(NetworkElementTypes.SwitchBox_2001);
			types.add(NetworkElementTypes.RegularBox_2002);
		} else if (relationshipType == NetworkElementTypes.SegmentTwoWay_4003) {
			types.add(NetworkElementTypes.SwitchBox_2001);
			types.add(NetworkElementTypes.RegularBox_2002);
		}
		return types;
	}

}
