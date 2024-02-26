package com.alwi.irfani.controller;

import com.alwi.irfani.dto.SearchFormData;
import com.alwi.irfani.entity.Product;
import com.alwi.irfani.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String welcome(Model model) {
        // do something
        String message = "Welcome to spring MVC";
        model.addAttribute("message",message);
        model.addAttribute("searchForm", new SearchFormData());
        model.addAttribute("products", productService.getAll());
        return "index";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    @PostMapping(path = "/save")
    public String save(Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping(path = "/update")
    public String update(Product product) {
        productService.updateProduct(product);
        return "redirect:/";
    }

    @PostMapping(path = "/search")
    public String search(SearchFormData searchFormData, Model model) {
        String message = "Welcome to spring MVC";
        model.addAttribute("message",message);
        model.addAttribute("searchForm", searchFormData);
        model.addAttribute("products", productService.findByName(searchFormData.getKeyword()));
        return "index";
    }

    @GetMapping(path = "/deleteAll")
    public String deleteAll() {
        productService.deleteAll();
        return "redirect:/";
    }
}
