package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TelaCliente extends Application {

    private final ClienteApiClient apiClient = new ClienteApiClient("https://clientes-api.onrender.com");

    @Override
    public void start(Stage stage) {
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do cliente");

        TextField limiteField = new TextField();
        limiteField.setPromptText("Limite de crédito");

        Button criarButton = new Button("Criar Cliente");
        Button buscarPedidosButton = new Button("Buscar Pedidos");

        Label resultadoLabel = new Label();
        TextArea pedidosArea = new TextArea();
        pedidosArea.setEditable(false);

        criarButton.setOnAction(e -> {
            try {
                double limite = Double.parseDouble(limiteField.getText());
                Cliente cliente = new Cliente(nomeField.getText(), limite);
                Cliente criado = apiClient.criarCliente(cliente);
                resultadoLabel.setText("Cliente criado: " + criado.nome);
            } catch (Exception ex) {
                resultadoLabel.setText("Erro: " + ex.getMessage());
            }
        });

        buscarPedidosButton.setOnAction(e -> {
            try {
                List<Pedido> pedidos = apiClient.buscarPedidos(nomeField.getText());
                StringBuilder sb = new StringBuilder();
                for (Pedido pedido : pedidos) {
                    sb.append("ID: ").append(pedido.id)
                      .append(" | Valor: R$").append(pedido.valorTotal)
                      .append(" | Status: ").append(pedido.liberado ? "Liberado" : "Bloqueado")
                      .append("\n");
                }
                pedidosArea.setText(sb.toString());
            } catch (Exception ex) {
                pedidosArea.setText("Erro: " + ex.getMessage());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Limite:"), 0, 1);
        grid.add(limiteField, 1, 1);
        grid.add(criarButton, 0, 2);
        grid.add(buscarPedidosButton, 1, 2);
        grid.add(resultadoLabel, 0, 3, 2, 1);

        VBox vbox = new VBox(10, grid, new Label("Pedidos:"), pedidosArea);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Controle de Pedidos - Cliente");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}