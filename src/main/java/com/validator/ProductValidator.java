package com.validator;

import com.model.Product;
import com.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unit", "NotEmpty");

        if (product.getId() == null && productService.findByName(product.getName()) != null) {
            errors.rejectValue("name", "Duplicate.productForm.name");
        }
    }

}
