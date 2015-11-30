package com.tsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Category;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
import com.tsystems.model.Product;
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

	public Person updateClient(Person client) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person authenticatePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean purchaseOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
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

}
