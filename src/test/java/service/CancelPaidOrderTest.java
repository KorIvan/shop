package service;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.PaymentMethod;

public class CancelPaidOrderTest {
	Order order;
	@Before
	public void setParameters(){
		order = new Order();
		order.setStatus(OrderStatus.SHIPMENT_PENDING);
		order.setDeliveryMethod(DeliveryMethod.SELF_DELIVERY);
		order.setPayMethod(PaymentMethod.PROVISIONING);
		order.setPaid(true);
	}

	@Test
	public void willNotCancelOrderTest() {
		if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING) || order.getPayMethod().equals(PaymentMethod.UNKNOWN)
				|| order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)) {

			order.setStatus(OrderStatus.CANCELED);

		}
		assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
		assertEquals(order.getStatus(), OrderStatus.SHIPMENT_PENDING);
	}
	
	@Test
	public void willNotcancelOrderTest1(){
		order.setStatus(OrderStatus.WAITING_SELF_DELIVERY);

		if (order.getPaid()&&order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {
			if (order.getStatus().equals(OrderStatus.WAITING_SELF_DELIVERY))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.SHIPPING))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.DELIVERED))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.SHIPMENT_PENDING)) {
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			}
		} 
	}
	
	@Test
	public void willNotcancelOrderTest2(){
		order.setStatus(OrderStatus.SHIPMENT_PENDING);
		if (order.getPaid()&&order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {
			if (order.getStatus().equals(OrderStatus.WAITING_SELF_DELIVERY))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.SHIPPING))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.DELIVERED))
				assertNotEquals(order.getStatus(), OrderStatus.CANCELED);
			if (order.getStatus().equals(OrderStatus.SHIPMENT_PENDING)) {
				order.setStatus(OrderStatus.CANCELED);
				assertEquals(order.getStatus(), OrderStatus.CANCELED);
			}
		} 
	}
	
	private void set1(){
		order = new Order();
		order.setStatus(OrderStatus.SHIPMENT_PENDING);
		order.setDeliveryMethod(DeliveryMethod.SELF_DELIVERY);
		order.setPayMethod(PaymentMethod.PROVISIONING);
		order.setPaid(true);
	}
}
