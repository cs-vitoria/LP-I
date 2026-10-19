package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class DesenhoController {

    @FXML
    private Button btn_apagar;

    @FXML
    private Button btn_esbocar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pintar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_estilo;

    @FXML
    private TextField txt_pintor;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Desenho d ;

    @FXML
    void criar(ActionEvent event) {
        String pintor = txt_pintor.getText();
        String estilo = txt_estilo.getText();
        String cor = txt_cor.getText();

        d = new Desenho (pintor, estilo, cor);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Desenho criado com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void apagar(ActionEvent event) {
        txt_resultado.setText((d.apagar()));
    }

    @FXML
    void esbocar(ActionEvent event) {
        txt_resultado.setText((d.esbocar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_pintor.clear();
        txt_estilo.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pintar(ActionEvent event) {
        txt_resultado.setText((d.pintar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
