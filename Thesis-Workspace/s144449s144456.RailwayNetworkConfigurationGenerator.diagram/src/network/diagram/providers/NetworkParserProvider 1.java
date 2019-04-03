package network.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import network.NetworkPackage;
import network.diagram.edit.parts.RegularBoxIdEditPart;
import network.diagram.edit.parts.SegmentIdEditPart;
import network.diagram.edit.parts.SwitchBoxIdEditPart;
import network.diagram.edit.parts.TrainIdEditPart;
import network.diagram.parsers.MessageFormatParser;
import network.diagram.part.NetworkVisualIDRegistry;

/**
 * @generated
 */
public class NetworkParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser switchBoxId_5005Parser;

	/**
	* @generated
	*/
	private IParser getSwitchBoxId_5005Parser() {
		if (switchBoxId_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getComponent_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			switchBoxId_5005Parser = parser;
		}
		return switchBoxId_5005Parser;
	}

	/**
	* @generated
	*/
	private IParser regularBoxId_5006Parser;

	/**
	* @generated
	*/
	private IParser getRegularBoxId_5006Parser() {
		if (regularBoxId_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getComponent_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			regularBoxId_5006Parser = parser;
		}
		return regularBoxId_5006Parser;
	}

	/**
	* @generated
	*/
	private IParser trainId_5007Parser;

	/**
	* @generated
	*/
	private IParser getTrainId_5007Parser() {
		if (trainId_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getComponent_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			trainId_5007Parser = parser;
		}
		return trainId_5007Parser;
	}

	/**
	* @generated
	*/
	private IParser segmentId_6003Parser;

	/**
	* @generated
	*/
	private IParser getSegmentId_6003Parser() {
		if (segmentId_6003Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getComponent_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			segmentId_6003Parser = parser;
		}
		return segmentId_6003Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case SwitchBoxIdEditPart.VISUAL_ID:
			return getSwitchBoxId_5005Parser();
		case RegularBoxIdEditPart.VISUAL_ID:
			return getRegularBoxId_5006Parser();
		case TrainIdEditPart.VISUAL_ID:
			return getTrainId_5007Parser();
		case SegmentIdEditPart.VISUAL_ID:
			return getSegmentId_6003Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(NetworkVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(NetworkVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (NetworkElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
