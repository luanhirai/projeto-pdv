package com.example.demo.DTOS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRecordDto {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @NotNull(message = "Valor cannot be null")
    @Positive(message = "Valor must be greater than 0")
    private double valor;

    @NotNull(message="Codigo de barras não pode ser nulo")
    @Positive(message="")
    private String codigodebarras;

    @NotNull(message="NCM não pode ser nulo")
    @Positive(message="")
    private String ncm;

    public String getName() {
        return name;
    }

    @NotNull(message = "Valor cannot be null")
    @Positive(message = "Valor must be greater than 0")
    public double getValor() {
        return valor;
    }

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    public int getQuantity() {
        return quantity;
    }

    @NotNull(message = "Codigo de barras não pode ser nulo")
    @Positive(message = "")
    public String getCodigodebarras() {
        return codigodebarras;
    }

    @NotNull(message = "NCM não pode ser nulo")
    @Positive(message = "")
    public String getNcm() {
        return ncm;
    }

    public void setName(@NotNull(message = "Name cannot be null") String name) {
        this.name = name;
    }

    public void setQuantity(@NotNull(message = "Quantity cannot be null") @Min(value = 0, message = "Quantity must be greater than or equal to 0") int quantity) {
        this.quantity = quantity;
    }

    public void setValor(@NotNull(message = "Valor cannot be null") @Positive(message = "Valor must be greater than 0") double valor) {
        this.valor = valor;
    }

    public void setCodigodebarras(@NotNull(message = "Codigo de barras não pode ser nulo") @Positive(message = "") String codigodebarras) {
        this.codigodebarras = codigodebarras;
    }

    public void setNcm(@NotNull(message = "NCM não pode ser nulo") @Positive(message = "") String ncm) {
        this.ncm = ncm;
    }
}
