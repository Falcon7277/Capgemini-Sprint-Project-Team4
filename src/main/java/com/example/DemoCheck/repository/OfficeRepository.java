package com.example.DemoCheck.repository;

import com.example.DemoCheck.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "offices", collectionResourceRel = "offices")
public interface OfficeRepository extends JpaRepository<Office, String> {

    @RestResource(path = "by-cities")
    List<Office> findByCityIn(@Param("cities") List<String> cities);

    // 🔥 ONLY CHANGE → add ContainingIgnoreCase
    @RestResource(path = "by-city", rel = "by-city-paged")
    Page<Office> findByCityContainingIgnoreCase(@Param("city") String city, Pageable pageable);

    @RestResource(path = "by-state", rel = "by-state")
    Page<Office> findByStateContainingIgnoreCase(@Param("state") String state, Pageable pageable);

    @RestResource(path = "by-country", rel = "by-country")
    Page<Office> findByCountryContainingIgnoreCase(@Param("country") String country, Pageable pageable);

    @RestResource(path = "by-territory", rel = "by-territory")
    Page<Office> findByTerritoryContainingIgnoreCase(@Param("territory") String territory, Pageable pageable);
}