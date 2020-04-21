package network.DiagramListener;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import network.Component;
import network.ControlBox;
import network.Network;
import network.SwitchBox;
import network.Train;
/**
 * @generated NOT
 *
 */
public class DiagramListener extends EContentAdapter{
	private NotificationFilter ModifiedFilter;
	
	public DiagramListener() {
		ModifiedFilter = NotificationFilter.createEventTypeFilter(
				Notification.SET).or(
				NotificationFilter.createEventTypeFilter(Notification.UNSET))
				.or(NotificationFilter.createEventTypeFilter(
				Notification.ADD)).
				or(NotificationFilter.createEventTypeFilter(
						Notification.REMOVE));
	}

	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof EObject) {
			super.notifyChanged(notification);
		}
		if (ModifiedFilter.matches(notification)
				&& (notification instanceof ENotificationImpl)
				&& (notification.getNotifier() instanceof EObject)) {
			Object notifier = notification.getNotifier();
			if (notifier instanceof EObject) {
				EObject eNotifier = (EObject) notifier;
				EObject container = eNotifier.eContainer();
				if (container instanceof View && !(container instanceof DecorationNode)) {
					View view = (View) container;
					EObject element = view.getElement();

					if (eNotifier instanceof Bounds && view instanceof Node && element instanceof ControlBox) {
						ControlBox cb = (ControlBox) element;
						updatePosition((Node) view, cb);

						System.out.println(cb.getId()+" moved: "+cb.getX()+", "+cb.getY());
					}
				}
			}
		}
	}

	private void updatePosition(Node n, ControlBox cb) {
		LayoutConstraint layout = n.getLayoutConstraint();
		if (layout instanceof Bounds) {
			Bounds bounds = (Bounds) layout;
			// Find center of bounds
			int width = bounds.getWidth();
			int height = bounds.getHeight();

			float x = bounds.getX() + width / 2.0f;
			float y = bounds.getY() + height / 2.0f;
			cb.setX(x);
			cb.setY(y);
		}
	}
	
}
