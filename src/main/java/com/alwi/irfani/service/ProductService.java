package com.alwi.irfani.service;

import com.alwi.irfani.entity.Product;
import com.alwi.irfani.repository.ProductRepository;
import com.alwi.irfani.utils.ExcelExport;
import com.alwi.irfani.utils.ExcelImport;
import com.alwi.irfani.utils.RandomNumber;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RandomNumber randomNumber;

    // export to excel / download file to excel
    public List<Product> exportToExcel(HttpServletResponse response) throws IOException {
        List<Product> products = productRepository.findAll();
        ExcelExport excelExport = new ExcelExport(products);
        excelExport.exportDataToExcel(response);
        return products;
    }

    // menyimpan dari file excel ke database MySQL
    public void saveProductsFromExcelToDatabase(MultipartFile file) {
        if (ExcelImport.isValidExcelFile(file)) {
            try {
                List<Product> products = ExcelImport.getProductsDataFromExcel(file.getInputStream());
                productRepository.saveAll(products);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file", e);
            }
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        product.setId(randomNumber.getRandom(1000L, 9999L));
        productRepository.save(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findByName(String keyword) {
        return productRepository.findByName(keyword);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}
