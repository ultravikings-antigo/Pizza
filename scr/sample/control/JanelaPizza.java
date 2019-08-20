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
    private TextField tfPesquisaPizza;

    @FXML
    private TextField tfPesquisaCliente;

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
            if (decisão != Boolean.TRUE){
                Pizzaria.getInstance().fecharPedido();
                Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
            }
        }
    }

    public void initialize(){

        bFinalizarPedido.setDisable(true);
        bAdicionarPizza.setDisable(true);

        ltvCardapio.getItems().clear();
        ltvCardapio.getItems().addAll(Pizzaria.getInstance().listaSabores());
        ltvCliente.getItems().addAll(Pizzaria.getInstance().listaCliente());

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
            lbTotal.setText("Total:");
            bAbrirPedido.setDisable(false);
            bFinalizarPedido.setDisable(true);
            bAdicionarPizza.setDisable(true);

        }
    }

    @FXML
    public void abrirPedido() throws Exception {
        if (Pizzaria.getInstance().listaSabores().size() == 0){

        }else{
            bAbrirPedido.setDisable(true);
            bFinalizarPedido.setDisable(false);
            bAdicionarPizza.setDisable(false);

            Pizzaria.getInstance().abrirPedido();
        }
    }

    @FXML
    public void acaoPesquisaPizza(){
        if (tfPesquisaPizza.getText().equals("")){
            ltvCardapio.getItems().clear();
            ltvCardapio.getItems().addAll(Pizzaria.getInstance().listaSabores());
        }else{
            String sabor = tfPesquisaPizza.getText();
            sabor = "%" + sabor + "%";

            if (Pizzaria.getInstance().pesquisaSabor(sabor) != null){
                ltvCardapio.getItems().clear();
                ltvCardapio.getItems().addAll(Pizzaria.getInstance().pesquisaSabor(sabor));
            }
        }
    }

    @FXML
    public void acaoPesquisaCliente(){
        if (tfPesquisaCliente.getText().equals("")){
            ltvCliente.getItems().clear();
            ltvCliente.getItems().addAll(Pizzaria.getInstance().listaCliente());
        }else{
            String nome = tfPesquisaCliente.getText();
            nome = "%" + nome + "%";

            if (Pizzaria.getInstance().pesquisaNome(nome) != null){
                ltvCliente.getItems().clear();
                ltvCliente.getItems().addAll(Pizzaria.getInstance().pesquisaNome(nome));
            }
        }
    }
    @FXML
    public void buscaPizzas(KeyEvent evt){



        if(evt.getCode() == KeyCode.Z && evt.isControlDown()){
            Pizzaria.getInstance().listaSabores();
            ((TextField)evt.getSource()).setText("");
        }else{
            String texto = ((TextField)evt.getSource()).getText() + evt.getText();

            if(texto.length() >= 3){
                Pizzaria.getInstance().pesquisaSabor(texto);
            }
        }

        //mensagem(Alert.AlertType.ERROR,"Erro ao listar Pizzas!"+e.getMessage());
    }

    @FXML
    public void buscaClientes(KeyEvent evt){
        String texto = ((TextField)evt.getSource()).getText();
        if(evt.getCode() != KeyCode.BACK_SPACE){
            texto += evt.getText();
        }

        System.out.println(texto+" "+texto.length());
        if(evt.getCode() == KeyCode.Z && evt.isControlDown() || texto.length()==0){
            Pizzaria.getInstance().listaCliente();
            ((TextField)evt.getSource()).setText("");
        }else{


            if(texto.length() >= 3){
                Pizzaria.getInstance().pesquisaNome(texto);
            }
        }
    }

}
