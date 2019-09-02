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

public class JanelaVisualizaCliente {

    @FXML
    private TableView<Cliente> tbCliente;

    @FXML
    private TableColumn<Cliente,Integer> tcClienteID;

    @FXML
    private TableColumn<Cliente, String> tcClienteNome;

    @FXML
    private TableColumn<Cliente, String> tcClienteTelefone;

    @FXML
    private TableColumn<Cliente, String> tcClienteAno;

    @FXML
    void telaPrincipal() throws Exception {
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
    }

    public void initialize() throws SQLException {

        //mapeamento da tabela pedidos
        tcClienteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcClienteTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcClienteAno.setCellValueFactory(new PropertyValueFactory<>("ano_nascimento"));

        tbCliente.setItems(Pizzaria.getInstance().listarCliente());
    }
}
