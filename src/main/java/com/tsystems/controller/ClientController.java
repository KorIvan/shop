package com.tsystems.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.tsystems.model.Address;
import com.tsystems.model.AddressEditor;
import com.tsystems.model.Cart;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.OrderItemEditor;
import com.tsystems.model.PaymentMethod;
import com.tsystems.model.Person;
import com.tsystems.model.PersonEditor;
import com.tsystems.model.Product;
import com.tsystems.model.ProductEditor;
import com.tsystems.model.User;
import com.tsystems.service.ClientService;

@Controller
@SessionAttributes({ "cart", "order" })
@RequestMapping("/client")
public class ClientController {
	// @Autowired
	// private PersonEditor personEditor;
	// @Autowired
	// private ProductEditor productEditor;
	// @Autowired
	// private OrderItemEditor orderItemEditor;
	// @Autowired
	// private OrderItemEditor addressItemEditor;
	@Autowired
	private AddressEditor addressEditor;

	//
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(Person.class, this.personEditor);
		// binder.registerCustomEditor(Product.class, this.productEditor);
		// binder.registerCustomEditor(OrderItem.class, this.orderItemEditor);
		binder.registerCustomEditor(Address.class, addressEditor);
	}

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getClientPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("client");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Person client = clientService.getClientByEmail(userDetail.getUsername());
		request.getSession().setAttribute("clientId", client.getId());
		System.out.println(request.getSession().getAttribute("clientId"));
		return model;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getClientPage2(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("client");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Person client = clientService.getClientByEmail(userDetail.getUsername());
		request.getSession().setAttribute("clientId", client.getId());
		System.out.println(request.getSession().getAttribute("clientId"));
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
		model.addAttribute("title", "Edit personal information");
		model.addAttribute("person",
				clientService.getClientById(Long.parseLong(session.getAttribute("clientId").toString())));
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
		// }
		return model;
	}

	@RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
	public ModelAndView makeOrder(HttpSession session) {
		ModelAndView model = new ModelAndView("order");
		long clientId = 0;
		if (session.getAttribute("clientId") != null) {
			clientId = Long.parseLong(session.getAttribute("clientId").toString());
		} else
			return new ModelAndView("redirect:/client");

		if (clientService.hasUnfinishedOrder(clientId)) {
			model.setViewName("order");
			model.addObject("message", "Finish or cancel this order first.");
			model.addObject("order", clientService.getUnfinishedOrder(clientId));
			return model;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			model.setViewName("catalog");
			model.addObject("title", "Catalog");
			model.addObject("message", "Cart is empty. Choose some products first.");
			return model;
		}
		model.addObject("title", "Your order");
		if (cart.getItemList() == null) {
			model.addObject("title", "Catalog");
			model.addObject("message", "Cart is empty. Choose some products first.");
			return model;
		}
		if (!cart.getItemList().isEmpty()) {
			Order newOrder = clientService.makeOrder(cart, clientId);
			if (newOrder == null) {
				model.setViewName("cart");
				model.addObject("message", "Not enough product's amount.");
				return model;
			}
			session.setAttribute("order", newOrder);
			model.addObject("order", newOrder);
			return model;
		}

		model.setViewName("catalog");
		return model;
	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public ModelAndView cancelOrder(HttpSession session) {
		ModelAndView model = new ModelAndView("client");
		if (session.getAttribute("order") == null) {
			return model;
		}
		Order order = (Order) session.getAttribute("order");
		String message = clientService.cancelOrder(order);
		model.addObject("message", message);
		return model;
	}

	@RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
	public ModelAndView purchaseOrder(HttpSession session, @ModelAttribute("order") Order order,
			BindingResult resultOrder, @ModelAttribute("address") Address address, BindingResult resultAddress,
			@RequestParam String action) {
		if (address != null) {
			address.setClient(clientService.getClientById(Long.parseLong(session.getAttribute("clientId").toString())));
			order.setAddress(address);
		}
		System.out.println(resultOrder.getAllErrors());
		System.out.println(order.getOrderItems());
		System.out.println(order.getClient());
		System.out.println("OrserCost" + order.getCost());
		System.out.println(order.getCreationDate());
		System.out.println(order.getStatus());
		System.out.println(order.getDeliveryMethod());
		System.out.println(order.getPayMethod());
		ModelAndView model = new ModelAndView("order");

		model.addObject("title", "Your order");
		if (action.equalsIgnoreCase("Add address")) {
			model.setViewName("address");
			return model;
		}
		if (action.equalsIgnoreCase("Next")) {
			Map<String, Object> resp = clientService.processOrder(order);
			model.setViewName(resp.get("view").toString());
			for (Map.Entry<String, Object> e : resp.entrySet()) {
				if (!e.getKey().equals("view"))
					model.addObject(e.getKey(), e.getValue());
			}
		}
		if (action.equalsIgnoreCase("Cancel")) {
			clientService.cancelOrder(order);

			model.setView(new RedirectView("/"));
			model.addObject("message", "Order is canceled.");
			return model;
		}
		return model;
	}

	@RequestMapping(value = "client", method = RequestMethod.GET)
	public String mainClient(HttpSession session) {
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
	public String getAddressForm(@ModelAttribute("address") Address address, Model model, HttpSession session) {
		model.addAttribute("title", "Add new address");
		return "address";
	}

	@RequestMapping(value = "/allAddresses", method = RequestMethod.GET)
	public ModelAndView getAddressForm(HttpSession session, @ModelAttribute("address") Address address,
			BindingResult result) {
		ModelAndView model = new ModelAndView("addresses");
		List<Address> addresses = clientService
				.findAllAddresses(Long.parseLong(session.getAttribute("clientId").toString()));
		model.addObject("addresses", addresses);
		model.addObject("title", "Address list");
		return model;
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public ModelAndView createAddress(@Valid @ModelAttribute("address") Address address, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView("address");
		model.addObject("taitle", "Add new address");

		if (result.hasErrors() && !result.hasFieldErrors("client")) {
			System.out.println(result.getFieldErrors());
			model.addObject("message", "Sorry, there is error in your parameters.");
			return model;
		} else {
			System.out.println(address.getApartment() + " " + address.getStreet() + " " + address.getCountry());
			clientService.createAddress(address, Long.parseLong(session.getAttribute("clientId").toString()));
			if (session.getAttribute("order") != null) {
				model.setViewName("orderDelivery");
				return model;
			}
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

	/**
	 * Return Client's Addresses as json
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllAddresses", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Address> getClientAddresses(HttpSession session, Model model) {
		return clientService.findAllAddresses(Long.parseLong(session.getAttribute("clientId").toString()));
	}

	/**
	 * Return Client's Orders as json
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Order> getClientOrders(HttpSession session) {
		return clientService.findAllOrders(Long.parseLong(session.getAttribute("clientId").toString()));
	}

	@RequestMapping(value = "/continueOrder", method = RequestMethod.GET)
	public ModelAndView continueOrder(HttpSession session) {
		ModelAndView model = new ModelAndView("orderDelivery");
		return model;
	}

	/**
	 * history of client orders
	 */
	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public ModelAndView getAllOrders(HttpSession session, @ModelAttribute("orderHistory") Order order,
			BindingResult result) {
		ModelAndView model = new ModelAndView("clientOrders");
		List<Order> orders = clientService
				.getOrdersHistoryByClientI(Long.parseLong(session.getAttribute("clientId").toString()));
		model.addObject("ordersHistory", orders);
		return model;
	}

}
