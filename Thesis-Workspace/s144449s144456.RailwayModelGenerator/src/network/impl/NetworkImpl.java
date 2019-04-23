/**
 */
package network.impl;

import java.util.Collection;

import network.ControlBox;
import network.Network;
import network.NetworkPackage;
import network.Segment;

import network.Train;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Network</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link network.impl.NetworkImpl#getControlBoxes <em>Control Boxes</em>}</li>
 *   <li>{@link network.impl.NetworkImpl#getSegments <em>Segments</em>}</li>
 *   <li>{@link network.impl.NetworkImpl#getTrains <em>Trains</em>}</li>
 *   <li>{@link network.impl.NetworkImpl#getLockLimit <em>Lock Limit</em>}</li>
 *   <li>{@link network.impl.NetworkImpl#getReserveLimit <em>Reserve Limit</em>}</li>
 *   <li>{@link network.impl.NetworkImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NetworkImpl extends MinimalEObjectImpl.Container implements Network {
	/**
	 * The cached value of the '{@link #getControlBoxes() <em>Control Boxes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlBoxes()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlBox> controlBoxes;

	/**
	 * The cached value of the '{@link #getSegments() <em>Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> segments;

	/**
	 * The cached value of the '{@link #getTrains() <em>Trains</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrains()
	 * @generated
	 * @ordered
	 */
	protected EList<Train> trains;

	/**
	 * The default value of the '{@link #getLockLimit() <em>Lock Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockLimit()
	 * @generated
	 * @ordered
	 */
	protected static final int LOCK_LIMIT_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getLockLimit() <em>Lock Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockLimit()
	 * @generated
	 * @ordered
	 */
	protected int lockLimit = LOCK_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getReserveLimit() <em>Reserve Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReserveLimit()
	 * @generated
	 * @ordered
	 */
	protected static final int RESERVE_LIMIT_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getReserveLimit() <em>Reserve Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReserveLimit()
	 * @generated
	 * @ordered
	 */
	protected int reserveLimit = RESERVE_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "untitled";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NetworkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NetworkPackage.Literals.NETWORK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ControlBox> getControlBoxes() {
		if (controlBoxes == null) {
			controlBoxes = new EObjectContainmentEList<ControlBox>(ControlBox.class, this, NetworkPackage.NETWORK__CONTROL_BOXES);
		}
		return controlBoxes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getSegments() {
		if (segments == null) {
			segments = new EObjectContainmentEList<Segment>(Segment.class, this, NetworkPackage.NETWORK__SEGMENTS);
		}
		return segments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Train> getTrains() {
		if (trains == null) {
			trains = new EObjectContainmentEList<Train>(Train.class, this, NetworkPackage.NETWORK__TRAINS);
		}
		return trains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLockLimit() {
		return lockLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLockLimit(int newLockLimit) {
		int oldLockLimit = lockLimit;
		lockLimit = newLockLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.NETWORK__LOCK_LIMIT, oldLockLimit, lockLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getReserveLimit() {
		return reserveLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReserveLimit(int newReserveLimit) {
		int oldReserveLimit = reserveLimit;
		reserveLimit = newReserveLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.NETWORK__RESERVE_LIMIT, oldReserveLimit, reserveLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.NETWORK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NetworkPackage.NETWORK__CONTROL_BOXES:
				return ((InternalEList<?>)getControlBoxes()).basicRemove(otherEnd, msgs);
			case NetworkPackage.NETWORK__SEGMENTS:
				return ((InternalEList<?>)getSegments()).basicRemove(otherEnd, msgs);
			case NetworkPackage.NETWORK__TRAINS:
				return ((InternalEList<?>)getTrains()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NetworkPackage.NETWORK__CONTROL_BOXES:
				return getControlBoxes();
			case NetworkPackage.NETWORK__SEGMENTS:
				return getSegments();
			case NetworkPackage.NETWORK__TRAINS:
				return getTrains();
			case NetworkPackage.NETWORK__LOCK_LIMIT:
				return getLockLimit();
			case NetworkPackage.NETWORK__RESERVE_LIMIT:
				return getReserveLimit();
			case NetworkPackage.NETWORK__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NetworkPackage.NETWORK__CONTROL_BOXES:
				getControlBoxes().clear();
				getControlBoxes().addAll((Collection<? extends ControlBox>)newValue);
				return;
			case NetworkPackage.NETWORK__SEGMENTS:
				getSegments().clear();
				getSegments().addAll((Collection<? extends Segment>)newValue);
				return;
			case NetworkPackage.NETWORK__TRAINS:
				getTrains().clear();
				getTrains().addAll((Collection<? extends Train>)newValue);
				return;
			case NetworkPackage.NETWORK__LOCK_LIMIT:
				setLockLimit((Integer)newValue);
				return;
			case NetworkPackage.NETWORK__RESERVE_LIMIT:
				setReserveLimit((Integer)newValue);
				return;
			case NetworkPackage.NETWORK__NAME:
				setName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NetworkPackage.NETWORK__CONTROL_BOXES:
				getControlBoxes().clear();
				return;
			case NetworkPackage.NETWORK__SEGMENTS:
				getSegments().clear();
				return;
			case NetworkPackage.NETWORK__TRAINS:
				getTrains().clear();
				return;
			case NetworkPackage.NETWORK__LOCK_LIMIT:
				setLockLimit(LOCK_LIMIT_EDEFAULT);
				return;
			case NetworkPackage.NETWORK__RESERVE_LIMIT:
				setReserveLimit(RESERVE_LIMIT_EDEFAULT);
				return;
			case NetworkPackage.NETWORK__NAME:
				setName(NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NetworkPackage.NETWORK__CONTROL_BOXES:
				return controlBoxes != null && !controlBoxes.isEmpty();
			case NetworkPackage.NETWORK__SEGMENTS:
				return segments != null && !segments.isEmpty();
			case NetworkPackage.NETWORK__TRAINS:
				return trains != null && !trains.isEmpty();
			case NetworkPackage.NETWORK__LOCK_LIMIT:
				return lockLimit != LOCK_LIMIT_EDEFAULT;
			case NetworkPackage.NETWORK__RESERVE_LIMIT:
				return reserveLimit != RESERVE_LIMIT_EDEFAULT;
			case NetworkPackage.NETWORK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (lockLimit: ");
		result.append(lockLimit);
		result.append(", reserveLimit: ");
		result.append(reserveLimit);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}
	

	/**
	 * @generated NOT
	 */
	public void reload() {
		for(ControlBox cb : controlBoxes) {
			cb.reload();
		}
		for(Train t : trains) {
			t.reload();
		}
	}

} //NetworkImpl
