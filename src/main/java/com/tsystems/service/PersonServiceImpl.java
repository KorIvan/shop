package com.tsystems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Address;
import com.tsystems.model.Cart;
import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.OrderStatus;
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
		client.setType(PersonType.CLIENT);
		if (clientRepository.validateClient(client)) {
			if (clientRepository.createClient(client))
				return "Congratulations! You're registred!";
			else
				return "Sorry, we have some problems.";
		} else
			return String.format("Sorry, user with email %s already exists.", client.getEmail());
	}
	public Person getClientById(Long id){
		return clientRepository.readPerson(id);
		
	}

	public String updateClient(Person client) {
//		System.out.println(client.getFirstName());
//		System.out.println(client.getLastName());
//		System.out.println(client.getEmail());
//		System.out.println(client.getPassword());
//		System.out.println(client.getAddresses());
//		System.out.println(client.getBirthdate());
//		System.out.println(client.getId());
//		System.out.println(client.getType());

		if(clientRepository.updatePerson(client))
			return "Personal information updated successfully.";
		else return "Sorry, error";
	}

	public Person authenticatePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public String purchaseOrder(Cart cart) {
		Order order = new Order();
		order.setStatus(OrderStatus.PAYMENT_PENDING);
		
		return null;
	}

	public boolean cancelOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean transferMoney(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean returnMoney(Order order) {
		// TODO Auto-generated method stub
		return false;
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
	public String createAddress(Address address, Long clientId) {
		Person client=clientRepository.readPerson(clientId);
		if (client.getAddresses()==null){
			client.setAddresses(new ArrayList<Address>());
		}
		client.getAddresses().add(address);
		address.setClient(client);
		if(clientRepository.updatePerson(client))
			return "New address added.";
		else return "Sorry, address can't be added.";
	}

}
