package com.example;

public class Pedido {
    public String descricao;
    public double valor;

    // Construtor padrão (obrigatório para desserialização JSON)
    public Pedido() {
    }

    public Pedido(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e setters (opcional, só se quiser usar encapsulamento)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
