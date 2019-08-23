package sample;

import sample.control.JanelaBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


import java.io.IOException;

public class Navegador{

    public static final String JANELA_PRINCIPAL    = "/view/TelaPrincipal.fxml";
    public static final String JANELA_PIZZA    = "/view/TelaPizza.fxml";
    public static final String JANELA_CADASTRO = "/view/TelaCadastro.fxml";
    public static final String JANELA_BASE = "/view/TelaBase.fxml";
    public static final String JANELA_CLIENTE = "/view/TelaCliente.fxml";
    public static final String JANELA_ATUALIZARCLIENTE = "view/TelaAtualizarCliente";
    public static final String JANELA_ATUALIZARPIZZA = "view/TelaAtualizarPizza";

    private static JanelaBase controlador;



    public static void setControlador(JanelaBase controlador) {
        Navegador.controlador = controlador;
    }

    public static void loadJanela(String fxml) {
        try { controlador.setJanela((Node) FXMLLoader.load(Navegador.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}