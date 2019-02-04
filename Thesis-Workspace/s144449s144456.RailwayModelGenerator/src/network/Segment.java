/**
 */
package network;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.Segment#getStart <em>Start</em>}</li>
 *   <li>{@link network.Segment#getEnd <em>End</em>}</li>
 *   <li>{@link network.Segment#isTrain <em>Train</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getSegment()
 * @model abstract="true"
 * @generated
 */
public interface Segment extends Element {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link network.ControlBox#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' reference.
	 * @see #setStart(ControlBox)
	 * @see network.NetworkPackage#getSegment_Start()
	 * @see network.ControlBox#getOutgoing
	 * @model opposite="outgoing" required="true"
	 * @generated
	 */
	ControlBox getStart();

	/**
	 * Sets the value of the '{@link network.Segment#getStart <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(ControlBox value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link network.ControlBox#getIngoing <em>Ingoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' reference.
	 * @see #setEnd(ControlBox)
	 * @see network.NetworkPackage#getSegment_End()
	 * @see network.ControlBox#getIngoing
	 * @model opposite="ingoing" required="true"
	 * @generated
	 */
	ControlBox getEnd();

	/**
	 * Sets the value of the '{@link network.Segment#getEnd <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(ControlBox value);

	/**
	 * Returns the value of the '<em><b>Train</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Train</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Train</em>' attribute.
	 * @see #setTrain(boolean)
	 * @see network.NetworkPackage#getSegment_Train()
	 * @model
	 * @generated
	 */
	boolean isTrain();

	/**
	 * Sets the value of the '{@link network.Segment#isTrain <em>Train</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Train</em>' attribute.
	 * @see #isTrain()
	 * @generated
	 */
	void setTrain(boolean value);

} // Segment
