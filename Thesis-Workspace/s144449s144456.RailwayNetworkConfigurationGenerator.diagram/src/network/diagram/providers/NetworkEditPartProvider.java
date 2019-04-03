package network.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import network.diagram.edit.parts.NetworkEditPart;
import network.diagram.edit.parts.NetworkEditPartFactory;
import network.diagram.part.NetworkVisualIDRegistry;

/**
 * @generated
 */
public class NetworkEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public NetworkEditPartProvider() {
		super(new NetworkEditPartFactory(), NetworkVisualIDRegistry.TYPED_INSTANCE, NetworkEditPart.MODEL_ID);
	}

}
