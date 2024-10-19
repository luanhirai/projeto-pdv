package com.example.demo.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserRecordDto(@NotBlank String name, @NotBlank String password) {
}