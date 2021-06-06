package com.tutorial.restapidesign.controller;

import com.tutorial.restapidesign.dto.ProductDTO;
import com.tutorial.restapidesign.entity.Product;
import com.tutorial.restapidesign.repository.ProductRepository;
import com.tutorial.restapidesign.util.Mapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/***
 *  Product Controller
 */

@RestController
@RequestMapping(name = "product_controller", value = "/api/v1/products")
public class ProductController {

    private Mapper mapper = new Mapper();

    @Autowired
    private ProductRepository repository;

    /***
     * Get All Products API-end point
     * End-point - /api/v1/products
     * Method - GET
     ***/
    @GetMapping
    public ResponseEntity Get() {
        List<ProductDTO> products = mapper.entityListToDtoList(repository.findAll());
        return new ResponseEntity(products,HttpStatus.OK);
    }

    /***
     * Get A Product By ID API-end point
     * End-point - /api/v1/products/{id}
     * Method _ GET
     ***/
    @GetMapping("/{id}")
    public ResponseEntity Get(@PathVariable("id") long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()) {
            return new ResponseEntity(product.get(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /***
     * Create New Product API-end point
     * End-point - /api/v1/products
     * Method - POST
     ***/
    @PostMapping
    public ResponseEntity Create(@NonNull @RequestBody ProductDTO productDTO) {
        // check for valid data entries
        if(productDTO.getName().isEmpty() || productDTO.getQty() <= 0 || productDTO.getPrice() <= 0.0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            Product product = repository.save(mapper.dtoToEntity(productDTO));
            return new ResponseEntity(mapper.entityToDto(product),HttpStatus.CREATED);
        }
    }

    /***
     * Update A Product API-end point
     * End-point - /api/v1/products/{id}
     * Method - PUT
     ***/
    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable("id") long id,@NonNull @RequestBody ProductDTO productDTO) {
        // check for valid data entries
        if(productDTO.getName().isEmpty() || productDTO.getQty() <= 0 || productDTO.getPrice() <= 0.0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
           Optional<Product> product = repository.findById(id);
           if(!product.isPresent()) {
               return new ResponseEntity(HttpStatus.NOT_FOUND);
           } else {
               Product product1 = mapper.dtoToEntity(productDTO);
               product1.setId(id);
               Product updatedProduct = repository.save(product1);
               return new ResponseEntity(mapper.entityToDto(updatedProduct),HttpStatus.OK);
           }
        }
    }

    /***
     * Delete A Product API-end point
     * End-point - /api/v1/products/{id}
     * Method - DELETE
     ***/
    @DeleteMapping("/{id}")
    public ResponseEntity Delete(@PathVariable("id") long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}