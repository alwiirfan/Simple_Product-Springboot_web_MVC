package com.alwi.irfani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alwi.irfani.service.ProductService;

import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(path = "/products")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping(path = "/export-to-excel")
    public void exportToExcel(HttpServletResponse response) {
        // export to excel
        response.setContentType("application/octet-stream");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products-information" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        try {
            productService.exportToExcel(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/upload")
    public String upload() {
        return "upload";
    }
    

    @PostMapping(path = "/import-from-excel")
    public String importFromExcel(@RequestParam("file") MultipartFile file, Model model) {
        // Validate file and handle the import
        if (!file.isEmpty()) {
            try {
                productService.saveProductsFromExcelToDatabase(file);
                // Add success message to the model
                model.addAttribute("successMessage", "Import successful!");
            } catch (Exception e) {
                // Handle exceptions, add error message to the model
                model.addAttribute("errorMessage", "Error during import: " + e.getMessage());
            }
        } else {
            // Handle empty file, add error message to the model
            model.addAttribute("errorMessage", "Please select a file to upload.");
        }

        model.addAttribute("products", productService.getAll());

        // Redirect to the upload page
        return "redirect:/";
    }
    
}
