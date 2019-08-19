package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Navegador;
import sample.model.Cliente;
import sample.model.Pizza;
import sample.model.Pizzaria;

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
    private TextField tfValor;

    @FXML
    private Button bAbrirPedido;

    @FXML
    private Button bAdicionarPizza;

    @FXML
    private Button bFinalizarPedido;

    @FXML
    void telaPrincipal() throws Exception {
        if (Pizzaria.getInstance().verPedido() == null){
            Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
        }else{
            Boolean decisão = mensagemConfirmar("Realmente Quer Sair ?\nAinda Há Um Pedido Aberto");
            if (decisão == true){
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
            tfValor.setText(String.valueOf(Pizzaria.getInstance().valorPedido()));
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
            if (tfValor.getLength() != 0){
                mensagem(Alert.AlertType.INFORMATION,"Pedido Finalizado Com Sucesso\nValor Total:"+df.format(Double.valueOf(tfValor.getText())));
            }else{
                mensagem(Alert.AlertType.INFORMATION,"\nPedido Finalizado Com Sucesso");
            }
            tfValor.clear();
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
}
