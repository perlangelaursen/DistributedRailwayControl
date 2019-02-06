package network.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import network.ControlBox;
import network.Network;
import network.SegmentTwoWay;
import network.diagram.edit.policies.NetworkBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class SegmentTwoWayReorientCommand extends EditElementCommand {

	/**
	* @generated
	*/
	private final int reorientDirection;

	/**
	* @generated
	*/
	private final EObject oldEnd;

	/**
	* @generated
	*/
	private final EObject newEnd;

	/**
	* @generated
	*/
	public SegmentTwoWayReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	* @generated
	*/
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof SegmentTwoWay) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	* @generated
	*/
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof ControlBox && newEnd instanceof ControlBox)) {
			return false;
		}
		ControlBox target = getLink().getEnd();
		if (!(getLink().eContainer() instanceof Network)) {
			return false;
		}
		Network container = (Network) getLink().eContainer();
		return NetworkBaseItemSemanticEditPolicy.getLinkConstraints().canExistSegmentTwoWay_4003(container, getLink(),
				getNewSource(), target);
	}

	/**
	* @generated
	*/
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof ControlBox && newEnd instanceof ControlBox)) {
			return false;
		}
		ControlBox source = getLink().getStart();
		if (!(getLink().eContainer() instanceof Network)) {
			return false;
		}
		Network container = (Network) getLink().eContainer();
		return NetworkBaseItemSemanticEditPolicy.getLinkConstraints().canExistSegmentTwoWay_4003(container, getLink(),
				source, getNewTarget());
	}

	/**
	* @generated
	*/
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	* @generated
	*/
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setStart(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated
	*/
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setEnd(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	* @generated
	*/
	protected SegmentTwoWay getLink() {
		return (SegmentTwoWay) getElementToEdit();
	}

	/**
	* @generated
	*/
	protected ControlBox getOldSource() {
		return (ControlBox) oldEnd;
	}

	/**
	* @generated
	*/
	protected ControlBox getNewSource() {
		return (ControlBox) newEnd;
	}

	/**
	* @generated
	*/
	protected ControlBox getOldTarget() {
		return (ControlBox) oldEnd;
	}

	/**
	* @generated
	*/
	protected ControlBox getNewTarget() {
		return (ControlBox) newEnd;
	}
}
