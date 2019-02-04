/**
 */
package network.util;

import network.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see network.NetworkPackage
 * @generated
 */
public class NetworkAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NetworkPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = NetworkPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NetworkSwitch<Adapter> modelSwitch =
		new NetworkSwitch<Adapter>() {
			@Override
			public Adapter caseNetwork(Network object) {
				return createNetworkAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseControlBox(ControlBox object) {
				return createControlBoxAdapter();
			}
			@Override
			public Adapter caseSegment(Segment object) {
				return createSegmentAdapter();
			}
			@Override
			public Adapter caseSegmentOneWay(SegmentOneWay object) {
				return createSegmentOneWayAdapter();
			}
			@Override
			public Adapter caseSegmentTwoWay(SegmentTwoWay object) {
				return createSegmentTwoWayAdapter();
			}
			@Override
			public Adapter caseSwitchBox(SwitchBox object) {
				return createSwitchBoxAdapter();
			}
			@Override
			public Adapter caseRegularBox(RegularBox object) {
				return createRegularBoxAdapter();
			}
			@Override
			public Adapter caseTrain(Train object) {
				return createTrainAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link network.Network <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.Network
	 * @generated
	 */
	public Adapter createNetworkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.ControlBox <em>Control Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.ControlBox
	 * @generated
	 */
	public Adapter createControlBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.Segment
	 * @generated
	 */
	public Adapter createSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.SegmentOneWay <em>Segment One Way</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.SegmentOneWay
	 * @generated
	 */
	public Adapter createSegmentOneWayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.SegmentTwoWay <em>Segment Two Way</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.SegmentTwoWay
	 * @generated
	 */
	public Adapter createSegmentTwoWayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.SwitchBox <em>Switch Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.SwitchBox
	 * @generated
	 */
	public Adapter createSwitchBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.RegularBox <em>Regular Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.RegularBox
	 * @generated
	 */
	public Adapter createRegularBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link network.Train <em>Train</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see network.Train
	 * @generated
	 */
	public Adapter createTrainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //NetworkAdapterFactory
