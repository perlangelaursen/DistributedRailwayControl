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
import network.diagram.edit.parts.SegmentEditPart;
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
			"s144449s144456.RailwayNetworkConfigurationGenerator.diagram.Network_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SwitchBox_2005 = getElementType(
			"s144449s144456.RailwayNetworkConfigurationGenerator.diagram.SwitchBox_2005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RegularBox_2006 = getElementType(
			"s144449s144456.RailwayNetworkConfigurationGenerator.diagram.RegularBox_2006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Train_2007 = getElementType(
			"s144449s144456.RailwayNetworkConfigurationGenerator.diagram.Train_2007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Segment_4004 = getElementType(
			"s144449s144456.RailwayNetworkConfigurationGenerator.diagram.Segment_4004"); //$NON-NLS-1$

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

			elements.put(SwitchBox_2005, NetworkPackage.eINSTANCE.getSwitchBox());

			elements.put(RegularBox_2006, NetworkPackage.eINSTANCE.getRegularBox());

			elements.put(Train_2007, NetworkPackage.eINSTANCE.getTrain());

			elements.put(Segment_4004, NetworkPackage.eINSTANCE.getSegment());
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
			KNOWN_ELEMENT_TYPES.add(SwitchBox_2005);
			KNOWN_ELEMENT_TYPES.add(RegularBox_2006);
			KNOWN_ELEMENT_TYPES.add(Train_2007);
			KNOWN_ELEMENT_TYPES.add(Segment_4004);
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
			return SwitchBox_2005;
		case RegularBoxEditPart.VISUAL_ID:
			return RegularBox_2006;
		case TrainEditPart.VISUAL_ID:
			return Train_2007;
		case SegmentEditPart.VISUAL_ID:
			return Segment_4004;
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
