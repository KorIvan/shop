package com.tsystems.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tsystems.model.Address;
import com.tsystems.model.Cart;
import com.tsystems.model.CartItem;
import com.tsystems.model.Category;
import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.PaymentMethod;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
import com.tsystems.model.Product;
import com.tsystems.model.User;
import com.tsystems.service.PersonService;

@Controller
@SessionAttributes({ "client", "cart", "order" })
public class PersonController {
	@Autowired
	private PersonService clientService;

	/**
	 * Client's registration
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistrationForm(@ModelAttribute("person") Person person, Model model) {
		model.addAttribute("title", "Registration");
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrate(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		ModelAndView model = new ModelAndView("registration");
		model.addObject("title", "Registration");
		// if result has errors
		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			model.addObject("message", clientService.createClient(person));
			// model.setView(new RedirectView("login.html"));
		}
		return model;
	}

	/**
	 * Returns personal information
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editClientParams", method = RequestMethod.GET)
	public String getPersonalInformationForm(@ModelAttribute("person") Person person, Model model,
			HttpSession session) {
		if (!validateClient(session)) {
			return "redirect:/";
		}
		model.addAttribute("title", "Edit personal information");
		User user = (User) (session.getAttribute("client"));
		System.out.println(user.getId() + " user's id");
		model.addAttribute("person", clientService.getClientById(user.getId()));
		return "editClient";
	}

	@RequestMapping(value = "/editClientParams", method = RequestMethod.POST)
	public ModelAndView saveClientUpdates(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		ModelAndView model = new ModelAndView("editClient");
		model.addObject("title", "Edit personal information");
		// if result has errors
		// if (result.hasErrors() && !result.hasFieldErrors("type")) {
		// model.addObject("message", "Sorry, error ocured.");
		// return model;
		// } else {
		model.addObject("message", clientService.updateClient(person));
		// }
		return model;
	}

	@RequestMapping(value = "/catalog", method = RequestMethod.GET)
	public ModelAndView getCatalog() {
		ModelAndView model = new ModelAndView("catalog");
		return model;
	}

	@RequestMapping(value = "/hello")
	public String getHello(Model model) {
		model.addAttribute("registration", "Registration");
		return "hello";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String sayHello(Model model) {

		model.addAttribute("greeting", "Hello World");

		return "hello";
	}

	/**
	 * Returns list of products for specified category.
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Product> getCategoryProducts(@PathVariable() String categoryId) {
		return clientService.getCategoryById(Long.parseLong(categoryId));
	}

	/**
	 * Returns all existing categories.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getAllCategories() {
		return clientService.findAllCategories();
	}

	@RequestMapping(value = "makeOrder", method = RequestMethod.GET)
	public ModelAndView makeOrder(HttpSession session) {
		ModelAndView model = new ModelAndView("order");
		if (!validateClient(session)) {
			model.setView(new RedirectView("login.html"));
			return model;
		}
		User client = (User) session.getAttribute("client");

		if (clientService.hasUnfinishedOrder(client.getId())) {
			model.setViewName("order");
			model.addObject("message", "Finish or cancel this order first.");
			model.addObject("paymentMethod", PaymentMethod.class);
			model.addObject("order", clientService.getUnfinishedOrder(client.getId()));
			return model;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		model.addObject("title", "Your order");
		if (cart.getItemList() == null || cart == null) {
			model.setView(new RedirectView("client.html"));
			return model;
		}
		Order order = clientService.makeOrder(cart, client.getId());
		session.setAttribute("order", order);
		model.addObject("order", order);
		return model;
	}

	@RequestMapping(value = "makeOrder", method = RequestMethod.POST)
	public ModelAndView purchaseOrder(HttpSession session, @ModelAttribute("order") Order order, BindingResult result) {

		System.out.println(result.getAllErrors());
		ModelAndView model = new ModelAndView("order");
		User client = (User) session.getAttribute("client");
		model.addObject("title", "Your order");
		if (order.getDeliveryMethod().equals(DeliveryMethod.SELF_DELIVERY)
				&& order.getPayMethod().equals(PaymentMethod.EXCHANGING)) {
			clientService.purchaseOrder(order, client.getId());
			model.setViewName("client");
			return model;
		} else {
			model.addObject("order", order);
			model.setViewName("deliveryMethod");
			return model;
		}
	}

	@RequestMapping(value = "getOrder", method = RequestMethod.GET)
	public ModelAndView getOrder(HttpSession session, @ModelAttribute("order") Order order, BindingResult result) {

		System.out.println(result.getAllErrors());
		ModelAndView model = new ModelAndView("order");
		User client = (User) session.getAttribute("client");
		model.addObject("title", "Confirm our order");
		clientService.purchaseOrder(order, client.getId());
		model.addObject("order", order);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logInGet(@ModelAttribute("user") User user) {
		return "auth";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void destroySession(HttpServletResponse response, HttpSession session) {
		User user = (User) session.getAttribute("client");
		user.setType(PersonType.NONE);
		try {
			response.sendRedirect("");
		} catch (IOException e) {
			System.out.println("page does not exist!");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView logInPost(HttpServletRequest request, @ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("redirect:/client.html");
		String message;
		if (clientService.validateClient(user)) {
			message = "You are logged in.";
			user.setType(PersonType.CLIENT);
			model.addObject("client", user);
			model.setView(new RedirectView("client.html"));
		} else {
			message = "Wrong login and password.";
			model.setViewName("auth");
		}

		model.addObject("message", message);
		return model;
	}

	@RequestMapping(value = "client", method = RequestMethod.GET)
	public String mainClient(HttpSession session) {
		if (!validateClient(session))
			return "redirect:/login.html";
		return "client";
	}

	/**
	 * Add new client's address.
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAddress", method = RequestMethod.GET)
	public String getAddressForm(@ModelAttribute("address") Address address, Model model) {
		model.addAttribute("title", "Add new address");
		return "address";
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public ModelAndView createAddress(@Valid @ModelAttribute("address") Address address, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView("address");
		model.addObject("title", "Add new address");

		if (result.hasErrors() && !result.hasFieldErrors("client")) {
			System.out.println(result.getFieldErrors());
			model.addObject("message", "Sorry, there is error in your parameters.");
			return model;
		} else {
			System.out.println(address.getApartment() + " " + address.getStreet() + " " + address.getCountry());
			User user = (User) session.getAttribute("client");
			model.addObject("message", clientService.createAddress(address, user.getId()));
			model.setView(new RedirectView("client.html"));
		}
		return model;
	}

	/**
	 * Validation of client
	 * 
	 * @param session
	 * @return true,if client logged in; false, if null or not a client
	 */
	private boolean validateClient(HttpSession session) {
		User user = (User) session.getAttribute("client");
		if (user == null)
			return false;
		if (user.getType().equals(PersonType.CLIENT))
			return true;
		else
			return false;
	}

