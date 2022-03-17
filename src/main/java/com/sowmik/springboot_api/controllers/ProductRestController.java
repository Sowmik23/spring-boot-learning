package com.sowmik.springboot_api.controllers;

import com.sowmik.springboot_api.data_jpa.entities.Product;
import com.sowmik.springboot_api.data_jpa.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Product Rest Endpoint") //this is added for swagger
//@Hidden //all things/endpoints will hide by using this annotation
public class ProductRestController {


    //how to use logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    ProductRepository productRepository;

    //get all the products
    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    @Hidden //this will hide
    public List<Product> getProducts(){
        return productRepository.findAll();
    }



    //get a specific product by it's id
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
   // @Transactional(readOnly = true)
    @Cacheable("product-cache")
    @Operation(summary = "Returns a product", description = "Some description") //this is for swagger
    public @ApiResponse(description = "Response product object") Product getProduct(@Parameter(description = "Takes product id") @PathVariable("id") int id){
        LOGGER.info("Finding product by id: "+id);
        return productRepository.findById(id).get();
    }


    //create new product
    //Here @Valid is added for validation
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }



    //update an existing product
    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productRepository.save(product);
    }



    //delete a product by it's id
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @CacheEvict("product-cache")
    public void deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);
    }
}
