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
}