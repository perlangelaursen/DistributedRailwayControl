package network.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import network.diagram.providers.NetworkElementTypes;
import network.diagram.providers.NetworkModelingAssistantProvider;

/**
 * @generated
 */
public class NetworkModelingAssistantProviderOfNetworkEditPart extends NetworkModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(NetworkElementTypes.SwitchBox_2005);
		types.add(NetworkElementTypes.RegularBox_2006);
		types.add(NetworkElementTypes.Train_2007);
		return types;
	}

}
