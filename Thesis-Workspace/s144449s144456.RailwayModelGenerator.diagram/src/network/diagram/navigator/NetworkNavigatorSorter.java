package network.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import network.diagram.part.NetworkVisualIDRegistry;

/**
 * @generated
 */
public class NetworkNavigatorSorter extends ViewerSorter {

	/**
	* @generated
	*/
	private static final int GROUP_CATEGORY = 4006;

	/**
	* @generated
	*/
	public int category(Object element) {
		if (element instanceof NetworkNavigatorItem) {
			NetworkNavigatorItem item = (NetworkNavigatorItem) element;
			return NetworkVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
