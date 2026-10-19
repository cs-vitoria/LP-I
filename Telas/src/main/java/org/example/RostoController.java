package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class RostoController {

    @FXML
    private Button btn_hidratar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button bt_limpar;

    @FXML
    private Button btn_maquiar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_contraste;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_formato;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Rosto r;

    @FXML
    void criar(ActionEvent event) {
        String cor_olho = txt_cor.getText();
        String contraste = txt_contraste.getText();
        String formato = txt_formato.getText();

        r = new Rosto(cor_olho, contraste, formato);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Rosto criado com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void hidratar(ActionEvent event) {
        txt_resultado.setText((r.hidratar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_contraste.clear();
        txt_formato.clear();
        txt_resultado.setText("");
    }

    @FXML
    void maquiar(ActionEvent event) {
        txt_resultado.setText((r.maquiar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }
    @FXML
    void limpar_rosto(ActionEvent event) {
        txt_resultado.setText((r.limpar_rosto()));
    }


}
