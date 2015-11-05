package br.com.housecode.store.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.housecode.store.conf.AppWebConfiguration;
import br.com.housecode.store.conf.DataSourceConfigurationTest;
import br.com.housecode.store.conf.JPAConfiguration;
import br.com.housecode.store.conf.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppWebConfiguration.class, JPAConfiguration.class, SecurityConfiguration.class, 
		DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductControllerTest {
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	@Test
	@Transactional
	public void shouldListAllBooksInTheHome() throws Exception {
		this.mockMvc.perform(get("/products"))
			.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products/list.jsp"));
	}
	
	@Test
	public void onlyAdminShouldAccessProductsForm() throws Exception {
		 this.mockMvc.perform(
                 get("/products/form").with(
                                 SecurityMockMvcRequestPostProcessors
                                                 .user("comprador@gmail.com").password("123456")
                                                 .roles("COMPRADOR"))).andExpect(
                 status().is(403));

	}

}
