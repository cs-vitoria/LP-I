package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button bt_senha;

    @FXML
    private Button btn_voltar;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_senha;
    @FXML
    private Button btn_limpar;

    @FXML
    void limpar(ActionEvent event) {
txt_senha.clear();
txt_resultado.setText("");
    }

    @FXML
    void entrar_senha(ActionEvent event) throws IOException {
        Login senha = new Login();
        txt_resultado.setText(senha.entrar_senha(String.valueOf(txt_senha.getText())));

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }

}
