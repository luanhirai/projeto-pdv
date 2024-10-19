package com.example.demo.Model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "valor_total")
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToMany
    @JoinTable(
            name = "transaction_products",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Products> products;

    @Column(name = "create_at")
    private Timestamp createAt;

    public Transaction() {
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    public Transaction(int id, double valorTotal, User seller, List<Products> products) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.seller = seller;
        this.products = products;
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
