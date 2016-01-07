package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.tsystems.model.Cart;
import com.tsystems.model.CartItem;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.Product;
import static org.junit.Assert.*;

public class ClientOrderTest {

	@Test
	public void calculateOrder() {

		Order order = new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setCreationDate(new Date());
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		float cost = 0;
		Cart cart = createCart();
		for (CartItem item : cart.getItemList()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(item.getProduct());
			Integer amount = item.getAmount();
			orderItem.setAmount(amount);
			Float price = item.getProduct().getCurrentPrice();
			orderItem.setPrice(price);
			orderItem.setOrder(order);
			itemList.add(orderItem);
			cost+=amount*price;
		}
		System.out.println(cost);
		order.setCost(cost);
		//must be 60
		assertEquals(order.getCost(), new Float(60f));

	}

	public Cart createCart() {
		Cart cart = new Cart();
		CartItem item1 = new CartItem();
		cart.setItemList(new ArrayList<CartItem>());
		// Prod 1
		Product product1 = new Product();
		product1.setCurrentPrice(10f);
		item1.setAmount(4);
		item1.setProduct(product1);
		cart.getItemList().add(item1);
		// Prod 2
		CartItem item2 = new CartItem();
		Product product2 = new Product();
		product2.setCurrentPrice(20f);
		item2.setAmount(1);
		item2.setProduct(product1);
		cart.getItemList().add(item2);
		// final cost =60

		return cart;
	}
}
