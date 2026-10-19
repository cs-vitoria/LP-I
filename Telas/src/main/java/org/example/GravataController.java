package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class GravataController {

    @FXML
    private Button btn_amarrar;

    @FXML
    private Button btn_cortar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pendurar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_estampa;

    @FXML
    private TextField txt_modelo;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_textura;

    @FXML
    private Button btn_criar;

    private Gravata g;

    @FXML
    void criar(ActionEvent event) {
        String textura = txt_textura.getText();
        String estampa = txt_estampa.getText();
        String modelo = txt_modelo.getText();

        g = new Gravata(textura, estampa, modelo);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText(" Gravta criada com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void amarrar(ActionEvent event) {
        txt_resultado.setText((g.amarrar()));
    }

    @FXML
    void cortar(ActionEvent event) {
        txt_resultado.setText((g.cortar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_textura.clear();
        txt_modelo.clear();
        txt_estampa.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pendurar(ActionEvent event) {
        txt_resultado.setText((g.pendurar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
