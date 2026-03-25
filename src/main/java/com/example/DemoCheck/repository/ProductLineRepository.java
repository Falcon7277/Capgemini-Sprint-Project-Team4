package com.example.DemoCheck.repository;

import com.example.DemoCheck.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLineRepository extends JpaRepository<ProductLine,String> {
}
