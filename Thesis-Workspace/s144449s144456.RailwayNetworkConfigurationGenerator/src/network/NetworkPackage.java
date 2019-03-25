/**
 */
package network;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see network.NetworkFactory
 * @model kind="package"
 * @generated
 */
public interface NetworkPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "network";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://s144449s144456.RailwayNetworkConfigurationGenerator/network";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "nw";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NetworkPackage eINSTANCE = network.impl.NetworkPackageImpl.init();

	/**
	 * The meta object id for the '{@link network.impl.NetworkImpl <em>Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.NetworkImpl
	 * @see network.impl.NetworkPackageImpl#getNetwork()
	 * @generated
	 */
	int NETWORK = 0;

	/**
	 * The feature id for the '<em><b>Control Boxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__CONTROL_BOXES = 0;

	/**
	 * The feature id for the '<em><b>Segments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__SEGMENTS = 1;

	/**
	 * The feature id for the '<em><b>Trains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__TRAINS = 2;

	/**
	 * The feature id for the '<em><b>Lock Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__LOCK_LIMIT = 3;

	/**
	 * The feature id for the '<em><b>Reserve Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__RESERVE_LIMIT = 4;

	/**
	 * The number of structural features of the '<em>Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link network.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.ElementImpl
	 * @see network.impl.NetworkPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__ID = 0;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link network.impl.ControlBoxImpl <em>Control Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.ControlBoxImpl
	 * @see network.impl.NetworkPackageImpl#getControlBox()
	 * @generated
	 */
	int CONTROL_BOX = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_BOX__ID = ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Ingoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_BOX__INGOING = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_BOX__OUTGOING = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Control Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_BOX_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Control Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_BOX_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link network.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.SegmentImpl
	 * @see network.impl.NetworkPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__ID = ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__START = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__END = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link network.impl.SwitchBoxImpl <em>Switch Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.SwitchBoxImpl
	 * @see network.impl.NetworkPackageImpl#getSwitchBox()
	 * @generated
	 */
	int SWITCH_BOX = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__ID = CONTROL_BOX__ID;

	/**
	 * The feature id for the '<em><b>Ingoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__INGOING = CONTROL_BOX__INGOING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__OUTGOING = CONTROL_BOX__OUTGOING;

	/**
	 * The feature id for the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__CONNECTED = CONTROL_BOX_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__STEM = CONTROL_BOX_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Plus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__PLUS = CONTROL_BOX_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Minus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX__MINUS = CONTROL_BOX_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Switch Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX_FEATURE_COUNT = CONTROL_BOX_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Switch Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_BOX_OPERATION_COUNT = CONTROL_BOX_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link network.impl.RegularBoxImpl <em>Regular Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.RegularBoxImpl
	 * @see network.impl.NetworkPackageImpl#getRegularBox()
	 * @generated
	 */
	int REGULAR_BOX = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULAR_BOX__ID = CONTROL_BOX__ID;

	/**
	 * The feature id for the '<em><b>Ingoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULAR_BOX__INGOING = CONTROL_BOX__INGOING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULAR_BOX__OUTGOING = CONTROL_BOX__OUTGOING;

	/**
	 * The number of structural features of the '<em>Regular Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULAR_BOX_FEATURE_COUNT = CONTROL_BOX_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Regular Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULAR_BOX_OPERATION_COUNT = CONTROL_BOX_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link network.impl.TrainImpl <em>Train</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.impl.TrainImpl
	 * @see network.impl.NetworkPackageImpl#getTrain()
	 * @generated
	 */
	int TRAIN = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIN__ID = ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Route</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIN__ROUTE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Train</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIN_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Train</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIN_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link network.PointSetting <em>Point Setting</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see network.PointSetting
	 * @see network.impl.NetworkPackageImpl#getPointSetting()
	 * @generated
	 */
	int POINT_SETTING = 7;


	/**
	 * Returns the meta object for class '{@link network.Network <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network</em>'.
	 * @see network.Network
	 * @generated
	 */
	EClass getNetwork();

	/**
	 * Returns the meta object for the containment reference list '{@link network.Network#getControlBoxes <em>Control Boxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Control Boxes</em>'.
	 * @see network.Network#getControlBoxes()
	 * @see #getNetwork()
	 * @generated
	 */
	EReference getNetwork_ControlBoxes();

	/**
	 * Returns the meta object for the containment reference list '{@link network.Network#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Segments</em>'.
	 * @see network.Network#getSegments()
	 * @see #getNetwork()
	 * @generated
	 */
	EReference getNetwork_Segments();

	/**
	 * Returns the meta object for the containment reference list '{@link network.Network#getTrains <em>Trains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trains</em>'.
	 * @see network.Network#getTrains()
	 * @see #getNetwork()
	 * @generated
	 */
	EReference getNetwork_Trains();

	/**
	 * Returns the meta object for the attribute '{@link network.Network#getLockLimit <em>Lock Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock Limit</em>'.
	 * @see network.Network#getLockLimit()
	 * @see #getNetwork()
	 * @generated
	 */
	EAttribute getNetwork_LockLimit();

	/**
	 * Returns the meta object for the attribute '{@link network.Network#getReserveLimit <em>Reserve Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reserve Limit</em>'.
	 * @see network.Network#getReserveLimit()
	 * @see #getNetwork()
	 * @generated
	 */
	EAttribute getNetwork_ReserveLimit();

	/**
	 * Returns the meta object for class '{@link network.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see network.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the attribute '{@link network.Element#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see network.Element#getId()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Id();

	/**
	 * Returns the meta object for class '{@link network.ControlBox <em>Control Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Box</em>'.
	 * @see network.ControlBox
	 * @generated
	 */
	EClass getControlBox();

	/**
	 * Returns the meta object for the reference list '{@link network.ControlBox#getIngoing <em>Ingoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ingoing</em>'.
	 * @see network.ControlBox#getIngoing()
	 * @see #getControlBox()
	 * @generated
	 */
	EReference getControlBox_Ingoing();

	/**
	 * Returns the meta object for the reference list '{@link network.ControlBox#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing</em>'.
	 * @see network.ControlBox#getOutgoing()
	 * @see #getControlBox()
	 * @generated
	 */
	EReference getControlBox_Outgoing();

	/**
	 * Returns the meta object for class '{@link network.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see network.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the reference '{@link network.Segment#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start</em>'.
	 * @see network.Segment#getStart()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_Start();

	/**
	 * Returns the meta object for the reference '{@link network.Segment#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End</em>'.
	 * @see network.Segment#getEnd()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_End();

	/**
	 * Returns the meta object for class '{@link network.SwitchBox <em>Switch Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Box</em>'.
	 * @see network.SwitchBox
	 * @generated
	 */
	EClass getSwitchBox();

	/**
	 * Returns the meta object for the attribute '{@link network.SwitchBox#getConnected <em>Connected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connected</em>'.
	 * @see network.SwitchBox#getConnected()
	 * @see #getSwitchBox()
	 * @generated
	 */
	EAttribute getSwitchBox_Connected();

	/**
	 * Returns the meta object for the reference '{@link network.SwitchBox#getStem <em>Stem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Stem</em>'.
	 * @see network.SwitchBox#getStem()
	 * @see #getSwitchBox()
	 * @generated
	 */
	EReference getSwitchBox_Stem();

	/**
	 * Returns the meta object for the reference '{@link network.SwitchBox#getPlus <em>Plus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Plus</em>'.
	 * @see network.SwitchBox#getPlus()
	 * @see #getSwitchBox()
	 * @generated
	 */
	EReference getSwitchBox_Plus();

	/**
	 * Returns the meta object for the reference '{@link network.SwitchBox#getMinus <em>Minus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Minus</em>'.
	 * @see network.SwitchBox#getMinus()
	 * @see #getSwitchBox()
	 * @generated
	 */
	EReference getSwitchBox_Minus();

	/**
	 * Returns the meta object for class '{@link network.RegularBox <em>Regular Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regular Box</em>'.
	 * @see network.RegularBox
	 * @generated
	 */
	EClass getRegularBox();

	/**
	 * Returns the meta object for class '{@link network.Train <em>Train</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Train</em>'.
	 * @see network.Train
	 * @generated
	 */
	EClass getTrain();

	/**
	 * Returns the meta object for the reference list '{@link network.Train#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Route</em>'.
	 * @see network.Train#getRoute()
	 * @see #getTrain()
	 * @generated
	 */
	EReference getTrain_Route();

	/**
	 * Returns the meta object for enum '{@link network.PointSetting <em>Point Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Point Setting</em>'.
	 * @see network.PointSetting
	 * @generated
	 */
	EEnum getPointSetting();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NetworkFactory getNetworkFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link network.impl.NetworkImpl <em>Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.NetworkImpl
		 * @see network.impl.NetworkPackageImpl#getNetwork()
		 * @generated
		 */
		EClass NETWORK = eINSTANCE.getNetwork();

		/**
		 * The meta object literal for the '<em><b>Control Boxes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK__CONTROL_BOXES = eINSTANCE.getNetwork_ControlBoxes();

		/**
		 * The meta object literal for the '<em><b>Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK__SEGMENTS = eINSTANCE.getNetwork_Segments();

		/**
		 * The meta object literal for the '<em><b>Trains</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK__TRAINS = eINSTANCE.getNetwork_Trains();

		/**
		 * The meta object literal for the '<em><b>Lock Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK__LOCK_LIMIT = eINSTANCE.getNetwork_LockLimit();

		/**
		 * The meta object literal for the '<em><b>Reserve Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NETWORK__RESERVE_LIMIT = eINSTANCE.getNetwork_ReserveLimit();

		/**
		 * The meta object literal for the '{@link network.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.ElementImpl
		 * @see network.impl.NetworkPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__ID = eINSTANCE.getElement_Id();

		/**
		 * The meta object literal for the '{@link network.impl.ControlBoxImpl <em>Control Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.ControlBoxImpl
		 * @see network.impl.NetworkPackageImpl#getControlBox()
		 * @generated
		 */
		EClass CONTROL_BOX = eINSTANCE.getControlBox();

		/**
		 * The meta object literal for the '<em><b>Ingoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_BOX__INGOING = eINSTANCE.getControlBox_Ingoing();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_BOX__OUTGOING = eINSTANCE.getControlBox_Outgoing();

		/**
		 * The meta object literal for the '{@link network.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.SegmentImpl
		 * @see network.impl.NetworkPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__START = eINSTANCE.getSegment_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__END = eINSTANCE.getSegment_End();

		/**
		 * The meta object literal for the '{@link network.impl.SwitchBoxImpl <em>Switch Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.SwitchBoxImpl
		 * @see network.impl.NetworkPackageImpl#getSwitchBox()
		 * @generated
		 */
		EClass SWITCH_BOX = eINSTANCE.getSwitchBox();

		/**
		 * The meta object literal for the '<em><b>Connected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH_BOX__CONNECTED = eINSTANCE.getSwitchBox_Connected();

		/**
		 * The meta object literal for the '<em><b>Stem</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_BOX__STEM = eINSTANCE.getSwitchBox_Stem();

		/**
		 * The meta object literal for the '<em><b>Plus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_BOX__PLUS = eINSTANCE.getSwitchBox_Plus();

		/**
		 * The meta object literal for the '<em><b>Minus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_BOX__MINUS = eINSTANCE.getSwitchBox_Minus();

		/**
		 * The meta object literal for the '{@link network.impl.RegularBoxImpl <em>Regular Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.RegularBoxImpl
		 * @see network.impl.NetworkPackageImpl#getRegularBox()
		 * @generated
		 */
		EClass REGULAR_BOX = eINSTANCE.getRegularBox();

		/**
		 * The meta object literal for the '{@link network.impl.TrainImpl <em>Train</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.impl.TrainImpl
		 * @see network.impl.NetworkPackageImpl#getTrain()
		 * @generated
		 */
		EClass TRAIN = eINSTANCE.getTrain();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRAIN__ROUTE = eINSTANCE.getTrain_Route();

		/**
		 * The meta object literal for the '{@link network.PointSetting <em>Point Setting</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see network.PointSetting
		 * @see network.impl.NetworkPackageImpl#getPointSetting()
		 * @generated
		 */
		EEnum POINT_SETTING = eINSTANCE.getPointSetting();

	}

} //NetworkPackage
