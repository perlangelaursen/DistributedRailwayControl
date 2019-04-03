package network.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import network.diagram.providers.NetworkElementTypes;

/**
 * @generated
 */
public class SegmentItemSemanticEditPolicy extends NetworkBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public SegmentItemSemanticEditPolicy() {
		super(NetworkElementTypes.Segment_4004);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
