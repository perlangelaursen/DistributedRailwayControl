/**
 */
package network;

import java.util.LinkedList;

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
 *   <li>{@link network.ControlBox#getX <em>X</em>}</li>
 *   <li>{@link network.ControlBox#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getControlBox()
 * @model abstract="true"
 * @generated
 */
public interface ControlBox extends Component {
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

	
	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(float)
	 * @see network.NetworkPackage#getControlBox_X()
	 * @model required="true"
	 * @generated
	 */
	float getX();

	/**
	 * Sets the value of the '{@link network.ControlBox#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(float value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(float)
	 * @see network.NetworkPackage#getControlBox_Y()
	 * @model required="true"
	 * @generated
	 */
	float getY();

	/**
	 * Sets the value of the '{@link network.ControlBox#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(float value);

	/**
	 * Returns the segments associated with a control box
	 * @return list of associated segment IDs
	 * @generated NOT
	 */
	
	LinkedList<Segment> getSegments();

	/**
	 * @generated NOT
	 */
	void reload();
} // ControlBox
