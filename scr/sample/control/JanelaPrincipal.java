package sample.control;


import javafx.fxml.FXML;
import sample.Navegador;


public class JanelaPrincipal {

    @FXML
    public void telaCadastrar(){
        Navegador.loadJanela(Navegador.JANELA_CADASTRO);
    }
    

    @FXML
    public void telaListar(){
        Navegador.loadJanela(Navegador.JANELA_PIZZA);
    }

    @FXML
    public void telaCliente() {
        Navegador.loadJanela(Navegador.JANELA_CLIENTE);
    }

    @FXML
    public void telaListarPedidos(){
        Navegador.loadJanela(Navegador.JANELA_VISUALIZAPEDIDOS);
    }

    @FXML
    public void telaListarCliente(){
        Navegador.loadJanela(Navegador.JANELA_VISUALIZACLIENTE);
    }

    @FXML
    public void telaListarPizza(){
        Navegador.loadJanela(Navegador.JANELA_VISUALIZAPIZZA);
    }
}