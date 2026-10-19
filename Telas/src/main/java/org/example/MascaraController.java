package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class MascaraController {

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pendurar;

    @FXML
    private Button btn_produzir;

    @FXML
    private Button btn_restaurar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_estilo;

    @FXML
    private TextField txt_face;

    @FXML
    private TextField txt_material;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Mascara m;

    @FXML
    void criar(ActionEvent event) {
        String material = txt_material.getText();
        String face = txt_face.getText();
        String estilo = txt_estilo.getText();

        m = new Mascara(material, face, estilo);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Máscara criada com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_material.clear();
        txt_face.clear();
        txt_estilo.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pendurar(ActionEvent event) {
        txt_resultado.setText((m.pendurar()));

    }

    @FXML
    void produzir(ActionEvent event) {
        txt_resultado.setText((m.produzir()));
    }

    @FXML
    void restaurar(ActionEvent event) {
        txt_resultado.setText((m.restaurar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
