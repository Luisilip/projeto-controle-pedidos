package com.example;

public class Cliente {
    public Long id;
    public String nome;
    public double limiteCredito;

    public Cliente() {
    }

    public Cliente(String nome, double limiteCredito) {
        this.nome = nome;
        this.limiteCredito = limiteCredito;
    }
}