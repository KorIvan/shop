package service;

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.PaymentMethod;

public class OrderCancelFirstStateTest {
	static Order order;

	@Before
	public void setParameters() {
		order=new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setDeliveryMethod(DeliveryMethod.UNKNOWN);
		order.setPayMethod(PaymentMethod.UNKNOWN);
		order.setPaid(false);

	}
	
	@Test
	public void cancelAlreadyCreatedOrderTest() {
		
		if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING) || order.getPayMethod().equals(PaymentMethod.UNKNOWN)
				|| order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)) {

			order.setStatus(OrderStatus.CANCELED);

		}
		assertEquals(order.getStatus(), OrderStatus.CANCELED);
	}
	@Test
	public void cancelDevliveryChangedOrderTest1(){
		order.setDeliveryMethod(DeliveryMethod.SELF_DELIVERY);
		if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING) || order.getPayMethod().equals(PaymentMethod.UNKNOWN)
				|| order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)) {
			order.setStatus(OrderStatus.CANCELED);
		}
		assertEquals(order.getStatus(), OrderStatus.CANCELED);
	}
	@Test
	public void cancelDevliveryChangedOrderTest2(){
		order.setDeliveryMethod(DeliveryMethod.COURIER);
		if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING) || order.getPayMethod().equals(PaymentMethod.UNKNOWN)
				|| order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)) {
			order.setStatus(OrderStatus.CANCELED);
		}
		assertEquals(order.getStatus(), OrderStatus.CANCELED);
	}
}
