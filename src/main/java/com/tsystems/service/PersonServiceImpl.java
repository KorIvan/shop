package com.tsystems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Address;
import com.tsystems.model.Cart;
import com.tsystems.model.CartItem;
import com.tsystems.model.Category;
import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.OrderStatus;
import com.tsystems.model.PaymentMethod;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
import com.tsystems.model.Product;
import com.tsystems.model.User;
import com.tsystems.repository.PersonRepository;

@Service("clientService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository clientRepository;

	public String createClient(Person client) {
		client.setType(PersonType.ROLE_CLIENT);
		if (clientRepository.validateClient(client)) {
			clientRepository.createClient(client);
			return "Congratulations! You're registred!";
		} else
			return String.format("Sorry, user with email %s already exists.", client.getEmail());
	}

	public Person getClientById(Long id) {
		return clientRepository.readPerson(id);

	}

	public void updateClient(Person client) {
		// System.out.println(client.getFirstName());
		// System.out.println(client.getLastName());
		// System.out.println(client.getEmail());
		// System.out.println(client.getPassword());
		// System.out.println(client.getAddresses());
		// System.out.println(client.getBirthdate());
		// System.out.println(client.getId());
		// System.out.println(client.getType());

		clientRepository.updatePerson(client);
	}

	public Person authenticatePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order makeOrder(Cart cart, Long clientId) {
		Order order = new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setPayMethod(PaymentMethod.UNKNOWN);
		order.setDeliveryMethod(DeliveryMethod.UNKNOWN);
		order.setCreationDate(new Date());
		order.setClient(clientRepository.readPerson(clientId));
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		float cost = 0;
		for (CartItem item : cart.getItemList()) {
			OrderItem orderItem = new OrderItem();
			Integer amount = item.getAmount();
			if (amount != 0) {
				orderItem.setAmount(amount);
				orderItem.setProduct(item.getProduct());
				Float price = item.getProduct().getCurrentPrice();
				orderItem.setPrice(price);
				orderItem.setOrder(order);
				itemList.add(orderItem);
				cost += amount * price;
			}

		}
		cart.setItemList(new ArrayList<CartItem>());
		System.out.println("total cost is" + cost);
		order.setCost(cost);
		order.setOrderItems(itemList);
		clientRepository.createOrder(order);
		return order;
	}

	public void cancelOrder(Order order) {
		clientRepository.deleteOrder(order);
	}

	public void transferMoney(Order order) {
		// TODO Auto-generated method stub
	}

	public void returnMoney(Order order) {
		// TODO Auto-generated method stub
	}

	public Integer takeFromStrorage(Product product, Integer amountToTake) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer putBackToStorage(Product product, Integer amountToPut) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getCategoryById(Long categoryId) {
		return clientRepository.getProductsByCategory(categoryId);
	}

	public List<Category> findAllCategories() {
		return clientRepository.findAllCategories();
	}

	public boolean validateClient(User user) {
		return clientRepository.validateClient(user);
	}

	public void createAddress(Address address, Long clientId) {
		Person client = clientRepository.readPerson(clientId);
		if (client.getAddresses() == null) {
			client.setAddresses(new ArrayList<Address>());
		}
		client.getAddresses().add(address);
		address.setClient(client);
		clientRepository.updatePerson(client);
	}

	public void purchaseOrder(Order order, Long clientId) {
		System.out.println(order.getOrderItems());
		System.out.println(order.getClient().getEmail());

		clientRepository.createOrder(order);
	}

	public void updateOrder(Order order) {
		clientRepository.updateOrder(order);
	}

	public List<Address> findAllAddresses(Long clientId) {
		return clientRepository.findAllAddresses(clientId);
	}

	public List<Order> findAllOrders(Long clientId) {
		return clientRepository.findAllOrders(clientId);
	}

	public Order getUnfinishedOrder(Long clientId) {
		List<Order> unfinished = clientRepository.findAllOrders(clientId);
		for (Order order:unfinished) {
			if(order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)||order.getPayMethod().equals(PaymentMethod.UNKNOWN)){
				return order;
			}
		}
		return null;
	}

	public boolean hasUnfinishedOrder(Long clientId) {
		return clientRepository.hasUnfinishedOrder(clientId);
	}

	public Address findAddressById(Long id) {
		return clientRepository.findAddressById(id);
	}

	public List<Order> getOrdersHistoryByClientI(Long clientId) {
		return clientRepository.findAllOrders(clientId);
	}

}
