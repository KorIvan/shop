package com.tsystems.service;

import java.util.List;
import java.util.Map;

import com.tsystems.model.Address;
import com.tsystems.model.Cart;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.model.User;

public interface ClientService {
	/**
	 * Client creates an account to continue purchasing process/
	 * 
	 * @return Person's account
	 */
	String createClient(Person person);

	/**
	 * Client made some adjustments to his account profile and save them.
	 * 
	 * @return Updated Person's account
	 */
	void updateClient(Person person);

	// boolean checkEmailForUniqueness();
	Person authenticatePerson();

	/**
	 * Client has chosen all products he needs and has decided to purchase
	 * order.
	 * 
	 * @param long1
	 * 
	 * @param order
	 * @return
	 */
	Order makeOrder(Cart cart, Long clientId);

	/**
	 * Client can cancel his Order at every step except when Order's status is
	 * "SHIPPING","DELIVERED".
	 * 
	 * @param order
	 */
	String cancelOrder(Order order);

	/**
	 * Client pays his Order by card or electronic wallet.
	 * 
	 * @param order
	 * @return true if transaction completed successfully
	 * @return false if Client has not enough money
	 */
	void transferMoney(Order order);

	/**
	 * When Client has canceled his Order after provisioning payment, he gets
	 * his money back.getClientById
	 * 
	 * @param order
	 * @return true, if money're transfered back.
	 */
	void returnMoney(Order order);

	/**
	 * Client chooses amount of Product to buy.
	 * 
	 * @param product
	 * @param amountToTake
	 * @return Product left at Storage
	 */
	Integer takeFromStrorage(Product product, Integer amountToTake);

	/**
	 * must be put back If Clilent cancels Order Products must be put back at
	 * Storage.
	 * 
	 * @return Product left at Storage
	 * @param product
	 * @param amountToPut
	 * @return Product became at Storage
	 */
	Integer putBackToStorage(Product product, Integer amountToPut);

	List<Product> getCategoryById(Long parseLong);

	List<Category> findAllCategories();

//	boolean validateClient(User user);

	Person getClientById(Long id);

	void createAddress(Address address, Long clientId);

	/**
	 * Persist Order for Client for further purchasing
	 * 
	 * @param order
	 * @param clientId
	 */
	void purchaseOrder(Order order, Long clientId);

	List<Address> findAllAddresses(Long clientId);

	List<Order> findAllOrders(Long clientId);

	Order getUnfinishedOrder(Long clientId);

	boolean hasUnfinishedOrder(Long clientId);

	
	Address findAddressById(Long id);

	List<Order> getOrdersHistoryByClientI(Long id);
	/**
	 * Returns Clietn by email
	 * @param username
	 * @return
	 */
	Person getClientByEmail(String username);

	Map<String, Object> processOrder(Order order);

	Product getProductById(long parseLong);

}
