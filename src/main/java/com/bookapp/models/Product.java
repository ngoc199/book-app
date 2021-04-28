package com.bookapp.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int period; // Period is calculated by days

    @Column(columnDefinition = "TEXT")
    private String description;

    @DecimalMin(value = "0.00", message = "Price can't be smaller than {value}")
    @Digits(integer = 19, fraction = 2, message = "Price can't have more than {integer} digits in integer and more than {fraction} digits in fraction")
    private BigDecimal price;
}
