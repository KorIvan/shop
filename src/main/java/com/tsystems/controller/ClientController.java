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
//	@Autowired
//	private PersonEditor personEditor;
//	@Autowired
//	private ProductEditor productEditor;
//	@Autowired
//	private OrderItemEditor orderItemEditor;
//	@Autowired
//	private OrderItemEditor addressItemEditor;
//
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Person.class, this.personEditor);
//		binder.registerCustomEditor(Product.class, this.productEditor);
//		binder.registerCustomEditor(OrderItem.class, this.orderItemEditor);
//		binder.registerCustomEditor(Address.class, addressItemEditor);
//	}

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
		// }
		return model;
	}

	@RequestMapping(value = "makeOrder", method = RequestMethod.GET)
	public ModelAndView makeOrder(
//			@ModelAttribute("order") Order order, BindingResult result,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("order");
		long clientId = Long.parseLong(request.getSession().getAttribute("clientId").toString());

		if (clientService.hasUnfinishedOrder(clientId)) {
			model.setViewName("order");
			model.addObject("message", "Finish or cancel this order first.");
			model.addObject("paymentMethod", PaymentMethod.class);
			Order unfinishedOrder=clientService.getUnfinishedOrder(clientId);
			model.addObject("order", unfinishedOrder);
			request.getSession().setAttribute("order", unfinishedOrder);
			return model;
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			model.setView(new RedirectView("catalog"));
			model.addObject("title", "Catalog");
			model.addObject("message", "Cart is empty. Choose some products first.");
			return model;
		}
		model.addObject("title", "Your order");
		if (cart.getItemList()!=null&& !cart.getItemList().isEmpty()) {
			Order newOrder = clientService.makeOrder(cart, clientId);
			request.getSession().setAttribute("order", newOrder);
			model.addObject("order", newOrder);
			return model;
		}
		model.addObject("paymentMethod", PaymentMethod.class);

		model.setViewName("catalog");
		return model;
	}

	@RequestMapping(value = "makeOrder", method = RequestMethod.POST)
	public ModelAndView purchaseOrder(
			//@ModelAttribute("order") Order order, BindingResult result,
			@RequestParam String action, HttpServletRequest request,@ModelAttribute("order") Order order) {
		Order order2=(Order)request.getSession().getAttribute("order");
//		System.out.println(result.getAllErrors());
		System.out.println(order.getOrderItems());
		System.out.println(order.getClient());
		System.out.println("OrserCost"+ order.getCost());
		System.out.println(order.getCreationDate());
		System.out.println(order.getStatus());
		System.out.println(order.getDeliveryMethod());
		System.out.println(order.getPayMethod());



		ModelAndView model = new ModelAndView("order");

		model.addObject("title", "Your order");

		if (action.equalsIgnoreCase("Next")) {
			Map<String, Object> resp = clientService.processOrder(order);
			model.setViewName(resp.get("view").toString());
			for (Map.Entry<String, Object> e : resp.entrySet()) {
				if (!e.getKey().equals("view"))
					model.addObject(e.getKey(), e.getValue());
			}
		}
		if(action.equalsIgnoreCase("Cancel")){
			clientService.cancelOrder(order);
			
			model.setView(new RedirectView("/"));
			model.addObject("message", "Order is canceled.");
			return model;
		}
		return model;
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
		User user = (User) session.getAttribute("client");
		List<Address> addresses = clientService.findAllAddresses(user.getId());
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
			User user = (User) session.getAttribute("client");
			// model.addObject("message", clientService.createAddress(address,
			// user.getId()));
			clientService.createAddress(address, user.getId());
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
		return model;
	}

	/**
	 * history of client orders
	 */
	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public ModelAndView getAllOrders(HttpSession session, @ModelAttribute("orderHistory") Order order,
			BindingResult result) {
		ModelAndView model = new ModelAndView("clientOrders");
		User user = (User) session.getAttribute("client");
		List<Order> orders = clientService.getOrdersHistoryByClientI(user.getId());
		model.addObject("ordersHistory", orders);
		return model;
	}

}
