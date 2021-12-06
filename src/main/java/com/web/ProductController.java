package com.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.WebSecurityConfig;
import com.model.Product;
import com.service.ProductService;
import com.validator.ProductValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        if (WebSecurityConfig.isAuthenticated()) {
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.findAll());

        return "admin/productsList";
    }

    @GetMapping("/admin/products/create")
    public String adminProductsCreate(Model model) {
        model.addAttribute("productForm", new Product());
        return "admin/productsForm";
    }

    @PostMapping("/admin/products/create")
    public String adminProductsForm(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult,
            @RequestParam("image") MultipartFile file) throws IOException {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/productsForm";
        }

        String projectDir = Paths.get("").toAbsolutePath().toString();
        String fileImgname = StringUtils.cleanPath(file.getOriginalFilename());

        String uploadImgDir = projectDir + "\\src\\main\\resources\\static\\images";

        Path uploadImgpath = Paths.get(uploadImgDir);

        if (!Files.exists(uploadImgpath)) {
            Files.createDirectories(uploadImgpath);
        }

        try {
            InputStream is = file.getInputStream();
            Path fileImgpath = uploadImgpath.resolve(fileImgname);
            Files.copy(is, fileImgpath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        productForm.setImageUrl("\\images\\" + fileImgname);

        productService.save(productForm);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/edit")
    public String adminProductsEdit(Model model, @RequestParam("id") int id) {
        Product product = productService.findById(id);

        model.addAttribute("productForm", product);
        return "admin/productsForm";
    }

    @GetMapping("/admin/products/delete")
    public String adminProductsDelete(@RequestParam("id") int id) throws IOException {
        Product product = productService.findById(id);

        String projectDir = Paths.get("").toAbsolutePath().toString();
        Files.delete(Paths.get(projectDir + "\\src\\main\\resources\\static\\" + product.getImageUrl()));

        productService.delete(product);

        return "redirect:/admin/products";
    }
}