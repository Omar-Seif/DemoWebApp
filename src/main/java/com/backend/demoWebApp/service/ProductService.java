package com.backend.demoWebApp.service;

import com.backend.demoWebApp.exception.ProductNotFoundException;
import com.backend.demoWebApp.repository.ProductRepository;
import com.backend.demoWebApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){ // ← Changed parameter to Long
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProduct(Product product){
       return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        Long productId = product.getId();
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

}
