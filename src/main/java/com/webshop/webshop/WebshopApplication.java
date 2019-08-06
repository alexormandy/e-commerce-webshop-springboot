package com.webshop.webshop;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopApplication {

	private static ProductDAO productDAO;

	@Autowired
	public WebshopApplication(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);

		productDAO.clearRepository();

		ProductModel product1 = new ProductModel("Rubber Duck", "Yellow Duck", 12D, "http://google.com");
		ProductModel product2 = new ProductModel("Football", "Red Ball", 20D, "http://google.com");

		productDAO.saveProduct(product1);
		productDAO.saveProduct(product2);
	}

}
