package com.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

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
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostConstruct
    public void init() {
        productService.init();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        if (WebSecurityConfig.isAuthenticated()) {
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("products", productService.findAll());

        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("title", "Products");
        return "admin/productsList";
    }

    @GetMapping("/admin/products/create")
    public String adminProductsCreate(Model model) {
        model.addAttribute("productForm", new Product());
        model.addAttribute("title", "Create Product");
        Map<String, String> categories = new HashMap<>();
        categories.put("veg", "Vegetarian");
        categories.put("non-veg", "Non-Vegetarian");
        model.addAttribute("categories", categories);
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

    @GetMapping("/admin/products/{id}/edit")
    public String adminProductsEdit(Model model, @PathVariable("id") int id) {
        Product product = productService.findById(id);

        if (product.getId() == null) {
            return "redirect:/admin/products";
        }

        model.addAttribute("productForm", product);
        model.addAttribute("title", "Product Edit");

        Map<String, String> categories = new HashMap<>();
        categories.put("veg", "Vegetarian");
        categories.put("non-veg", "Non-Vegetarian");
        model.addAttribute("categories", categories);

        return "admin/productsForm";
    }

    @PostMapping("/admin/products/{id}/edit")
    public String adminProductsUpdate(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult,
            @RequestParam("image") MultipartFile file, Model model) throws IOException {
        productValidator.validate(productForm, bindingResult);

        Map<String, String> categories = new HashMap<>();
        categories.put("veg", "Vegetarian");
        categories.put("non-veg", "Non-Vegetarian");
        model.addAttribute("title", "Product Edit");
        model.addAttribute("categories", categories);

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

    @GetMapping("/admin/products/{id}/delete")
    public String adminProductsDelete(@PathVariable("id") int id) throws IOException {
        Product product = productService.findById(id);

        String projectDir = Paths.get("").toAbsolutePath().toString();
        Files.delete(Paths.get(projectDir + "\\src\\main\\resources\\static\\" + product.getImageUrl()));

        productService.delete(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

}