/**
 */
package network.impl;

import java.util.Collection;
import java.util.LinkedList;

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
 * An implementation of the model object '<em><b>Control Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link network.impl.ControlBoxImpl#getIngoing <em>Ingoing</em>}</li>
 *   <li>{@link network.impl.ControlBoxImpl#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ControlBoxImpl extends ElementImpl implements ControlBox {
	/**
	 * The cached value of the '{@link #getIngoing() <em>Ingoing</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIngoing()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> ingoing;

	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> outgoing;

	/**
	 * @see #getSegments()
	 * @generated NOT
	 */
	protected LinkedList<Segment> segments;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlBoxImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NetworkPackage.Literals.CONTROL_BOX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getIngoing() {
		if (ingoing == null) {
			ingoing = new EObjectWithInverseResolvingEList<Segment>(Segment.class, this, NetworkPackage.CONTROL_BOX__INGOING, NetworkPackage.SEGMENT__END);
		}
		return ingoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getOutgoing() {
		if (outgoing == null) {
			outgoing = new EObjectWithInverseResolvingEList<Segment>(Segment.class, this, NetworkPackage.CONTROL_BOX__OUTGOING, NetworkPackage.SEGMENT__START);
		}
		return outgoing;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public LinkedList<Segment> getSegments() {
		if(segments == null) {
			segments = new LinkedList<>();
			EList<Segment> i = getIngoing();
			EList<Segment> o = getOutgoing();
			for(int j = 0; j < i.size(); j++) {
				segments.add(i.get(j));
			}
			for(int j = 0; j < o.size(); j++) {
				segments.add(o.get(j));
			}
		}
		return segments;
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIngoing()).basicAdd(otherEnd, msgs);
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				return ((InternalEList<?>)getIngoing()).basicRemove(otherEnd, msgs);
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				return getIngoing();
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				return getOutgoing();
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				getIngoing().clear();
				getIngoing().addAll((Collection<? extends Segment>)newValue);
				return;
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends Segment>)newValue);
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				getIngoing().clear();
				return;
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				getOutgoing().clear();
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
			case NetworkPackage.CONTROL_BOX__INGOING:
				return ingoing != null && !ingoing.isEmpty();
			case NetworkPackage.CONTROL_BOX__OUTGOING:
				return outgoing != null && !outgoing.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ControlBoxImpl
