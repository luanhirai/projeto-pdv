package com.example.demo.DTOS;

import com.example.demo.Model.Products;
import com.example.demo.Model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record TransactionRecordDto(@NotBlank List<Products> products, @NotNull BigDecimal value) {
}