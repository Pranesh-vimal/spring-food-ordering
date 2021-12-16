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
        Product p ;
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            for(int i=1;i<=2;i++){
                p = new Product();
                p.setName("Food"+i);
                p.setCategory("Vegetarian");
                p.setPrice(Double.valueOf(i*10));
                p.setDescription("This is Food"+i);
                p.setUnit("Per Kg");
                p.setImageUrl("\\images\\authimg.jpg");
                productRepository.save(p);
            }
        }
    }
}
