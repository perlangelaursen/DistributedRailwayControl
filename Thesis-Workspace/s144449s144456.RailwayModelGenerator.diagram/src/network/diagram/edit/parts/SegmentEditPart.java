package network.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

import network.diagram.edit.policies.SegmentItemSemanticEditPolicy;

/**
 * @generated
 */
public class SegmentEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4004;

	/**
	* @generated
	*/
	public SegmentEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SegmentItemSemanticEditPolicy());
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/

	protected Connection createConnectionFigure() {
		return new SegmentFigure();
	}

	/**
	* @generated
	*/
	public SegmentFigure getPrimaryShape() {
		return (SegmentFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class SegmentFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public SegmentFigure() {

			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			WrappingLabel segmentOneWayIdFigure0 = new WrappingLabel();

			segmentOneWayIdFigure0.setText("<...>");

			this.add(segmentOneWayIdFigure0);

		}

	}

}