	private Cart cart = new Cart();

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView getCart() {
		ModelAndView model = new ModelAndView("cart");
		model.addObject("cart", cart);
		model.addObject("title", "Your cart");
		return model;
	}

	@RequestMapping(value = "/cartContent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Cart getCategoryProducts() {
		return cart;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView setCart(@RequestBody CartItem cartItem) {
		ModelAndView model = new ModelAndView("cart");
		cart.setCartItem(cartItem);
		model.addObject("title", "Your cart");
		model.addObject("cart", cart);
		return model;
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView putToCart(@RequestBody CartItem cartItem) {
		System.out.println(cartItem.getProduct().getName() + " id:" + cartItem.getProduct().getId() + " amount:"
				+ cartItem.getAmount());
		if (cart.getItemList() == null) {
			cart.setItemList(new LinkedList<CartItem>());
		}
		if (cart.getItemList().contains(cartItem)) {
			cart.addToCartItem(cartItem);
		} else
			cart.getItemList().add(cartItem);
		ModelAndView model = new ModelAndView("cart");
		model.addObject("cart", cart);
		model.addObject("lol", "String ;ol");
		return model;
	}

	/**
	 * Return Client's Addresses as json
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllAddresses", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Address> getClientAddresses(HttpSession session) {
		User user = (User) session.getAttribute("client");
		return clientService.findAllAddresses(user.getId());
	}

	/**
	 * Return Client's Orders as json
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Order> getClientOrders(HttpSession session) {
		User user = (User) session.getAttribute("client");
		return clientService.findAllOrders(user.getId());
	}

	@RequestMapping(value = "/continueOrder", method = RequestMethod.GET)
	public ModelAndView continueOrder(HttpSession session) {
		ModelAndView model = new ModelAndView("orderDelivery");
		if (!validateClient(session)) {
			model.setView(new RedirectView("login.html"));
			return model;
		}
		return model;
	}

}
