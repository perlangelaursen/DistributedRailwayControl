/**
 */
package network;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.ControlBox#getSegments <em>Segments</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getControlBox()
 * @model abstract="true"
 * @generated
 */
public interface ControlBox extends Element {
	/**
	 * Returns the value of the '<em><b>Segments</b></em>' reference list.
	 * The list contents are of type {@link network.Segment}.
	 * It is bidirectional and its opposite is '{@link network.Segment#getControlBoxes <em>Control Boxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' reference list.
	 * @see network.NetworkPackage#getControlBox_Segments()
	 * @see network.Segment#getControlBoxes
	 * @model opposite="controlBoxes" required="true" upper="3"
	 * @generated
	 */
	EList<Segment> getSegments();

} // ControlBox
