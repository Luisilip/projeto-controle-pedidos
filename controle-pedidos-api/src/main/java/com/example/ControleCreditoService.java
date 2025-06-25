package com.example;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ControleCreditoService {
    private static final double LIMITE_CREDITO = 1000.0;

    public boolean aprovarCredito(double valorTotal) {
        return valorTotal <= LIMITE_CREDITO;
    }
}