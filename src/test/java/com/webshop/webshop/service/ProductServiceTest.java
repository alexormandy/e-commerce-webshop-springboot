package com.webshop.webshop.service;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.dao.StockDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private StockDAO stockDAO;

    @Before
    public void setUp() {

        productService = new ProductService(productDAO, stockDAO);
    }

    @Test
    public void givenProductModelProvidesSizeStringWhenProductIsPassedInThenRegExSplitsTheString() {

        StockModel stockModel1 = new StockModel("Small", "Blue", 54);
        ProductModel productModel1 = new ProductModel();
        productModel1.setId(10L);
        stockModel1.setProductModel(productModel1);

        StockModel stockModel2 = new StockModel("Medium", "Orange", 8454);
        ProductModel productModel2 = new ProductModel();
        productModel2.setId(10L);
        stockModel2.setProductModel(productModel2);

        StockModel stockModel3 = new StockModel("Large", "Blue", 34);
        ProductModel productModel3 = new ProductModel();
        productModel3.setId(10L);
        stockModel3.setProductModel(productModel3);

        StockModel stockModel4 = new StockModel("Small", "Red", 34);
        ProductModel productModel4 = new ProductModel();
        productModel4.setId(11L);
        stockModel4.setProductModel(productModel4);

        StockModel stockModel5 = new StockModel("Medium", "Yellow", 12);
        ProductModel productModel5 = new ProductModel();
        productModel5.setId(11L);
        stockModel5.setProductModel(productModel5);

        List<StockModel> stock = new ArrayList<>();
        stock.add(stockModel1);
        stock.add(stockModel2);
        stock.add(stockModel3);
        stock.add(stockModel4);
        stock.add(stockModel5);

        ProductModel productModel = new ProductModel();
        productModel.setId(10L);

        given(stockDAO.findAll()).willReturn(stock);

        List stockList = productService.getStockDetails(productModel);

        List<String> expectedStockList;
        expectedStockList = Collections.unmodifiableList(Arrays.asList("Blue Small (54 in Stock)", "Orange Medium (8454 in Stock)", "Blue Large (34 in Stock)"));

        assertEquals(expectedStockList, stockList);
    }

}
