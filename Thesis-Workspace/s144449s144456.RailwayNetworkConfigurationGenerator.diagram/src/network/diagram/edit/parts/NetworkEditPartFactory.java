package network.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import network.diagram.part.NetworkVisualIDRegistry;

/**
 * @generated
 */
public class NetworkEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (NetworkVisualIDRegistry.getVisualID(view)) {

			case NetworkEditPart.VISUAL_ID:
				return new NetworkEditPart(view);

			case SwitchBoxEditPart.VISUAL_ID:
				return new SwitchBoxEditPart(view);

			case SwitchBoxIdEditPart.VISUAL_ID:
				return new SwitchBoxIdEditPart(view);

			case RegularBoxEditPart.VISUAL_ID:
				return new RegularBoxEditPart(view);

			case RegularBoxIdEditPart.VISUAL_ID:
				return new RegularBoxIdEditPart(view);

			case TrainEditPart.VISUAL_ID:
				return new TrainEditPart(view);

			case TrainIdEditPart.VISUAL_ID:
				return new TrainIdEditPart(view);

			case SegmentEditPart.VISUAL_ID:
				return new SegmentEditPart(view);

			case SegmentIdEditPart.VISUAL_ID:
				return new SegmentIdEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	* @generated
	*/
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* @generated
	*/
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
