/**
 */
package network;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Network</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link network.Network#getControlBoxes <em>Control Boxes</em>}</li>
 *   <li>{@link network.Network#getSegments <em>Segments</em>}</li>
 *   <li>{@link network.Network#getTrains <em>Trains</em>}</li>
 *   <li>{@link network.Network#getLockLimit <em>Lock Limit</em>}</li>
 *   <li>{@link network.Network#getReservationLimit <em>Reservation Limit</em>}</li>
 *   <li>{@link network.Network#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see network.NetworkPackage#getNetwork()
 * @model
 * @generated
 */
public interface Network extends EObject {
	/**
	 * Returns the value of the '<em><b>Control Boxes</b></em>' containment reference list.
	 * The list contents are of type {@link network.ControlBox}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Boxes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Boxes</em>' containment reference list.
	 * @see network.NetworkPackage#getNetwork_ControlBoxes()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<ControlBox> getControlBoxes();

	/**
	 * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
	 * The list contents are of type {@link network.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' containment reference list.
	 * @see network.NetworkPackage#getNetwork_Segments()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Segment> getSegments();

	/**
	 * Returns the value of the '<em><b>Trains</b></em>' containment reference list.
	 * The list contents are of type {@link network.Train}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trains</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trains</em>' containment reference list.
	 * @see network.NetworkPackage#getNetwork_Trains()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Train> getTrains();

	/**
	 * Returns the value of the '<em><b>Lock Limit</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock Limit</em>' attribute.
	 * @see #setLockLimit(int)
	 * @see network.NetworkPackage#getNetwork_LockLimit()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getLockLimit();

	/**
	 * Sets the value of the '{@link network.Network#getLockLimit <em>Lock Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock Limit</em>' attribute.
	 * @see #getLockLimit()
	 * @generated
	 */
	void setLockLimit(int value);

	/**
	 * Returns the value of the '<em><b>Reservation Limit</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reservation Limit</em>' attribute.
	 * @see #setReservationLimit(int)
	 * @see network.NetworkPackage#getNetwork_ReservationLimit()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getReservationLimit();

	/**
	 * Sets the value of the '{@link network.Network#getReservationLimit <em>Reservation Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reservation Limit</em>' attribute.
	 * @see #getReservationLimit()
	 * @generated
	 */
	void setReservationLimit(int value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>"untitled"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see network.NetworkPackage#getNetwork_Name()
	 * @model default="untitled" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link network.Network#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @generated NOT
	 */
	void reload();

} // Network
