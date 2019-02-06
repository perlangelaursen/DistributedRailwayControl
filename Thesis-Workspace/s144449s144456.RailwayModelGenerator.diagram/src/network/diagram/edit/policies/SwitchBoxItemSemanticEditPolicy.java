package network.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import network.diagram.edit.commands.SegmentOneWayCreateCommand;
import network.diagram.edit.commands.SegmentOneWayReorientCommand;
import network.diagram.edit.commands.SegmentTwoWayCreateCommand;
import network.diagram.edit.commands.SegmentTwoWayReorientCommand;
import network.diagram.edit.parts.SegmentOneWayEditPart;
import network.diagram.edit.parts.SegmentTwoWayEditPart;
import network.diagram.part.NetworkVisualIDRegistry;
import network.diagram.providers.NetworkElementTypes;

/**
 * @generated
 */
public class SwitchBoxItemSemanticEditPolicy extends NetworkBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public SwitchBoxItemSemanticEditPolicy() {
		super(NetworkElementTypes.SwitchBox_2001);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (NetworkVisualIDRegistry.getVisualID(incomingLink) == SegmentOneWayEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (NetworkVisualIDRegistry.getVisualID(incomingLink) == SegmentTwoWayEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (NetworkVisualIDRegistry.getVisualID(outgoingLink) == SegmentOneWayEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (NetworkVisualIDRegistry.getVisualID(outgoingLink) == SegmentTwoWayEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (NetworkElementTypes.SegmentOneWay_4002 == req.getElementType()) {
			return getGEFWrapper(new SegmentOneWayCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (NetworkElementTypes.SegmentTwoWay_4003 == req.getElementType()) {
			return getGEFWrapper(new SegmentTwoWayCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (NetworkElementTypes.SegmentOneWay_4002 == req.getElementType()) {
			return getGEFWrapper(new SegmentOneWayCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (NetworkElementTypes.SegmentTwoWay_4003 == req.getElementType()) {
			return getGEFWrapper(new SegmentTwoWayCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SegmentOneWayEditPart.VISUAL_ID:
			return getGEFWrapper(new SegmentOneWayReorientCommand(req));
		case SegmentTwoWayEditPart.VISUAL_ID:
			return getGEFWrapper(new SegmentTwoWayReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}