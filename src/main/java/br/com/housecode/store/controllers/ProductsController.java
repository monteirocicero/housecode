package br.com.housecode.store.controllers;

import javax.servlet.http.Part;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.housecode.store.daos.ProductDAO;
import br.com.housecode.store.models.BookType;
import br.com.housecode.store.models.Product;

@Controller
@Transactional
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Part summary, @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println(summary.getName() + "; " + summary.getHeader("content-disposition"));
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		System.out.println("Register the product " + product);
		return new ModelAndView("redirect:products");
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

}
