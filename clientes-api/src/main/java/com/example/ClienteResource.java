package com.example;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    @RestClient
    PedidoClient pedidoClient;

    @GET
    public List<Cliente> listar() {
        return Cliente.listAll();
    }

    @POST
    @Transactional
    public Cliente criar(Cliente cliente) {
        cliente.persist();
        return cliente;
    }

    @GET
    @Path("/{nome}/pedidos")
    public List<Pedido> buscarPedidos(@PathParam("nome") String nome) {
        return pedidoClient.buscarPedidosPorCliente(nome);
    }
}
