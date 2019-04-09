/**
 */
package network;

import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Train</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.Train#getRoute <em>Route</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getTrain()
 * @model
 * @generated
 */
public interface Train extends Component {
	/**
	 * Returns the value of the '<em><b>Route</b></em>' reference list.
	 * The list contents are of type {@link network.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route</em>' reference list.
	 * @see network.NetworkPackage#getTrain_Route()
	 * @model required="true"
	 * @generated
	 */
	EList<Segment> getRoute();

	/**
	 * @generated NOT
	 */
	LinkedList<ControlBox> getBoxRoute();
} // Train
