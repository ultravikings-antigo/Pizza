package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Navegador;
import sample.model.Pizzaria;

public class JanelaCliente extends Avisos{
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfAno;


    @FXML
    void telaPrincipal(){
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
    }

    @FXML
    void acaoCadastrar(){

        if (tfNome.getText().equals("") || tfTelefone.getText().equals("") || tfAno.getText().equals("")){
            mensagem(Alert.AlertType.ERROR,"\nInforme As Informações Corretas");
        }
        else{

            String nome = tfNome.getText();
            String telefone = tfTelefone.getText();
            String ano = tfAno.getText();

            Pizzaria.getInstance().cadastraCliente(nome,telefone,ano);
            Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);
        }
    }
}
