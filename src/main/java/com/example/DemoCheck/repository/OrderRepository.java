package com.example.DemoCheck.repository;
import com.example.DemoCheck.entity.Order;
import com.example.DemoCheck.projection.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;

@RepositoryRestResource(
        path = "orders",
        excerptProjection = OrderProjection.class
)
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @RestResource(path = "byCustomer", rel = "byCustomer")
    Page<Order> findByCustomer_CustomerNumber(
            @Param("customerNumber") Integer customerNumber,
            Pageable pageable
    );

    @RestResource(path = "byStatus", rel = "byStatus")
    Page<Order> findByStatus(
            @Param("status") String status,
            Pageable pageable
    );

    @RestResource(path = "byOrderDate", rel = "byOrderDate")
    Page<Order> findByOrderDate(
            @Param("orderDate") LocalDate orderDate,
            Pageable pageable
    );

    @RestResource(path = "byOrderDateBetween", rel = "byOrderDateBetween")
    Page<Order> findByOrderDateBetween(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            Pageable pageable
    );

    @RestResource(path = "byCustomerAndStatus", rel = "byCustomerAndStatus")
    Page<Order> findByCustomer_CustomerNumber(
            @Param("customerNumber") Integer customerNumber,
            @Param("status") String status,
            Pageable pageable
    );

    @RestResource(path = "byShippedDate", rel = "byShippedDate")
    Page<Order> findByShippedDate(
            @Param("shippedDate") LocalDate shippedDate,
            Pageable pageable
    );
}