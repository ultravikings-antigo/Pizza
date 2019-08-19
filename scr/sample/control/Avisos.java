package sample.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Avisos {

    protected void mensagem(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta!!");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    protected boolean mensagemConfirmar(String mensagem) {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Alerta!!");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        ButtonType buttonTypeQuero = new ButtonType("Quero!");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alerta.getButtonTypes().setAll(buttonTypeQuero,buttonTypeCancel);

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == buttonTypeQuero){
            return true;
        }else{
            return false;
        }
    }
}
