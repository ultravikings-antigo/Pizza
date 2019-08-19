package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Navegador;
import sample.model.Pizzaria;


public class JanelaCadastro extends Avisos{
    @FXML
    private TextField tfSabor;

    @FXML
    private TextField tfValor;


    @FXML
    void telaPrincipal(){
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
    }

    @FXML
    void acaoCadastrar(){

        if (tfSabor.getText().equals("") || tfValor.getText().equals("")){
           mensagem(Alert.AlertType.ERROR,"\nInforme Um Sabor E Um Valor Validos");
        }
        else{
            String sabor = tfSabor.getText();
            Double valor = Double.valueOf(tfValor.getText());
            Pizzaria.getInstance().cadastraPizza(sabor,valor);
            Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);

        }
    }
}
