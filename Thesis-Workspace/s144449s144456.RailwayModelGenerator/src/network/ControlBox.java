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
 *   <li>{@link network.ControlBox#getIngoing <em>Ingoing</em>}</li>
 *   <li>{@link network.ControlBox#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getControlBox()
 * @model
 * @generated
 */
public interface ControlBox extends Element {

	/**
	 * Returns the value of the '<em><b>Ingoing</b></em>' reference list.
	 * The list contents are of type {@link network.Segment}.
	 * It is bidirectional and its opposite is '{@link network.Segment#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ingoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ingoing</em>' reference list.
	 * @see network.NetworkPackage#getControlBox_Ingoing()
	 * @see network.Segment#getEnd
	 * @model opposite="end" upper="3"
	 * @generated
	 */
	EList<Segment> getIngoing();

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link network.Segment}.
	 * It is bidirectional and its opposite is '{@link network.Segment#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see network.NetworkPackage#getControlBox_Outgoing()
	 * @see network.Segment#getStart
	 * @model opposite="start" upper="3"
	 * @generated
	 */
	EList<Segment> getOutgoing();
} // ControlBox
