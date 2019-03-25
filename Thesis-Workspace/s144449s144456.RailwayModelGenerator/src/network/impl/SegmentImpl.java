/**
 */
package network.impl;

import java.util.Collection;
import network.ControlBox;
import network.NetworkPackage;
import network.Segment;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link network.impl.SegmentImpl#getControlBoxes <em>Control Boxes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SegmentImpl extends ElementImpl implements Segment {
	/**
	 * The cached value of the '{@link #getControlBoxes() <em>Control Boxes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlBoxes()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlBox> controlBoxes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NetworkPackage.Literals.SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ControlBox> getControlBoxes() {
		if (controlBoxes == null) {
			controlBoxes = new EObjectWithInverseResolvingEList.ManyInverse<ControlBox>(ControlBox.class, this, NetworkPackage.SEGMENT__CONTROL_BOXES, NetworkPackage.CONTROL_BOX__SEGMENTS);
		}
		return controlBoxes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getControlBoxes()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				return ((InternalEList<?>)getControlBoxes()).basicRemove(otherEnd, msgs);
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
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				return getControlBoxes();
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
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				getControlBoxes().clear();
				getControlBoxes().addAll((Collection<? extends ControlBox>)newValue);
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
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				getControlBoxes().clear();
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
			case NetworkPackage.SEGMENT__CONTROL_BOXES:
				return controlBoxes != null && !controlBoxes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SegmentImpl
