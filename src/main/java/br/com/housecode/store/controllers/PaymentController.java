package br.com.housecode.store.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.housecode.store.models.PaymentData;
import br.com.housecode.store.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				System.out.println("[PaymentController] checkout code: " + response);
				return "redirect:/payment/success";
			} catch (HttpClientErrorException e) {
				return "redirect:/payment/error";
			}
		};
	}

}
