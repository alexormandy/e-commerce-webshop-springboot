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

    @Before
    public void setUp() {

        productServiceImpl = new ProductServiceImpl(productDAO, stockDAO);

        stockList = new ArrayList<>();
    }

    public void setupData( String productSize, String productColour, int productQuantity, long productId, List<StockModel> stockList) {
        StockModel stockModel = new StockModel();
        ProductModel productModel = new ProductModel();

        stockModel.setProductSize(productSize);
        stockModel.setProductColour(productColour);
        stockModel.setProductQuantity(productQuantity);
        productModel.setId(productId);
        stockModel.setProductModel(productModel);
        stockList.add(stockModel);
    }

    @Test
    public void givenProductModelProvidesSizeStringWhenProductIsPassedInThenRegExSplitsTheString() {

        setupData("Small", "Blue", 54, 10L, stockList);
        setupData("Medium", "Orange", 8454, 10L, stockList);
        setupData("Large", "Blue", 34, 10L, stockList);
        setupData("Small", "Red", 34, 11L, stockList);
        setupData("Medium", "Yellow", 12, 11L, stockList);

        ProductModel productModel = new ProductModel();
        productModel.setId(10L);

        given(stockDAO.findAll()).willReturn(stockList);

        List returnedStockList = productServiceImpl.getStockDetails(productModel);

        List<String> expectedStockList;
        expectedStockList = Collections.unmodifiableList(Arrays.asList("Blue Small (54 in Stock)", "Orange Medium (8454 in Stock)", "Blue Large (34 in Stock)"));

        assertEquals(expectedStockList, returnedStockList);
    }

}
