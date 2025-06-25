package com.example;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
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