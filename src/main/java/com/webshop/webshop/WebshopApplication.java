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

		ProductModel product1 = new ProductModel("Navy Zak Puppytooth Suit Jacket", "Navy puppytooth suit jacket in a regular fit with a two button fasten, " +
				"jetted chest pocket with detachable pocket square", 12D, "/r08yy131704w.jpg");
		ProductModel product2 = new ProductModel("Blue Short Sleeve Textured Double Collar Shirt Long", "Stay on trend this season with our short sleeve double " +
				"collar button down shirt with a textured print design.", 20D, "/r08ca136705w.jpg");
		ProductModel product3 = new ProductModel("Vans Ward Trainers", "The Ward trainers feature the iconic Vans classic side stripe, padded collars for comfort " +
				"and platform rubber waffle outsoles for a firm grip.", 20D, "/q08yd333707s.jpg");

		productDAO.saveProduct(product1);
		productDAO.saveProduct(product2);
		productDAO.saveProduct(product3);
	}

}
