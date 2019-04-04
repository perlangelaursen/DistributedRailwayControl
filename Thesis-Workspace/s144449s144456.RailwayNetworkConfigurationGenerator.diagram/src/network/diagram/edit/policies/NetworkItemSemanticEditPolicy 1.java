package network.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import network.diagram.edit.commands.RegularBoxCreateCommand;
import network.diagram.edit.commands.SwitchBoxCreateCommand;
import network.diagram.edit.commands.TrainCreateCommand;
import network.diagram.providers.NetworkElementTypes;

/**
 * @generated
 */
public class NetworkItemSemanticEditPolicy extends NetworkBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public NetworkItemSemanticEditPolicy() {
		super(NetworkElementTypes.Network_1000);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (NetworkElementTypes.SwitchBox_2005 == req.getElementType()) {
			return getGEFWrapper(new SwitchBoxCreateCommand(req));
		}
		if (NetworkElementTypes.RegularBox_2006 == req.getElementType()) {
			return getGEFWrapper(new RegularBoxCreateCommand(req));
		}
		if (NetworkElementTypes.Train_2007 == req.getElementType()) {
			return getGEFWrapper(new TrainCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	* @generated
	*/
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		* @generated
		*/
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
