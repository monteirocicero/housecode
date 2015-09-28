package br.com.housecode.store.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.housecode.store.daos.ProductDAO;
import br.com.housecode.store.models.BookType;
import br.com.housecode.store.models.Product;

@Controller
@Transactional
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/products")
	public String save(Product product) {
		productDAO.save(product);
		System.out.println("Register the product " + product);
		return "products/ok";
	}
	
	@RequestMapping("/products/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

}
