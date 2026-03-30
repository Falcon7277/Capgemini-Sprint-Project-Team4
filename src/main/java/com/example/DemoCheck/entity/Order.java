package com.example.DemoCheck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;

    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;

    private String status;
    private String comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerNumber")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonIgnore   // ADD THIS
    List<OrderDetails> orderDetails;
}


