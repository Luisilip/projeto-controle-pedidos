package com.example;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "pedido-api")
public interface PedidoClient {

    @GET
    @Path("/pedidos/cliente/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Pedido> buscarPedidosPorCliente(@PathParam("nome") String nome);
}
