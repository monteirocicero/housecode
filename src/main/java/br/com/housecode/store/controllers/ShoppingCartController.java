package br.com.housecode.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.housecode.store.daos.ProductDAO;
import br.com.housecode.store.models.BookType;
import br.com.housecode.store.models.Product;
import br.com.housecode.store.models.ShoppingCart;
import br.com.housecode.store.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/products");
		
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String items() {
		return "shoppingCart/items";
	}
	

}
