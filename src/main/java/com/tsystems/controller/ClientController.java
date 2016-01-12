package com.tsystems.controller;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tsystems.model.Address;
import com.tsystems.model.AddressEditor;
import com.tsystems.model.Cart;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.excep.ExceptionMessages;
import com.tsystems.model.excep.ObjectNoLongerExistsException;
import com.tsystems.service.ClientService;

@Controller
@SessionAttributes({ "cart", "order" })
@RequestMapping("/client")
public class ClientController {
	private static Logger log = Logger.getLogger(ClientController.class.getName());

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
		request.getSession().setAttribute("username", userDetail.getUsername());
		System.out.println(request.getSession().getAttribute("clientId"));
		log.info("Welcome. Client logged in");
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
			if (address.getCity() != null && address.getCountry() != null)
				if (address.getCity().length() > 0 && address.getCountry().length() > 0) {
					address.setClient(
							clientService.getClientById(Long.parseLong(session.getAttribute("clientId").toString())));
					order.setAddress(address);
				}
		}
		System.out.println(resultOrder.getAllErrors());
		ModelAndView model = new ModelAndView("order");

		model.addObject("title", "Your order");
		if (action.equalsIgnoreCase("Add address")) {
			model.setViewName("address");
			return model;
		}
		if (action.equalsIgnoreCase("Next")) {
			Map<String, Object> resp=null;
			try {
				resp = clientService.processOrder(order);
			} catch (ConnectException e1) {
				model.setViewName("exception");
				model.addObject("message", ExceptionMessages.DATABASE_FAILED_CONNECT);
				log.error(ExceptionMessages.DATABASE_FAILED_CONNECT);
				return model;
			}
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

	@RequestMapping(value = "/allAddresses", method = RequestMethod.GET)
	public ModelAndView getAddressForm(HttpSession session, @ModelAttribute("address") Address address,
			BindingResult result) {
		ModelAndView model = new ModelAndView("addresses");
		List<Address> addresses = null;
		try {
			addresses = clientService
					.findAllAddresses(Long.parseLong(session.getAttribute("clientId").toString()));
		} catch (ConnectException e) {
			model.addObject("message", ExceptionMessages.DATABASE_FAILED_CONNECT);
			model.setViewName("exception");
			return model;
		} catch (NumberFormatException e) {
			model.addObject("message", ExceptionMessages.WRONG_ID);
			model.setViewName("exception");
			return model;


		}
		model.addObject("addresses", addresses);
		model.addObject("title", "Address list");
		return model;
	}

	@RequestMapping(value = "/editAddress/{id}", method = RequestMethod.GET)
	public ModelAndView editAddress(@ModelAttribute("address") Address address, @PathVariable String id,
			HttpSession session) {
		ModelAndView model = new ModelAndView("address");
		Person client = clientService.getClientById(Long.parseLong(session.getAttribute("clientId").toString()));
		System.out.println("xxx"+client.getAddresses().contains(address));
		System.out.println(address);
		if (client.getAddresses().contains(address)) {
			model.addObject("title", "Edit address");
			try {
				try {
					model.addObject("address", clientService.getAddressById(Long.parseLong(id)));
				} catch (ConnectException e) {
					model.setViewName("exception");
					model.addObject("message", ExceptionMessages.DATABASE_FAILED_CONNECT);
					return model;
				}
			} catch (NumberFormatException e) {
				model.addObject("message", ExceptionMessages.WRONG_ID);
				model.setViewName("exception");
				return model;
			} catch (ObjectNoLongerExistsException e) {
				model.addObject("message", ExceptionMessages.RESOURCE_DOES_NOT_EXISTS);
				model.setViewName("exception");
				return model;
			}
			return model;
		} else
			model.setViewName("addresses");
		return model;
	}

	@RequestMapping(value = "/editAddress/{id}", method = RequestMethod.POST)
	public ModelAndView saveAddress(@ModelAttribute("address") Address address, @PathVariable String id,
			HttpSession session) {
		ModelAndView model = new ModelAndView("addresses");
		
		if (Long.parseLong(session.getAttribute("clientId").toString()) == address.getId().longValue()) {
			try {
				clientService.updateAddress(address);
			} catch (ConnectException e) {
				model.setViewName("exception");
				model.addObject("message", ExceptionMessages.DATABASE_FAILED_CONNECT);
				return model;
			} catch (EntityNotFoundException e) {
				model.setViewName("exception");
				model.addObject("message", ExceptionMessages.RESOURCE_DOES_NOT_EXISTS);
				return model;
			}
			model.addObject("title", "Address list");
			model.addObject("message", "Address saved.");
			return model;
		}
		return model;
	}

	/**
	 * Return Client's Addresses as json
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllAddresses", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Address> getClientAddresses(HttpSession session, Model model) {
		List<Address> list=null;
		try {
			list=clientService.findAllAddresses(Long.parseLong(session.getAttribute("clientId").toString()));
		} catch (NumberFormatException e) {
			log.error(ExceptionMessages.WRONG_ID);
			return null;
		} catch (ConnectException e) {
			log.error(ExceptionMessages.DATABASE_FAILED_CONNECT);
			return null;
		}
		return list;
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
