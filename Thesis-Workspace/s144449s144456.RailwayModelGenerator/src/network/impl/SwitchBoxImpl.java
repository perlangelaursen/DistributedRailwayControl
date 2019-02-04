/**
 */
package network.impl;

import network.NetworkPackage;
import network.PointSetting;
import network.Segment;
import network.SwitchBox;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link network.impl.SwitchBoxImpl#getConnected <em>Connected</em>}</li>
 *   <li>{@link network.impl.SwitchBoxImpl#getStem <em>Stem</em>}</li>
 *   <li>{@link network.impl.SwitchBoxImpl#getPlus <em>Plus</em>}</li>
 *   <li>{@link network.impl.SwitchBoxImpl#getMinus <em>Minus</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchBoxImpl extends ControlBoxImpl implements SwitchBox {
	/**
	 * The default value of the '{@link #getConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnected()
	 * @generated
	 * @ordered
	 */
	protected static final PointSetting CONNECTED_EDEFAULT = PointSetting.PLUS;
	/**
	 * The cached value of the '{@link #getConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnected()
	 * @generated
	 * @ordered
	 */
	protected PointSetting connected = CONNECTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStem() <em>Stem</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStem()
	 * @generated
	 * @ordered
	 */
	protected Segment stem;
	/**
	 * The cached value of the '{@link #getPlus() <em>Plus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlus()
	 * @generated
	 * @ordered
	 */
	protected Segment plus;
	/**
	 * The cached value of the '{@link #getMinus() <em>Minus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinus()
	 * @generated
	 * @ordered
	 */
	protected Segment minus;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchBoxImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NetworkPackage.Literals.SWITCH_BOX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PointSetting getConnected() {
		return connected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnected(PointSetting newConnected) {
		PointSetting oldConnected = connected;
		connected = newConnected == null ? CONNECTED_EDEFAULT : newConnected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.SWITCH_BOX__CONNECTED, oldConnected, connected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Segment getStem() {
		if (stem != null && stem.eIsProxy()) {
			InternalEObject oldStem = (InternalEObject)stem;
			stem = (Segment)eResolveProxy(oldStem);
			if (stem != oldStem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NetworkPackage.SWITCH_BOX__STEM, oldStem, stem));
			}
		}
		return stem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment basicGetStem() {
		return stem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStem(Segment newStem) {
		Segment oldStem = stem;
		stem = newStem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.SWITCH_BOX__STEM, oldStem, stem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Segment getPlus() {
		if (plus != null && plus.eIsProxy()) {
			InternalEObject oldPlus = (InternalEObject)plus;
			plus = (Segment)eResolveProxy(oldPlus);
			if (plus != oldPlus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NetworkPackage.SWITCH_BOX__PLUS, oldPlus, plus));
			}
		}
		return plus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment basicGetPlus() {
		return plus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlus(Segment newPlus) {
		Segment oldPlus = plus;
		plus = newPlus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.SWITCH_BOX__PLUS, oldPlus, plus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Segment getMinus() {
		if (minus != null && minus.eIsProxy()) {
			InternalEObject oldMinus = (InternalEObject)minus;
			minus = (Segment)eResolveProxy(oldMinus);
			if (minus != oldMinus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NetworkPackage.SWITCH_BOX__MINUS, oldMinus, minus));
			}
		}
		return minus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment basicGetMinus() {
		return minus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinus(Segment newMinus) {
		Segment oldMinus = minus;
		minus = newMinus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.SWITCH_BOX__MINUS, oldMinus, minus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NetworkPackage.SWITCH_BOX__CONNECTED:
				return getConnected();
			case NetworkPackage.SWITCH_BOX__STEM:
				if (resolve) return getStem();
				return basicGetStem();
			case NetworkPackage.SWITCH_BOX__PLUS:
				if (resolve) return getPlus();
				return basicGetPlus();
			case NetworkPackage.SWITCH_BOX__MINUS:
				if (resolve) return getMinus();
				return basicGetMinus();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NetworkPackage.SWITCH_BOX__CONNECTED:
				setConnected((PointSetting)newValue);
				return;
			case NetworkPackage.SWITCH_BOX__STEM:
				setStem((Segment)newValue);
				return;
			case NetworkPackage.SWITCH_BOX__PLUS:
				setPlus((Segment)newValue);
				return;
			case NetworkPackage.SWITCH_BOX__MINUS:
				setMinus((Segment)newValue);
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
			case NetworkPackage.SWITCH_BOX__CONNECTED:
				setConnected(CONNECTED_EDEFAULT);
				return;
			case NetworkPackage.SWITCH_BOX__STEM:
				setStem((Segment)null);
				return;
			case NetworkPackage.SWITCH_BOX__PLUS:
				setPlus((Segment)null);
				return;
			case NetworkPackage.SWITCH_BOX__MINUS:
				setMinus((Segment)null);
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
			case NetworkPackage.SWITCH_BOX__CONNECTED:
				return connected != CONNECTED_EDEFAULT;
			case NetworkPackage.SWITCH_BOX__STEM:
				return stem != null;
			case NetworkPackage.SWITCH_BOX__PLUS:
				return plus != null;
			case NetworkPackage.SWITCH_BOX__MINUS:
				return minus != null;
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
		result.append(" (connected: ");
		result.append(connected);
		result.append(')');
		return result.toString();
	}

} //SwitchBoxImpl
