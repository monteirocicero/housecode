package br.com.housecode.store.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.housecode.store.builders.ProductBuilder;
import br.com.housecode.store.daos.ProductDAO;
import br.com.housecode.store.models.BookType;
import br.com.housecode.store.models.Product;

public class ProductDAOTest {
	
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		ProductDAO productDAO = new ProductDAO();
		
		List<Product> printedBooks = ProductBuilder.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4).buildAll();
		
		printedBooks.stream().forEach(productDAO::save);
		
		List<Product> ebooks = ProductBuilder.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buildAll();
		
		ebooks.stream().forEach(productDAO::save);
		
		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}
	

}
