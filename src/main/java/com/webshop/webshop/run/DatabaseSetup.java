package com.webshop.webshop.run;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.dao.StockDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSetup {

    private ProductDAO productDAO;
    private StockDAO stockDAO;

    @Autowired
    public DatabaseSetup(ProductDAO productDAO, StockDAO stockDAO) {
        this.productDAO = productDAO;
        this.stockDAO = stockDAO;
        setUp();
    }

    public void setUp() {

        productDAO.clearRepository();
        stockDAO.clearRepository();

        StockModel stock1 = new StockModel("Small","Blue", 50);
        StockModel stock2 = new StockModel("Medium","Blue", 50);
        StockModel stock3 = new StockModel("4","Black", 50);
        StockModel stock4 = new StockModel("Large","Blue", 50);

        stockDAO.saveStock(stock1);
        stockDAO.saveStock(stock2);
        stockDAO.saveStock(stock3);
        stockDAO.saveStock(stock4);

        ProductModel product1 = new ProductModel("Navy Zak Puppytooth Suit Jacket", "Navy puppytooth suit jacket in a regular fit with a two button fasten, " +
                "jetted chest pocket with detachable pocket square", 12.50D,"/r08yy131704w.jpg", stock1,stock2);

        ProductModel product2 = new ProductModel("Blue Short Sleeve Textured Double Collar Shirt Long", "Stay on trend this season with our short sleeve double " +
                "collar button down shirt with a textured print design.", 20.99D, "/r08ca136705w.jpg", stock3);
//
//        ProductModel product3 = new ProductModel("Vans Ward Trainers", "The Ward trainers feature the iconic Vans classic side stripe, padded collars for comfort " +
//                "and platform rubber waffle outsoles for a firm grip.", 20D, "/q08yd333707s.jpg", stock2);
//
        ProductModel product4 = new ProductModel("Hype Speckle Fade T-Shirt", "Hype T-shirt in black, featuring all over speckle fade print and brand logo to the chest.", 25D, "/r08xa536740w.jpg", stock1);
//
//        ProductModel product5 = new ProductModel("Vans Filmore Trainers", "The classic lace-up skate shoe. We could tell you the whole history of why it has become a skating legend, but we'll let the comfort tell the story.", 52D, "/q08yd335907s.jpg", stock2);
//
//        ProductModel product6 = new ProductModel("Khaki Marl Over the Head Hoody Long", "Over-the-head hoody with contrast colour drawcord, kangaroo-style pockets and ribbed hem and cuffs.", 21.95D, "/r08yp852764w.jpg", stock1);

        productDAO.saveProduct(product1);
        productDAO.saveProduct(product2);
//        productDAO.saveProduct(product3);
        productDAO.saveProduct(product4);
//        productDAO.saveProduct(product5);
//        productDAO.saveProduct(product6);

    }

}
