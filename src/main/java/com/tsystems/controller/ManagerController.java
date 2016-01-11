package com.tsystems.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import com.tsystems.model.Attribute;
import com.tsystems.model.Category;
import com.tsystems.model.CategoryEditor;
import com.tsystems.model.Order;
import com.tsystems.model.Product;
import com.tsystems.model.Properties;
import com.tsystems.service.ManagerService;

@Controller
@RequestMapping("/manager")
@SessionAttributes({ "product","order" })
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private CategoryEditor categoryEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, this.categoryEditor);
	}

	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	// public ModelAndView logInPost(HttpServletRequest request,
	// @ModelAttribute("user") User user, HttpSession session) {
	// ModelAndView model = new ModelAndView("redirect:/client.html");
	// String message;
	// if (managerService.validateManager(user)) {
	// message = "You are logged in.";
	// user.setType(PersonType.SALES_MANAGER);
	// User user2 = (User) session.getAttribute("client");
	// if(user2!=null)
	// user2.setType(PersonType.NONE);
	// model.addObject("manager", user);
	// model.setView(new RedirectView("../manager.html"));
	// } else {
	// message = "Wrong login and password.";
	// model.setViewName("auth");
	// }
	//
	// model.addObject("message", message);
	// return model;
	// }

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mainManager(HttpSession session) {
		// if (!validateManager(session))
		// return "redirect:/manager/login.html";
		return "manager";
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView chooseCategory(HttpSession session) {
		ModelAndView model = new ModelAndView("chooseCategory");
		// if (!validateManager(session)) {
		// model.setViewName("accessDenied");
		// return model;
		// }
		Product product = new Product();
		model.addObject("product", product);
		model.addObject("title", "Choose category");
		model.addObject("categories", managerService.findAllCategories());

		return model;
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView createProductCategory(@ModelAttribute("product") Product product) {

		ModelAndView model = new ModelAndView(new RedirectView("addProduct.html"));
		// if (!validateManager(session)) {
		// model.setViewName("accessDenied");
		// return model;
		// }
		// if (product.getProperties() == null) {
		List<Properties> properties = new ArrayList<Properties>();
		List<Attribute> attributes = product.getCategory().getAttributesForCategory();
		for (int i = 0; i < attributes.size(); i++) {
			System.out.println(attributes.get(i).getName());
		}
		for (int i = 0; i < attributes.size(); i++) {
			Properties p = new Properties();
			p.setAttributes(attributes.get(i));
			p.setProduct(product);
			properties.add(p);
			System.out.println("i: " + i);
			System.out.println(properties);
		}
		System.out.println(properties);
		product.setProperties(properties);
		model.addObject("product", product);

		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView createProduct(@ModelAttribute("product") Product product, HttpSession session) {
		ModelAndView model = new ModelAndView("product");
		if (session.getAttribute("product").equals(null)) {
			model.setViewName("manager");
			return model;
		}

		// if (!validateManager(session)) {
		// model.setViewName("accessDenied");
		// return model;
		// }
		model.addObject("product", product);
		System.out.println(product.getCategory().getName());
		model.addObject("title", "New product");

		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView saveProduct(@Valid @ModelAttribute("product") Product product,BindingResult result) {
		ModelAndView model = new ModelAndView("product");
		model.addObject("title", "New product");
		if (result.hasErrors()) {
			model.addObject("message", "Sorry, error ocured.");
			System.out.println(result.getAllErrors());
			return model;
		} else {
//			product.getAmount().setAmount(Integer.parseInt(amount));;
			model.addObject("message", managerService.createProduct(product));
			model.setView(new RedirectView("category.html"));
		}
		return model;
	}

	// @RequestMapping(value = "/orders", method = RequestMethod.GET, produces =
	// "application/json")
	// public @ResponseBody List<Order> getAllOrders() {
	// return managerService.findAllOrders();
	// }
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView findAllOrders(HttpSession session) {
		ModelAndView model = new ModelAndView("orders");
		List<Order> orders = managerService.findAllOrders();
		model.addObject("orders", orders);
		return model;
	}
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public ModelAndView getOrder(@PathVariable String orderId ,HttpSession session) {
		ModelAndView model = new ModelAndView("orderEdit");
		Order order=managerService.getOrderById(Long.parseLong(orderId));
		model.addObject("order", order);
		session.setAttribute("order", order);
		return model;
	}
	
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.POST)
	public ModelAndView applyChangesOrder(@PathVariable String orderId ,HttpSession session, @ModelAttribute("order") Order order, BindingResult result) {
//		ModelAndView model = new ModelAndView("orderEdit");
		ModelAndView model = new ModelAndView(new RedirectView("/shop/manager/orders/"));
		String message=managerService.updateOrder(order);
		if(message!=null){
		model.addObject("message", message);
		model.setViewName("orderEdit");
		
		}
		session.setAttribute("order",null);
		return model;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getAllCategories() {
		return managerService.findAllCategories();
	}

	@RequestMapping(value = "/category/{categoryId}/attributes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Attribute> getCategoryAttributes(@PathVariable() String categoryId) {
		return managerService.getAllAttributesOfCategory(Long.parseLong(categoryId));
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView createCategory() {
		ModelAndView model = new ModelAndView("category");

		Category cat = new Category();
		List<Attribute> attributes = new LinkedList<Attribute>();
		attributes.add(new Attribute());
		cat.setAttributesForCategory(attributes);
		model.addObject("title", "New category");
		model.addObject("category", cat);

		return model;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView postCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			@RequestParam String action, HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView("category");

		String[] toDelete = request.getParameterValues("toDelete");

		model.addObject("title", "New category");

		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		}
		if (action.equalsIgnoreCase("Save")) {
			model.addObject("message", managerService.createCategory(category));
			model.setView(new RedirectView("addCategory.html"));
		}
		if (action.equalsIgnoreCase("Add Attribute")) {
			category.getAttributesForCategory().add(new Attribute());
			model.addObject("category", category);
		}
		if (toDelete != null && action.equalsIgnoreCase("Delete")) {
			for (int i = toDelete.length - 1; i >= 0; i--) {
				category.getAttributesForCategory().remove(Integer.parseInt(toDelete[i]));
			}
			model.addObject("category", category);
		}
		return model;
	}


}
