package com.tsystems.service;

import java.util.List;

import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.Product;

public interface PersonService {
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
	Person updateClient(Person person);

//	boolean checkEmailForUniqueness();
	Person authenticatePerson();

	/**
	 * Client has chosen all products he needs and has decided to purchase
	 * order.
	 * 
	 * @param order
	 * @return
	 */
	boolean purchaseOrder(Order order);

	/**
	 * Client can cancel his Order at every step except when Order's status is "SHIPPING","DELIVERED".
	 * @param order
	 * @return true, if Order canceled.
	 */
	boolean cancelOrder(Order order);
	
	/**
	 * Client pays his Order by card or electronic wallet.
	 * 
	 * @param order
	 * @return true if transaction completed successfully
	 * @return false if Client has not enough money
	 */
	boolean transferMoney(Order order);
	
	/**
	 * When Client has canceled his Order after provisioning payment,
	 * he gets his money back.
	 * @param order
	 * @return true, if money're transfered back.
	 */
	boolean returnMoney(Order order);

	/**
	 * Client chooses amount of Product to buy.
	 * 
	 * @param product
	 * @param amountToTake
	 * @return Product left at Storage
	 */
	Integer takeFromStrorage(Product product, Integer amountToTake);

	/**must be put back
	 * If Clilent cancels Order Products must be put back at Storage.
	 * @return Product left at Storage
	 * @param product
	 * @param amountToPut
	 * @return Product became at Storage
	 */
	Integer putBackToStorage(Product product, Integer amountToPut);

	List<Product> getCategoryById(Long parseLong);

	List<Category> findAllCategories();
}
