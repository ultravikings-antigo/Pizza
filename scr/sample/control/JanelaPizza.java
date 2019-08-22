package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Navegador;
import sample.model.Cliente;
import sample.model.Pizza;
import sample.model.Pizzaria;

import java.sql.SQLException;
import java.text.DecimalFormat;

public class JanelaPizza extends Avisos{

    @FXML
    private ListView<Pizza> ltvCardapio;

    @FXML
    private ListView<Pizza> ltvPizzas;

    @FXML
    private ListView<Cliente> ltvCliente;

    @FXML
    private Button bAbrirPedido;

    @FXML
    private Button bAdicionarPizza;

    @FXML
    private Button bFinalizarPedido;

    @FXML
    private Label lbTotal;

    @FXML
    void telaPrincipal() throws Exception {
        if (Pizzaria.getInstance().verPedido() == null){
            Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
        }else{
            Boolean decisão = mensagemConfirmar("Realmente Quer Sair ?\nAinda Há Um Pedido Aberto");
            if (decisão != Boolean.FALSE){
                Pizzaria.getInstance().fecharPedido();
                Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
            }
        }
    }

    public void initialize() throws SQLException {

        bFinalizarPedido.setDisable(true);
        bAdicionarPizza.setDisable(true);

        ltvCardapio.getItems().clear();
        ltvCardapio.getItems().addAll(Pizzaria.getInstance().listarPizzas());
        ltvCliente.getItems().addAll(Pizzaria.getInstance().listarCliente());
        lbTotal.setText("Total: 0.00");

    }

    @FXML
    public void acaoAdicionar() throws Exception {

        if (Pizzaria.getInstance().verPedido() == null){
            mensagem(Alert.AlertType.INFORMATION, "\nÉ Necessario Abrir Um Pedido");
        }else if(ltvCardapio.getSelectionModel().getSelectedItem() == null){
            mensagem(Alert.AlertType.INFORMATION,"\nÈ Necessario Selecionar Uma Pizza");
        }else{
            ltvPizzas.setItems(Pizzaria.getInstance().getListaPedido());
            Pizzaria.getInstance().incluirPizza(ltvCardapio.getSelectionModel().getSelectedItem());
            DecimalFormat df = new DecimalFormat("###,##0.00");
            lbTotal.setText("Total: "+ df.format(Double.valueOf(Pizzaria.getInstance().valorPedido())));
        }
    }

    @FXML
    public void acaoPedir() throws Exception {
        if (Pizzaria.getInstance().verPedido() == null){
            mensagem(Alert.AlertType.INFORMATION, "\nÉ Necessario Abrir Um Pedido");
        }else {
            Pizzaria.getInstance().fecharPedido();
            DecimalFormat df = new DecimalFormat("###,##0.00");
            ltvPizzas.getItems().clear();
            if (lbTotal.getText() != ""){
                mensagem(Alert.AlertType.INFORMATION,"Pedido Finalizado Com Sucesso\nValor Total: "+lbTotal.getText());
            }else{
                mensagem(Alert.AlertType.INFORMATION,"\nPedido Finalizado Com Sucesso");
            }
            lbTotal.setText("Total: 0.00");
            bAbrirPedido.setDisable(false);
            bFinalizarPedido.setDisable(true);
            bAdicionarPizza.setDisable(true);

        }
    }

    @FXML
    public void abrirPedido() throws Exception {
        if (Pizzaria.getInstance().listarPizzas().size() == 0){

        }else{
            bAbrirPedido.setDisable(true);
            bFinalizarPedido.setDisable(false);
            bAdicionarPizza.setDisable(false);

            Pizzaria.getInstance().abrirPedido();
        }
    }

    @FXML
    public void buscaPizzas(KeyEvent evt) throws SQLException {

        String texto = ((TextField)evt.getSource()).getText();
        if(evt.getCode() != KeyCode.BACK_SPACE){
            texto += evt.getText();
        }

        if(texto.length()==0){

            ltvCardapio.getItems().clear();
            ltvCardapio.getItems().addAll(Pizzaria.getInstance().listarPizzas());
            ((TextField)evt.getSource()).setText("");
        }else{
            if(texto.length() >= 3){
                ltvCardapio.getItems().clear();
                ltvCardapio.getItems().addAll(Pizzaria.getInstance().pesquisarSabor(texto+"%"));
            }
        }
    }

    @FXML
    public void buscaClientes(KeyEvent evt) throws SQLException {
        String texto = ((TextField)evt.getSource()).getText();
        if(evt.getCode() != KeyCode.BACK_SPACE){
            texto += evt.getText();
        }

        if(texto.length()==0){
            ltvCliente.getItems().clear();
            ltvCliente.getItems().addAll(Pizzaria.getInstance().listarCliente());
            ((TextField)evt.getSource()).setText("");
        }else{
            if(texto.length() >= 3){
                ltvCliente.getItems().clear();
                ltvCliente.getItems().addAll(Pizzaria.getInstance().pesquisarNome(texto+"%"));
            }
        }
    }

}
