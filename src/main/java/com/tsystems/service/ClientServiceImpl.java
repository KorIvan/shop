package com.tsystems.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
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
import com.tsystems.model.excep.ObjectNoLongerExistsException;
import com.tsystems.repository.AddressRepository;
import com.tsystems.repository.CategoryRepository;
import com.tsystems.repository.OrderRepository;
import com.tsystems.repository.PersonRepository;
import com.tsystems.repository.ProductRepository;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
	private static Logger log = Logger.getLogger(ClientService.class.getName());
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AddressRepository addressRepository;

	public String createClient(Person client) {
		client.setType(PersonType.ROLE_CLIENT);
		if (personRepository.validatePerson(client)) {
			log.info(String.format("New client %s registered", client.getEmail()));
			personRepository.createPerson(client);
			return "Congratulations! You're registred!";
		} else
			return String.format("Sorry, user with email %s already exists.", client.getEmail());
	}

	public Person getClientById(Long id) {
		return personRepository.readPerson(id);

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

		personRepository.updatePerson(client);
	}

	public Person authenticatePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order makeOrder(Cart cart, Long clientId) {
		Order order = new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		order.setPaid(false);
		order.setPayMethod(PaymentMethod.UNKNOWN);
		order.setDeliveryMethod(DeliveryMethod.UNKNOWN);
		order.setCreationDate(new Date());
		order.setClient(personRepository.readPerson(clientId));
		Set<OrderItem> itemList = new HashSet<OrderItem>();
		float cost = 0;
		for (CartItem item : cart.getItemList()) {
			OrderItem orderItem = new OrderItem();
			Integer amount = item.getAmount();
			if (amount != 0 && checkAndTakeProductAmount(item.getProduct(), amount)) {
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
		orderRepository.createOrder(order);
		return order;
	}

	public String cancelOrder(Order order) {
		if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING) || order.getPayMethod().equals(PaymentMethod.UNKNOWN)
				|| order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)) {
			invalidateOrder(order);
			orderRepository.updateOrder(order);
			return "Order is canceled.";
		}
		if (order.getPaid()) {
			if (order.getStatus().equals(OrderStatus.WAITING_SELF_DELIVERY))
				return "Order's already paid and delivered to self delivery point.";
			if (order.getStatus().equals(OrderStatus.SHIPPING))
				return "Order's already paid and shipping to you.";
			if (order.getStatus().equals(OrderStatus.DELIVERED))
				return "Order's already paid and delivered.";
			if (order.getStatus().equals(OrderStatus.SHIPMENT_PENDING)) {
				invalidateOrder(order);
				orderRepository.updateOrder(order);
				return "Order is canceled. You money'll be returned.";

			}
		} else {
			if (order.getDeliveryMethod().equals(DeliveryMethod.COURIER))
				if (order.getPayMethod().equals(PaymentMethod.EXCHANGING)) {
					if (order.getStatus().equals(OrderStatus.SHIPPING))
						return "Order's shipping to you.";
				}
			if (order.getDeliveryMethod().equals(DeliveryMethod.OTHER)) {
				invalidateOrder(order);
				orderRepository.updateOrder(order);
				return "Order is canceled.";
			}
			if (order.getDeliveryMethod().equals(DeliveryMethod.SELF_DELIVERY)) {
				// if(order.getPayMethod().equals(PaymentMethod.EXCHANGING)){
				invalidateOrder(order);
				orderRepository.updateOrder(order);
				return "Order is canceled.";
				// }
			}
		}
		invalidateOrder(order);
		orderRepository.updateOrder(order);
		return "Order is canceled.";
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
		return productRepository.findProductsByCategory(categoryId);
	}

	public List<Category> findAllCategories() {
		return categoryRepository.findAllCategories();
	}

	@Deprecated
	public boolean validateClient(User user) {
		// return personRepository.validatePerson(user);
		return false;
	}

	public void createAddress(Address address, Long clientId) {
		Person client = personRepository.readPerson(clientId);
		if (client.getAddresses() == null) {
			client.setAddresses(new ArrayList<Address>());
		}
		client.getAddresses().add(address);
		address.setClient(client);
		personRepository.updatePerson(client);
	}

	public void purchaseOrder(Order order, Long clientId) {
		System.out.println(order.getOrderItems());
		System.out.println(order.getClient().getEmail());

		orderRepository.createOrder(order);
	}

	private void updateOrder(Order order) {
		orderRepository.updateOrder(order);
	}

	public List<Address> findAllAddresses(Long clientId) throws ConnectException {
		return addressRepository.findAllAddresses(clientId);
	}

	public List<Order> findAllOrders(Long clientId) {
		return orderRepository.findAllOrders(clientId);
	}

	public Order getUnfinishedOrder(Long clientId) {
		List<Order> unfinished = orderRepository.findAllOrders(clientId);
		for (Order order : unfinished) {
			if (order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)
					|| order.getPayMethod().equals(PaymentMethod.UNKNOWN)) {
				return order;
			}
		}
		return null;
	}

	public boolean hasUnfinishedOrder(Long clientId) {
		return orderRepository.hasUnfinishedOrder(clientId);
	}

	public Address getAddressById(Long id) throws ObjectNoLongerExistsException, ConnectException {
		try {
		return addressRepository.findAddressById(id);}
		catch (EntityNotFoundException e){
			throw new ObjectNoLongerExistsException();
		}
	}
	public List<Order> getOrdersHistoryByClientI(Long clientId) {
		return orderRepository.findAllOrders(clientId);
	}

	@Override
	public Person getClientByEmail(String email) {
		return personRepository.findClientByEmail(email);
	}

	/**
	 * Keys "view","message","addresses","title","order"
	 * @throws ConnectException 
	 */
	@Override
	public Map<String, Object> processOrder(Order order) throws ConnectException {
		Map<String, Object> response = new HashMap<>();
		String keyView = "view";
		String keyMessage = "message";
		if (order.getDeliveryMethod().equals(DeliveryMethod.UNKNOWN)
				|| order.getPayMethod().equals(PaymentMethod.UNKNOWN)) {
			response.put(keyView, "order");
			response.put(keyMessage,
					"If you want complete purchase order, fields Payment method and Delivery method can't be unknown!");
			return response;
		}
		if (order.getDeliveryMethod().equals(DeliveryMethod.SELF_DELIVERY)) {
			order.setAddress(null);
			order.setDeliveryDate(null);
			if (order.getPayMethod().equals(PaymentMethod.EXCHANGING)) {
				order.setStatus(OrderStatus.WAITING_SELF_DELIVERY);
				updateOrder(order);
				// now this order's purchasing is finished
				response.put(keyView, "catalog");
				response.put(keyMessage, "Your order is waiting you.");
				return response;
			}
			if (order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {

				order.setStatus(OrderStatus.PAYMENT_PENDING);

				if (order.getPaid().equals(true)) {
					order.setStatus(OrderStatus.WAITING_SELF_DELIVERY);
					updateOrder(order);
					// now this order's purchasing is finished
					response.put(keyView, "catalog");
				}
				updateOrder(order);
				response.put(keyView, "orderPayment");
				response.put("order", order);
				return response;
			}
		} else if (order.getDeliveryMethod().equals(DeliveryMethod.COURIER)) {
			order.setStatus(OrderStatus.PAYMENT_PENDING);
			System.out.println("order address " + order.getAddress());
			System.out.println("order delivery " + order.getDeliveryDate());
			// order.setAddress(clientService.get);
			System.out.println("order address " + order.getAddress());
			System.out.println("order delivery " + order.getDeliveryDate());

			if (order.getAddress() != null && order.getDeliveryDate() != null) {
				if (order.getPayMethod().equals(PaymentMethod.EXCHANGING)) {
					order.setStatus(OrderStatus.SHIPMENT_PENDING);
					updateOrder(order);
					// now this order's purchasing is finished
					response.put(keyView, "catalog");
					response.put("order", order);
					response.put(keyMessage, "Shipment is pending. Courier'll contact you.");
					return response;
				}
				if (order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {
					if (order.getPaid().equals(true)) {
						order.setStatus(OrderStatus.SHIPMENT_PENDING);
						updateOrder(order);
						// now this order's purchasing is finished
						response.put(keyView, "catalog");
						response.put("order", order);
						response.put(keyMessage, "Order is paid. Shipment is pending. Courier'll contact you.");

						return response;
					}
				}
			}
			response.put(keyView, "orderDelivery");
			response.put("order", order);
			List<Address> userAddresses = findAllAddresses(order.getClient().getId());

			for (Address address : userAddresses)
				System.out.println(address.getCity());
			response.put("addresses", userAddresses);
			response.put("title", "Choose address and delivery date");
			return response;
		} else if (order.getDeliveryMethod().equals(DeliveryMethod.OTHER)) {
			if (order.getAddress() != null && order.getDeliveryDate() != null) {
				if (order.getPayMethod().equals(PaymentMethod.EXCHANGING)) {
					response.put("message", "Exchanging payment possible only with COURIER and SELF DELIVERY.");
					response.put(keyView, "order");
					response.put("order", order);
					return response;
				}
				if (order.getPayMethod().equals(PaymentMethod.PROVISIONING)) {
					order.setStatus(OrderStatus.PAYMENT_PENDING);
					if (order.getPaid().equals(true)) {
						order.setStatus(OrderStatus.SHIPMENT_PENDING);
						updateOrder(order);
						// now this order's purchasing is finished
						response.put(keyView, "catalog");
						// response.put("order", order);
						return response;
					}
					response.put(keyView, "orderPayment");
					response.put("order", order);
					return response;
				}
			}
			response.put(keyView, "orderDelivery");
			response.put("order", order);
			List<Address> userAddresses = findAllAddresses(order.getClient().getId());
			response.put("addresses", userAddresses);
			response.put("title", "Choose address and delivery date");
			return response;
		}
		return response;
	}

	@Override
	public Product getProductById(long productId) {
		return productRepository.findProductById(productId);

	}

	private void invalidateOrder(Order order) {
		putBackProducts(order);
		order.setStatus(OrderStatus.CANCELED);
		order.setPaid(false);
		order.setDeliveryMethod(DeliveryMethod.UNKNOWN);
		order.setPayMethod(PaymentMethod.UNKNOWN);
		order.setDeliveryDate(null);
		order.setAddress(null);
	}

	/**
	 * To put back products from canceled Order
	 * 
	 * @param order
	 */
	private void putBackProducts(Order order) {
		Set<OrderItem> items = order.getOrderItems();
		for (OrderItem o : items) {
			Product beforePurchase = productRepository.findProductById(o.getProduct().getId());
			beforePurchase.getStorage().setAmount(beforePurchase.getStorage().getAmount() + o.getAmount());
			productRepository.updateProduct(beforePurchase);
		}
	}

	private boolean checkAndTakeProductAmount(Product product, Integer amountToTake) {
		Product productFromDB = productRepository.findProductById(product.getId());
		if (productFromDB.getStorage().getAmount() < amountToTake) {
			return false;
		} else {
			productFromDB.getStorage().setAmount(productFromDB.getStorage().getAmount() - amountToTake);
			productRepository.updateProduct(productFromDB);
			return true;
		}
	}

	@Override
	public void updateAddress(Address address) throws ConnectException, EntityNotFoundException {
		if (address.getCity() != null && address.getCountry() != null && address.getBuilding() != null
				&& address.getApartment() != null && address.getClient() != null && address.getStreet() != null
				&& address.getZip() != null)
			addressRepository.updateAddress(address);

	}
}
