package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ClienteApiClient {
    private final String baseUrl;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public ClienteApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public Cliente criarCliente(Cliente cliente) throws Exception {
        String json = mapper.writeValueAsString(cliente);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/clientes"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Cliente.class);
    }

    public List<Pedido> buscarPedidos(String nome) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/clientes/" + nome + "/pedidos"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Arrays.asList(mapper.readValue(response.body(), Pedido[].class));
    }
}