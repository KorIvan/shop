package service;

import org.junit.Before;
import org.junit.Test;

import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.PaymentMethod;
import com.tsystems.model.excep.UnallowableStateException;

public class OrderStatusTest {
	Order order;

	@Before
	public void setParameters() {
		order = new Order();
		order.setDeliveryMethod(DeliveryMethod.OTHER);
		order.setPayMethod(PaymentMethod.PROVISIONING);
		order.setPaid(false);

		order.setStatus(OrderStatus.PAYMENT_PENDING);

	}

	@Test(expected = UnallowableStateException.class)
	public void stateChangingUnpaid() {
		order.setStatus(OrderStatus.DELIVERED);
	}
	@Test(expected = UnallowableStateException.class)
	public void stateChangingPaid() {
		order.setPaid(true);
		order.setStatus(OrderStatus.PAYMENT_PENDING);
	}
	
}
