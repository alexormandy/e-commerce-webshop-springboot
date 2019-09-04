package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.model.ProductModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductModel mockProductModelA;

    private ProductService productService;

    @Before
    public void setUp() {

        productService = new ProductService();
    }

    @Test
    public void givenProductModelProvidesSizeStringWhenProductIsPassedInThenRegExSplitsTheString() {

        List testProductByIdSizes = new ArrayList();
        testProductByIdSizes.add("Small");
        testProductByIdSizes.add("Medium");
        testProductByIdSizes.add("Large");

        List testProductByIdSizes2 = new ArrayList();
        testProductByIdSizes.add("Small");
        testProductByIdSizes.add("Medium");
        testProductByIdSizes.add("Large");

        given(productService.getStockDetails(mockProductModelA)).willReturn(testProductByIdSizes2);

        assertEquals(testProductByIdSizes, testProductByIdSizes2);
    }

}
