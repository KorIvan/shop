package com.tsystems.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.tsystems.model.Cart;
import com.tsystems.model.CartItem;
import com.tsystems.model.Category;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.service.ClientService;

@Controller
@RequestMapping("/")
@SessionAttributes({ "cart" })
public class GuestController {
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String selectClientType() {
		return "index.jsp";
	}

	/**
	 * Client's registration
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistrationForm(@ModelAttribute("person") Person person, Model model, HttpSession session) {
		model.addAttribute("title", "Registration");
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrate(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		ModelAndView model = new ModelAndView("registration");
		model.addObject("title", "Registration");
		if (result.hasErrors() && !result.hasFieldErrors("type")) {
			model.addObject("message", "Sorry, error ocured.");
			return model;
		} else {
			model.addObject("message", clientService.createClient(person));
			// model.setView(new RedirectView("login.html"));
		}
		return model;
	}

	@RequestMapping(value = "/catalog", method = RequestMethod.GET)
	public ModelAndView getCatalog(HttpSession session) {
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
		}
		ModelAndView model = new ModelAndView("catalog");
		model.addObject("title", "Catalog");
		return model;
	}

	@RequestMapping(value = "/hello")
	public String getHello(Model model, HttpServletRequest request) {
		System.out.println(request.getRemoteUser() + " getRemoteUser()");
		model.addAttribute("registration", "Registration");
		return "hello";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String sayHello(Model model) {

		model.addAttribute("greeting", "Hello World");

		return "hello";
	}

	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable() String productId) {
		ModelAndView model = new ModelAndView("productDescription");
		Product product = clientService.getProductById(Long.parseLong(productId));
		model.addObject("product", product);
		model.addObject("properties", product.getProperties());
		return model;
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

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView getCart() {
		ModelAndView model = new ModelAndView("cart");
		model.addObject("title", "Your cart");
		return model;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView setCart(@RequestBody CartItem cartItem, HttpSession session) {
		ModelAndView model = new ModelAndView("cart");
		Cart cart = ((Cart) session.getAttribute("cart"));
		if (cartItem.getAmount().intValue() < 0)
			return model;
		if (cart.getItemList().contains(cartItem)) {
			if (cartItem.getAmount().intValue() == 0) {
				cart.getItemList().remove(cartItem);
				return model;
			}
			if (cartItem.getAmount().intValue() > 0) {
				Integer maxAmount = clientService.getProductById(cartItem.getProduct().getId()).getStorage()
						.getAmount();
				CartItem current = cart.getItemList().get(cart.getItemList().indexOf(cartItem));
				if ((current.getAmount() + cartItem.getAmount()) > maxAmount) {
					cart.getItemList().get(cart.getItemList().indexOf(cartItem)).setAmount(maxAmount);
				}
				else cart.addToCartItem(cartItem);
				return model;
			}

		}

		cart.setCartItem(cartItem);
		model.addObject("title", "Your cart");
		return model;
	}

	@RequestMapping(value = "/cartContent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Cart getCategoryProducts(HttpSession session) {
		return (Cart) session.getAttribute("cart");
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView putToCart(@RequestBody CartItem cartItem, HttpSession session) {
		System.out.println(cartItem.getProduct().getName() + " id:" + cartItem.getProduct().getId() + " amount:"
				+ cartItem.getAmount());
		Cart cart = (Cart) session.getAttribute("cart");
		ModelAndView model = new ModelAndView("cart");
		if (cart.getItemList() == null) {
			cart.setItemList(new LinkedList<CartItem>());
		}
		if (cart.getItemList().contains(cartItem)) {
			Integer maxAmount = clientService.getProductById(cartItem.getProduct().getId()).getStorage().getAmount();
			CartItem current = cart.getItemList().get(cart.getItemList().indexOf(cartItem));
			if ((current.getAmount() + cartItem.getAmount()) > maxAmount) {
				cart.getItemList().get(cart.getItemList().indexOf(cartItem)).setAmount(maxAmount);
				// model.addObject("message", )
				return model;
			}
			cart.addToCartItem(cartItem);
		} else
			cart.getItemList().add(cartItem);
		model.addObject("cart", cart);
		model.addObject("lol", "String ;ol");
		return model;
	}
}
