/**
 */
package network.impl;

import java.util.Collection;
import java.util.LinkedList;

import network.NetworkPackage;
import network.Segment;
import network.Train;
import network.ControlBox;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Train</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link network.impl.TrainImpl#getRoute <em>Route</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TrainImpl extends ComponentImpl implements Train {
	/**
	 * The cached value of the '{@link #getRoute() <em>Route</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> route;
	
	/**
	 * @generated NOT
	 */
	private LinkedList<ControlBox> boxRoute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NetworkPackage.Literals.TRAIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getRoute() {
		if (route == null) {
			route = new EObjectResolvingEList<Segment>(Segment.class, this, NetworkPackage.TRAIN__ROUTE);
		}
		return route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NetworkPackage.TRAIN__ROUTE:
				return getRoute();
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
			case NetworkPackage.TRAIN__ROUTE:
				getRoute().clear();
				getRoute().addAll((Collection<? extends Segment>)newValue);
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
			case NetworkPackage.TRAIN__ROUTE:
				getRoute().clear();
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
			case NetworkPackage.TRAIN__ROUTE:
				return route != null && !route.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * @generated NOT
	 */
	private ControlBox[] getSegmentBoxes(Segment s, Segment s2) { //0 = first, 1 = shared, 2 = last
		ControlBox[] cbs = new ControlBox[3];
		if(s.getStart() == s2.getStart()) {
			cbs[0] = s.getEnd();
			cbs[1] = s.getStart();
			cbs[2] = s2.getEnd();
		} else if (s.getStart() == s2.getEnd()) {
			cbs[0] = s.getEnd();
			cbs[1] = s.getStart();
			cbs[2] = s2.getStart();
		} else if (s.getEnd() == s2.getStart()) {
			cbs[0] = s.getStart();
			cbs[1] = s.getEnd();
			cbs[2] = s2.getEnd();
		} else {
			cbs[0] = s.getStart();
			cbs[1] = s.getEnd();
			cbs[2] = s2.getStart();
		}
		return cbs;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public LinkedList<ControlBox> getBoxRoute() {
		if(boxRoute == null) {
			LinkedList<ControlBox> l = new LinkedList<>();
			ControlBox[] cbs;
			if(getRoute().size() > 1) {
				for(int j = 0; j < getRoute().size()-1; j++) {
					cbs = getSegmentBoxes(getRoute().get(j), getRoute().get(j+1));
					l.add(cbs[0]);
					if(j == getRoute().size()-2) {
						l.add(cbs[1]);
						l.add(cbs[2]);
					}
				}
			} else {
				l.add(getRoute().get(0).getStart());
				l.add(getRoute().get(0).getEnd());
			}
			boxRoute = l;
		}
		return boxRoute;
	}
	
	/**
	 * @generated NOT
	 */
	public void reload() {
		boxRoute = null;
	}
} //TrainImpl
