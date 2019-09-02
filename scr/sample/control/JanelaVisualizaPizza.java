package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Navegador;
import sample.model.Pedido;
import sample.model.Pizza;
import sample.model.Pizzaria;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class JanelaVisualizaPizza {


    @FXML
    private TableView<Pizza> tbPizza;

    @FXML
    private TableColumn<Pizza,Integer> tcPizzaID;

    @FXML
    private TableColumn<Pizza, String> tcPizzaSabor;

    @FXML
    private TableColumn<Pizza, Double> tcPizzaValor;

    @FXML
    void telaPrincipal() throws Exception {
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
    }

    public void initialize() throws SQLException {


        tcPizzaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPizzaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        tcPizzaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tbPizza.setItems(Pizzaria.getInstance().listarPizzas());
    }
}
