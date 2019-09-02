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

//    @Test
//    public void givenProductModelProvidesSizeStringWhenProductIsPassedInThenRegExSplitsTheString() {
//
//        given(mockProductModelA.getProductSizes()).willReturn("Small,Medium,Large");
//        List mockProductByIdSizes = productService.getSizeDetails(mockProductModelA);
//
//        List testProductByIdSizes = new ArrayList();
//        testProductByIdSizes.add("Small");
//        testProductByIdSizes.add("Medium");
//        testProductByIdSizes.add("Large");
//
//        assertEquals(testProductByIdSizes, mockProductByIdSizes);
//    }


}
