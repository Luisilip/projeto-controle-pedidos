package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends PanacheEntity {
    public String nome;
    public double limiteCredito;

    public Cliente() {
    }

    public Cliente(String nome, double limiteCredito) {
        this.nome = nome;
        this.limiteCredito = limiteCredito;
    }
}