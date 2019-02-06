package network.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import network.Network;
import network.NetworkPackage;
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

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class NetworkVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "s144449s144456.RailwayModelGenerator.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (NetworkEditPart.MODEL_ID.equals(view.getType())) {
				return NetworkEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return network.diagram.part.NetworkVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	* @generated
	*/
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	* @generated
	*/
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				NetworkDiagramEditorPlugin.getInstance()
						.logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	* @generated
	*/
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (NetworkPackage.eINSTANCE.getNetwork().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((Network) domainElement)) {
			return NetworkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = network.diagram.part.NetworkVisualIDRegistry.getModelID(containerView);
		if (!NetworkEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (NetworkEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = network.diagram.part.NetworkVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = NetworkEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case NetworkEditPart.VISUAL_ID:
			if (NetworkPackage.eINSTANCE.getSwitchBox().isSuperTypeOf(domainElement.eClass())) {
				return SwitchBoxEditPart.VISUAL_ID;
			}
			if (NetworkPackage.eINSTANCE.getRegularBox().isSuperTypeOf(domainElement.eClass())) {
				return RegularBoxEditPart.VISUAL_ID;
			}
			if (NetworkPackage.eINSTANCE.getTrain().isSuperTypeOf(domainElement.eClass())) {
				return TrainEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = network.diagram.part.NetworkVisualIDRegistry.getModelID(containerView);
		if (!NetworkEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (NetworkEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = network.diagram.part.NetworkVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = NetworkEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case NetworkEditPart.VISUAL_ID:
			if (SwitchBoxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RegularBoxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TrainEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SwitchBoxEditPart.VISUAL_ID:
			if (SwitchBoxIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RegularBoxEditPart.VISUAL_ID:
			if (RegularBoxIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TrainEditPart.VISUAL_ID:
			if (TrainIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SegmentOneWayEditPart.VISUAL_ID:
			if (SegmentOneWayIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SegmentTwoWayEditPart.VISUAL_ID:
			if (SegmentTwoWayIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (NetworkPackage.eINSTANCE.getSegmentOneWay().isSuperTypeOf(domainElement.eClass())) {
			return SegmentOneWayEditPart.VISUAL_ID;
		}
		if (NetworkPackage.eINSTANCE.getSegmentTwoWay().isSuperTypeOf(domainElement.eClass())) {
			return SegmentTwoWayEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* User can change implementation of this method to handle some specific
	* situations not covered by default logic.
	* 
	* @generated
	*/
	private static boolean isDiagram(Network element) {
		return true;
	}

	/**
	* @generated
	*/
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	* @generated
	*/
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	* @generated
	*/
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case NetworkEditPart.VISUAL_ID:
			return false;
		case SwitchBoxEditPart.VISUAL_ID:
		case RegularBoxEditPart.VISUAL_ID:
		case TrainEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		* @generated
		*/
		@Override

		public int getVisualID(View view) {
			return network.diagram.part.NetworkVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return network.diagram.part.NetworkVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return network.diagram.part.NetworkVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return network.diagram.part.NetworkVisualIDRegistry.checkNodeVisualID(containerView, domainElement,
					candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return network.diagram.part.NetworkVisualIDRegistry.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return network.diagram.part.NetworkVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};

}
