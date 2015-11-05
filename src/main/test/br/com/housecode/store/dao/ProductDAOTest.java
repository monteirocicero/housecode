package br.com.housecode.store.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.housecode.store.builders.ProductBuilder;
import br.com.housecode.store.conf.DataSourceConfigurationTest;
import br.com.housecode.store.conf.JPAConfiguration;
import br.com.housecode.store.daos.ProductDAO;
import br.com.housecode.store.models.BookType;
import br.com.housecode.store.models.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationTest.class, ProductDAO.class, JPAConfiguration.class})
@ActiveProfiles("test")
public class ProductDAOTest {
	
	@Autowired
	ProductDAO productDAO;
	
	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		
		List<Product> printedBooks = ProductBuilder.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4).buildAll();
		
		printedBooks.stream().forEach(productDAO::save);
		
		List<Product> ebooks = ProductBuilder.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buildAll();
		
		ebooks.stream().forEach(productDAO::save);
		
		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}
	
	

}
