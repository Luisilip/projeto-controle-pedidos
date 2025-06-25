package com.example;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    ControleCreditoService controleCreditoService;

    @GET
    public List<Pedido> listar() {
        return Pedido.listAll();
    }

    @POST
    @Transactional
    public Pedido criar(Pedido pedido) {
        boolean aprovado = controleCreditoService.aprovarCredito(pedido.valorTotal);
        pedido.liberado = aprovado;
        pedido.persist();
        return pedido;
    }

    @GET
    @Path("/{id}")
    public Pedido buscar(@PathParam("id") Long id) {
        return Pedido.findById(id);
    }

    @GET
    @Path("/cliente/{nome}")
    public List<Pedido> buscarPorCliente(@PathParam("nome") String nome) {
        return Pedido.find("clienteNome", nome).list();
    }
}