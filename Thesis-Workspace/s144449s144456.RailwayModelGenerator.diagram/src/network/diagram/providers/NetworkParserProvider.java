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
import network.diagram.edit.parts.SegmentOneWayIdEditPart;
import network.diagram.edit.parts.SegmentTwoWayIdEditPart;
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
	private IParser switchBoxId_5001Parser;

	/**
	* @generated
	*/
	private IParser getSwitchBoxId_5001Parser() {
		if (switchBoxId_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getElement_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			switchBoxId_5001Parser = parser;
		}
		return switchBoxId_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser regularBoxId_5002Parser;

	/**
	* @generated
	*/
	private IParser getRegularBoxId_5002Parser() {
		if (regularBoxId_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getElement_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			regularBoxId_5002Parser = parser;
		}
		return regularBoxId_5002Parser;
	}

	/**
	* @generated
	*/
	private IParser trainId_5004Parser;

	/**
	* @generated
	*/
	private IParser getTrainId_5004Parser() {
		if (trainId_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getElement_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			trainId_5004Parser = parser;
		}
		return trainId_5004Parser;
	}

	/**
	* @generated
	*/
	private IParser segmentOneWayId_6001Parser;

	/**
	* @generated
	*/
	private IParser getSegmentOneWayId_6001Parser() {
		if (segmentOneWayId_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getElement_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			segmentOneWayId_6001Parser = parser;
		}
		return segmentOneWayId_6001Parser;
	}

	/**
	* @generated
	*/
	private IParser segmentTwoWayId_6002Parser;

	/**
	* @generated
	*/
	private IParser getSegmentTwoWayId_6002Parser() {
		if (segmentTwoWayId_6002Parser == null) {
			EAttribute[] features = new EAttribute[] { NetworkPackage.eINSTANCE.getElement_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			segmentTwoWayId_6002Parser = parser;
		}
		return segmentTwoWayId_6002Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case SwitchBoxIdEditPart.VISUAL_ID:
			return getSwitchBoxId_5001Parser();
		case RegularBoxIdEditPart.VISUAL_ID:
			return getRegularBoxId_5002Parser();
		case TrainIdEditPart.VISUAL_ID:
			return getTrainId_5004Parser();
		case SegmentOneWayIdEditPart.VISUAL_ID:
			return getSegmentOneWayId_6001Parser();
		case SegmentTwoWayIdEditPart.VISUAL_ID:
			return getSegmentTwoWayId_6002Parser();
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
