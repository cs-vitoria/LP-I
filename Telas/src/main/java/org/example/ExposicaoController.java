package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ExposicaoController {

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_observar;

    @FXML
    private Button btn_organizar;

    @FXML
    private Button btn_visitar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_artes;

    @FXML
    private TextField txt_dias;

    @FXML
    private TextField txt_local;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Exposicao e;

    @FXML
    void criar(ActionEvent event) {
        int dia = Integer.parseInt(txt_dias.getText());
        String local = txt_local.getText();
        String artes = txt_artes.getText();

        e = new Exposicao(dia, local, artes);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Exposição criada com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_artes.clear();
        txt_dias.clear();
        txt_local.clear();
        txt_resultado.setText("");
    }

    @FXML
    void observar(ActionEvent event) {
txt_resultado.setText(e.observar());
    }

    @FXML
    void organizar(ActionEvent event) {
        txt_resultado.setText(e.organizar());
    }

    @FXML
    void visitar(ActionEvent event) {
        txt_resultado.setText(e.visitar());
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
