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
 *   <li>{@link network.Network#getReserveLimit <em>Reserve Limit</em>}</li>
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
	 * @model containment="true"
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
	 * @model containment="true"
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
	 * @model containment="true"
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
	 * Returns the value of the '<em><b>Reserve Limit</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reserve Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reserve Limit</em>' attribute.
	 * @see #setReserveLimit(int)
	 * @see network.NetworkPackage#getNetwork_ReserveLimit()
	 * @model default="1" required="true"
	 * @generated
	 */
	int getReserveLimit();

	/**
	 * Sets the value of the '{@link network.Network#getReserveLimit <em>Reserve Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reserve Limit</em>' attribute.
	 * @see #getReserveLimit()
	 * @generated
	 */
	void setReserveLimit(int value);

} // Network
