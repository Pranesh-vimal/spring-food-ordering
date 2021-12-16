package com.service;

import java.util.List;

import com.model.Product;
import com.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById((long) id).orElse(new Product());
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void init() {
        Product product;
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {

            for (int i = 1; i <= 2; i++) {
                product = new Product();
                product.setName("Food" + i);
                product.setCategory("non-veg");
                product.setPrice(Double.valueOf(i * 10));
                product.setDescription("This is Food" + i);
                product.setUnit("Per Kg");
                product.setImageUrl("\\images\\dummy.jpg");
                productRepository.save(product);
            }
        }
    }
}
