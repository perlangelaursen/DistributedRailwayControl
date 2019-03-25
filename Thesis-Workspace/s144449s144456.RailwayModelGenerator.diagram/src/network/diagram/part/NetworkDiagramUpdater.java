package network.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import network.ControlBox;
import network.Network;
import network.NetworkPackage;
import network.RegularBox;
import network.Segment;
import network.SwitchBox;
import network.Train;
import network.diagram.edit.parts.NetworkEditPart;
import network.diagram.edit.parts.RegularBoxEditPart;
import network.diagram.edit.parts.SegmentEditPart;
import network.diagram.edit.parts.SwitchBoxEditPart;
import network.diagram.edit.parts.TrainEditPart;
import network.diagram.providers.NetworkElementTypes;

/**
 * @generated
 */
public class NetworkDiagramUpdater {

	/**
	* @generated
	*/
	public static List<NetworkNodeDescriptor> getSemanticChildren(View view) {
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case NetworkEditPart.VISUAL_ID:
			return getNetwork_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<NetworkNodeDescriptor> getNetwork_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Network modelElement = (Network) view.getElement();
		LinkedList<NetworkNodeDescriptor> result = new LinkedList<NetworkNodeDescriptor>();
		for (Iterator<?> it = modelElement.getControlBoxes().iterator(); it.hasNext();) {
			ControlBox childElement = (ControlBox) it.next();
			int visualID = NetworkVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == SwitchBoxEditPart.VISUAL_ID) {
				result.add(new NetworkNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RegularBoxEditPart.VISUAL_ID) {
				result.add(new NetworkNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getTrains().iterator(); it.hasNext();) {
			Train childElement = (Train) it.next();
			int visualID = NetworkVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TrainEditPart.VISUAL_ID) {
				result.add(new NetworkNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<NetworkLinkDescriptor> getContainedLinks(View view) {
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case NetworkEditPart.VISUAL_ID:
			return getNetwork_1000ContainedLinks(view);
		case SwitchBoxEditPart.VISUAL_ID:
			return getSwitchBox_2001ContainedLinks(view);
		case RegularBoxEditPart.VISUAL_ID:
			return getRegularBox_2002ContainedLinks(view);
		case TrainEditPart.VISUAL_ID:
			return getTrain_2004ContainedLinks(view);
		case SegmentEditPart.VISUAL_ID:
			return getSegment_4004ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<NetworkLinkDescriptor> getIncomingLinks(View view) {
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case SwitchBoxEditPart.VISUAL_ID:
			return getSwitchBox_2001IncomingLinks(view);
		case RegularBoxEditPart.VISUAL_ID:
			return getRegularBox_2002IncomingLinks(view);
		case TrainEditPart.VISUAL_ID:
			return getTrain_2004IncomingLinks(view);
		case SegmentEditPart.VISUAL_ID:
			return getSegment_4004IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<NetworkLinkDescriptor> getOutgoingLinks(View view) {
		switch (NetworkVisualIDRegistry.getVisualID(view)) {
		case SwitchBoxEditPart.VISUAL_ID:
			return getSwitchBox_2001OutgoingLinks(view);
		case RegularBoxEditPart.VISUAL_ID:
			return getRegularBox_2002OutgoingLinks(view);
		case TrainEditPart.VISUAL_ID:
			return getTrain_2004OutgoingLinks(view);
		case SegmentEditPart.VISUAL_ID:
			return getSegment_4004OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getNetwork_1000ContainedLinks(View view) {
		Network modelElement = (Network) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Segment_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSwitchBox_2001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getRegularBox_2002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getTrain_2004ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSegment_4004ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSwitchBox_2001IncomingLinks(View view) {
		SwitchBox modelElement = (SwitchBox) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Segment_4004(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getRegularBox_2002IncomingLinks(View view) {
		RegularBox modelElement = (RegularBox) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Segment_4004(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getTrain_2004IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSegment_4004IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSwitchBox_2001OutgoingLinks(View view) {
		SwitchBox modelElement = (SwitchBox) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Segment_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getRegularBox_2002OutgoingLinks(View view) {
		RegularBox modelElement = (RegularBox) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Segment_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getTrain_2004OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getSegment_4004OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	private static Collection<NetworkLinkDescriptor> getContainedTypeModelFacetLinks_Segment_4004(Network container) {
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		for (Iterator<?> links = container.getSegments().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Segment) {
				continue;
			}
			Segment link = (Segment) linkObject;
			if (SegmentEditPart.VISUAL_ID != NetworkVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getControlBoxes();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof ControlBox) {
				continue;
			}
			ControlBox dst = (ControlBox) theTarget;
			List sources = link.getControlBoxes();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof ControlBox) {
				continue;
			}
			ControlBox src = (ControlBox) theSource;
			result.add(new NetworkLinkDescriptor(src, dst, link, NetworkElementTypes.Segment_4004,
					SegmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NetworkLinkDescriptor> getIncomingTypeModelFacetLinks_Segment_4004(ControlBox target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != NetworkPackage.eINSTANCE.getSegment_ControlBoxes()
					|| false == setting.getEObject() instanceof Segment) {
				continue;
			}
			Segment link = (Segment) setting.getEObject();
			if (SegmentEditPart.VISUAL_ID != NetworkVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			List sources = link.getControlBoxes();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof ControlBox) {
				continue;
			}
			ControlBox src = (ControlBox) theSource;
			result.add(new NetworkLinkDescriptor(src, target, link, NetworkElementTypes.Segment_4004,
					SegmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<NetworkLinkDescriptor> getOutgoingTypeModelFacetLinks_Segment_4004(ControlBox source) {
		Network container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Network) {
				container = (Network) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		for (Iterator<?> links = container.getSegments().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Segment) {
				continue;
			}
			Segment link = (Segment) linkObject;
			if (SegmentEditPart.VISUAL_ID != NetworkVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getControlBoxes();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof ControlBox) {
				continue;
			}
			ControlBox dst = (ControlBox) theTarget;
			List sources = link.getControlBoxes();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof ControlBox) {
				continue;
			}
			ControlBox src = (ControlBox) theSource;
			if (src != source) {
				continue;
			}
			result.add(new NetworkLinkDescriptor(src, dst, link, NetworkElementTypes.Segment_4004,
					SegmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		* @generated
		*/
		@Override

		public List<NetworkNodeDescriptor> getSemanticChildren(View view) {
			return NetworkDiagramUpdater.getSemanticChildren(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<NetworkLinkDescriptor> getContainedLinks(View view) {
			return NetworkDiagramUpdater.getContainedLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<NetworkLinkDescriptor> getIncomingLinks(View view) {
			return NetworkDiagramUpdater.getIncomingLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<NetworkLinkDescriptor> getOutgoingLinks(View view) {
			return NetworkDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
