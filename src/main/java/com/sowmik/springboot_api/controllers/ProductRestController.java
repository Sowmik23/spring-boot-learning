package com.sowmik.springboot_api.controllers;

import com.sowmik.springboot_api.data_jpa.entities.Product;
import com.sowmik.springboot_api.data_jpa.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {


    //how to use logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);



    @Autowired
    ProductRepository productRepository;

    //get all the products
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //get a specific product by it's id
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") int id){
        LOGGER.info("Finding product by id: "+id);
        return productRepository.findById(id).get();
    }

    //create new product
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //update an existing product
    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //delete a product by it's id
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);
    }
}
