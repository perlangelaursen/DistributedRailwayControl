/**
 */
package network;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.Segment#getControlBoxes <em>Control Boxes</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends Element {
	/**
	 * Returns the value of the '<em><b>Control Boxes</b></em>' reference list.
	 * The list contents are of type {@link network.ControlBox}.
	 * It is bidirectional and its opposite is '{@link network.ControlBox#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Boxes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Boxes</em>' reference list.
	 * @see network.NetworkPackage#getSegment_ControlBoxes()
	 * @see network.ControlBox#getSegments
	 * @model opposite="segments" lower="2" upper="2"
	 * @generated
	 */
	EList<ControlBox> getControlBoxes();

} // Segment
