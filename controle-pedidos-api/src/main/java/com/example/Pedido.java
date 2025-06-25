package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pedido extends PanacheEntity {
    public double valorTotal;
    public boolean liberado;
    public String clienteNome;

    public Pedido() {
    }

    public Pedido(double valorTotal, String clienteNome) {
        this.valorTotal = valorTotal;
        this.clienteNome = clienteNome;
        this.liberado = false;
    }
}