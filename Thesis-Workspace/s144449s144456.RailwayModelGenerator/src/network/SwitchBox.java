/**
 */
package network;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.SwitchBox#getConnected <em>Connected</em>}</li>
 *   <li>{@link network.SwitchBox#getStem <em>Stem</em>}</li>
 *   <li>{@link network.SwitchBox#getPlus <em>Plus</em>}</li>
 *   <li>{@link network.SwitchBox#getMinus <em>Minus</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getSwitchBox()
 * @model
 * @generated
 */
public interface SwitchBox extends ControlBox {

	/**
	 * Returns the value of the '<em><b>Connected</b></em>' attribute.
	 * The literals are from the enumeration {@link network.PointSetting}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected</em>' attribute.
	 * @see network.PointSetting
	 * @see #setConnected(PointSetting)
	 * @see network.NetworkPackage#getSwitchBox_Connected()
	 * @model required="true"
	 * @generated
	 */
	PointSetting getConnected();

	/**
	 * Sets the value of the '{@link network.SwitchBox#getConnected <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connected</em>' attribute.
	 * @see network.PointSetting
	 * @see #getConnected()
	 * @generated
	 */
	void setConnected(PointSetting value);

	/**
	 * Returns the value of the '<em><b>Stem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stem</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stem</em>' reference.
	 * @see #setStem(Segment)
	 * @see network.NetworkPackage#getSwitchBox_Stem()
	 * @model required="true"
	 * @generated
	 */
	Segment getStem();

	/**
	 * Sets the value of the '{@link network.SwitchBox#getStem <em>Stem</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stem</em>' reference.
	 * @see #getStem()
	 * @generated
	 */
	void setStem(Segment value);

	/**
	 * Returns the value of the '<em><b>Plus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plus</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plus</em>' reference.
	 * @see #setPlus(Segment)
	 * @see network.NetworkPackage#getSwitchBox_Plus()
	 * @model required="true"
	 * @generated
	 */
	Segment getPlus();

	/**
	 * Sets the value of the '{@link network.SwitchBox#getPlus <em>Plus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plus</em>' reference.
	 * @see #getPlus()
	 * @generated
	 */
	void setPlus(Segment value);

	/**
	 * Returns the value of the '<em><b>Minus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minus</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minus</em>' reference.
	 * @see #setMinus(Segment)
	 * @see network.NetworkPackage#getSwitchBox_Minus()
	 * @model required="true"
	 * @generated
	 */
	Segment getMinus();

	/**
	 * Sets the value of the '{@link network.SwitchBox#getMinus <em>Minus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minus</em>' reference.
	 * @see #getMinus()
	 * @generated
	 */
	void setMinus(Segment value);
} // SwitchBox
