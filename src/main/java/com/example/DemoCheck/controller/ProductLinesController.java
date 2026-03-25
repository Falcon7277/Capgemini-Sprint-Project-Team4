package com.example.DemoCheck.controller;

import com.example.DemoCheck.entity.ProductLine;
import com.example.DemoCheck.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productlines")
public class ProductLinesController {

    @Autowired
    private ProductLineRepository repository;

    @GetMapping("/getAll")
    public List<ProductLine> getAllProductLines() {
        return repository.findAll();
    }

    @PostMapping("/create")
    public ProductLine createProductLine(@RequestBody ProductLine productLine) {
        productLine.setHtmlDescription(null);
        productLine.setImage(null);
        return repository.save(productLine);
    }
}