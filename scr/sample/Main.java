package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.control.JanelaBase;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws Exception {

        Pane root = loadMainPane();
        stage.setScene(new Scene(root, 1280, 720));

        stage.setTitle("Pizzaria...");
        stage.show();
        stage.setResizable(false);
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Navegador.JANELA_BASE
                )
        );

        JanelaBase controller = loader.getController();

        Navegador.setControlador(controller);
        Navegador.loadJanela(Navegador.JANELA_PRINCIPAL);

        return mainPane;
    }
}
