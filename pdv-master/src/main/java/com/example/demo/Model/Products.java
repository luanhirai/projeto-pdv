package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo_de_barras")
    private String codigodebarras;

    @Column(name = "name")
    private String name;

    @Column(name = "valor")
    private double valor;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "ncm")
    private String ncm;

    @ManyToMany(mappedBy = "products")
    private List<Transaction> transactions;



    public Products(String name, double valor, int quantity, String codigodebarras, String ncm) {
        this.name = name;
        this.valor = valor;
        this.quantity = quantity;
        this.codigodebarras = codigodebarras;
        this.ncm = ncm;
    }

    public Products() {
        // Construtor padrão
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodigodebarras() {
        return codigodebarras;
    }

    public void setCodigodebarras(String codigodebarras) {
        this.codigodebarras = codigodebarras;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }


}
