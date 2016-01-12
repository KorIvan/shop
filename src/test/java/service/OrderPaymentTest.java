package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.PaymentMethod;

public class OrderPaymentTest {

	static Order order;

	@Before
	public void setParameters() {
		order = new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setDeliveryMethod(DeliveryMethod.SELF_DELIVERY);
		order.setPayMethod(PaymentMethod.PROVISIONING);
		order.setPaid(false);
	}

	@Test
	public void payOrderProvisioning() {
		if (!order.getPaid() && order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {
			if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING)) {
				order.setPaid(true);
				order.setStatus(OrderStatus.SHIPMENT_PENDING);
			}
		}
		assertEquals(order.getStatus(), OrderStatus.SHIPMENT_PENDING);
	}

}
