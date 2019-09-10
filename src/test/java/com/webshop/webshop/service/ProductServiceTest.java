package com.webshop.webshop.service;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.dao.StockDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import com.webshop.webshop.service.implementations.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private StockDAO stockDAO;

    private List<StockModel> stockList;

    final long productId = 10L;

    @Before
    public void setUp() {

        productServiceImpl = new ProductServiceImpl(productDAO, stockDAO);
        stockList = new ArrayList<>();
    }

    public void setupData(String productSize,
                          String productColour,
                          int productQuantity,
                          long productId,
                          List<StockModel> stockList) {

        ProductModel productModel = new ProductModel();
        productModel.setId(productId);

        StockModel stockModel = new StockModel(productSize, productColour, productQuantity);
        stockModel.setProductModel(productModel);
        stockList.add(stockModel);

    }

    @Test
    public void givenProductModelProvidesSizeStringWhenProductIsPassedInThenRegExSplitsTheString() {

        setupData("Small", "Blue", 54, productId, stockList);
        setupData("Medium", "Orange", 8454, productId, stockList);
        setupData("Large", "Blue", 34, productId, stockList);
        setupData("Small", "Red", 34, 11L, stockList);
        setupData("Medium", "Yellow", 12, 11L, stockList);

        ProductModel productModelLookup = new ProductModel();
        productModelLookup.setId(productId);

        given(stockDAO.findAll()).willReturn(stockList);
        List<String> returnedStockList = productServiceImpl.getStockDetails(productModelLookup);

        List<String> expectedStockList = new ArrayList<>();
        expectedStockList.add("Blue Small (54 in Stock)");
        expectedStockList.add("Orange Medium (8454 in Stock)");
        expectedStockList.add("Blue Large (34 in Stock)");

        assertEquals(expectedStockList, returnedStockList);
    }

}
