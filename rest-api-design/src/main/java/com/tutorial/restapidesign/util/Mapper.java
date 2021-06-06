package com.tutorial.restapidesign.util;

import com.tutorial.restapidesign.dto.ProductDTO;
import com.tutorial.restapidesign.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public ProductDTO entityToDto(Product product) {
           return new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getQty()
                   ,product.getPrice());
    }

    public Product dtoToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(),productDTO.getDescription(),productDTO.getQty(),productDTO.getPrice());
    }

    public List<ProductDTO> entityListToDtoList(List<Product> productList) {
        List<ProductDTO> products = new ArrayList<>();
        for(Product product : productList) {
            products.add(entityToDto(product));
        }
        return products;
    }

    public List<Product> dtoListToEntityList(List<ProductDTO> productList) {
        List<Product> products = new ArrayList<>();
        for(ProductDTO product : productList) {
            products.add(dtoToEntity(product));
        }
        return products;
    }
}
