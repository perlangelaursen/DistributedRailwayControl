package network.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import network.NetworkPackage;
import network.diagram.edit.parts.NetworkEditPart;
import network.diagram.edit.parts.RegularBoxEditPart;
import network.diagram.edit.parts.SegmentOneWayEditPart;
import network.diagram.edit.parts.SegmentTwoWayEditPart;
import network.diagram.edit.parts.SwitchBoxEditPart;
import network.diagram.edit.parts.TrainEditPart;
import network.diagram.part.NetworkDiagramEditorPlugin;

/**
 * @generated
 */
public class NetworkElementTypes {

	/**
	* @generated
	*/
	private NetworkElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			NetworkDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType Network_1000 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.Network_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SwitchBox_2001 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.SwitchBox_2001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RegularBox_2002 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.RegularBox_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Train_2004 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.Train_2004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SegmentOneWay_4002 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.SegmentOneWay_4002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SegmentTwoWay_4003 = getElementType(
			"s144449s144456.RailwayModelGenerator.diagram.SegmentTwoWay_4003"); //$NON-NLS-1$

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	* @generated
	*/
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	* @generated
	*/
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	* Returns 'type' of the ecore object associated with the hint.
	* 
	* @generated
	*/
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Network_1000, NetworkPackage.eINSTANCE.getNetwork());

			elements.put(SwitchBox_2001, NetworkPackage.eINSTANCE.getSwitchBox());

			elements.put(RegularBox_2002, NetworkPackage.eINSTANCE.getRegularBox());

			elements.put(Train_2004, NetworkPackage.eINSTANCE.getTrain());

			elements.put(SegmentOneWay_4002, NetworkPackage.eINSTANCE.getSegmentOneWay());

			elements.put(SegmentTwoWay_4003, NetworkPackage.eINSTANCE.getSegmentTwoWay());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	* @generated
	*/
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	* @generated
	*/
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Network_1000);
			KNOWN_ELEMENT_TYPES.add(SwitchBox_2001);
			KNOWN_ELEMENT_TYPES.add(RegularBox_2002);
			KNOWN_ELEMENT_TYPES.add(Train_2004);
			KNOWN_ELEMENT_TYPES.add(SegmentOneWay_4002);
			KNOWN_ELEMENT_TYPES.add(SegmentTwoWay_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	* @generated
	*/
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case NetworkEditPart.VISUAL_ID:
			return Network_1000;
		case SwitchBoxEditPart.VISUAL_ID:
			return SwitchBox_2001;
		case RegularBoxEditPart.VISUAL_ID:
			return RegularBox_2002;
		case TrainEditPart.VISUAL_ID:
			return Train_2004;
		case SegmentOneWayEditPart.VISUAL_ID:
			return SegmentOneWay_4002;
		case SegmentTwoWayEditPart.VISUAL_ID:
			return SegmentTwoWay_4003;
		}
		return null;
	}

	/**
	* @generated
	*/
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		* @generated
		*/
		@Override

		public boolean isKnownElementType(IElementType elementType) {
			return network.diagram.providers.NetworkElementTypes.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return network.diagram.providers.NetworkElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return network.diagram.providers.NetworkElementTypes.getElement(elementTypeAdapter);
		}
	};

}
