package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Navegador;
import sample.model.Cliente;
import sample.model.Pedido;
import sample.model.Pizza;
import sample.model.Pizzaria;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class JanelaVisualizaPedidos {

    //tbPedidos
    @FXML
    private TableView<Pedido> tbPedidos;

    @FXML
    private TableColumn<Pedido,Integer> tcPedidoID;

    @FXML
    private TableColumn<Pedido, LocalDateTime> tcPedidoData;

    @FXML
    private TableColumn<Pedido, Double> tcPedidoValor;

    @FXML
    private TableColumn<Pedido, String> tcPedidoNome;

    //tb Pizzas do Pedido
    @FXML
    private TableView<Pizza> tbPedidoPizzas;

    @FXML
    private TableColumn<Pedido, String> tcPizzaSabor;

    @FXML
    private TableColumn<Pedido, Double> tcPizzaValor;

    @FXML
    void telaPrincipal() throws Exception {
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
    }

    public void initialize() throws SQLException {

        //mapeamento da tabela pedidos
        tcPedidoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPedidoData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcPedidoValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tcPedidoNome.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        //fazendo mapeamento da pizza
        tcPizzaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        tcPizzaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        //carrega dados dos pedidos
        tbPedidos.setItems(Pizzaria.getInstance().listarPedido());
    }

    @FXML
    private void carregarPizzas(){
        Pedido pedido = tbPedidos.getSelectionModel().getSelectedItem();

        if(pedido != null){
            tbPedidoPizzas.setItems(pedido.listaPizzas());
        }
    }
}
