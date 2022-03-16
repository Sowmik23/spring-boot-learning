package com.sowmik.springboot_api;


import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sowmik.springboot_api.data_jpa.entities.Product;
import com.sowmik.springboot_api.data_jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductRestControllerMvcUnitTest {


    private static final String PRODUCTS_URL = "/api/products";
    private static final String CONTEXT_URL = "/api";
    private static final int PRODUCT_PRICE = 100;
    private static final String PRODUCT_DESCRIPTION_STRING = "Nice product";
    private static final String PRODUCT_NAME_STRING = "Test product";
    private static final int PRODUCT_id = 30;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() throws Exception {

        Product product = buildProduct();
        List<Product> productList = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(productList);

        com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();

        mockMvc.perform(get(PRODUCTS_URL).contextPath(CONTEXT_URL))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content()
//                        .json("[{'id': 30, 'name': 'Test Product', 'description': 'Nice product', 'price': 100}]"));
                .json(objectWriter.writeValueAsString(productList)));
    }


    @Test
    public void createProduct() throws JsonProcessingException, Exception {
        Product product = buildProduct();
        when(productRepository.save(any())).thenReturn(product);

        com.fasterxml.jackson.databind.ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(post(PRODUCTS_URL).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product)))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().json(objectWriter.writeValueAsString(product)));
    }


    @Test
    public void updateProduct() throws JsonProcessingException, Exception {
        Product product = buildProduct();
        product.setPrice(1900);

        when(productRepository.save(any())).thenReturn(product);

        com.fasterxml.jackson.databind.ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(post(PRODUCTS_URL).contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json(objectWriter.writeValueAsString(product)));
    }

    @Test
    public void deleteProduct() throws Exception {
        doNothing().when(productRepository).deleteById(PRODUCT_id);

        mockMvc.perform(delete(PRODUCTS_URL+PRODUCT_id).contextPath(CONTEXT_URL))
                .andExpect(status().isOk());
    }


    private Product buildProduct(){
        Product product = new Product();
        product.setId(PRODUCT_id);
        product.setName(PRODUCT_NAME_STRING);
        product.setDescription(PRODUCT_DESCRIPTION_STRING);
        product.setPrice(PRODUCT_PRICE);

        return product;
    }

}
