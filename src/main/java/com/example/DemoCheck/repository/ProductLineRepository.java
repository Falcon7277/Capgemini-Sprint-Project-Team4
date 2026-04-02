package com.example.DemoCheck.repository;

import com.example.DemoCheck.entity.Product;
import org.springframework.data.domain.Pageable;
import com.example.DemoCheck.entity.ProductLine;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource(path = "productlines")
public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

    @RestResource(path = "by-name-and-desc")
    Page<ProductLine> findByProductLineContainingIgnoreCaseAndTextDescriptionContainingIgnoreCase(
            @Param("name") String name,
            @Param("desc") String desc,
            Pageable pageable
    );

    @RestResource(path = "by-description")
    List<ProductLine> findByTextDescriptionContainingIgnoreCase(String keyword);
    // NEW: For Exact Image Matching
    Optional<ProductLine> findByImage(byte[] image);

    // NEW: For Analytics (Counts products per product line)
    @Query("SELECT p.productLine, COUNT(pr) FROM ProductLine p LEFT JOIN p.products pr GROUP BY p.productLine")
    List<Object[]> getProductCounts();
}