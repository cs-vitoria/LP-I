package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class BicicletaController {

    @FXML
    private Button btn_andar;

    @FXML
    private Button btn_freiar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_quebrar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_marca;

    @FXML
    private TextField txt_modelo;

    @FXML
    private Label txt_resultado;
    @FXML
    private Button btn_criar;

    private Bicicleta b;

    @FXML
    void criar(ActionEvent event) {
        String cor = txt_cor.getText();
        String modelo = txt_modelo.getText();
        String marca = txt_marca.getText();

        b = new Bicicleta (cor, modelo, marca);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Bicicleta criada com sucesso!");

        alerta.showAndWait();
    }


    @FXML
    void andar(ActionEvent event) {
txt_resultado.setText(b.andar());
    }

    @FXML
    void freiar(ActionEvent event) {
        txt_resultado.setText(b.freiar());
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_marca.clear();
        txt_modelo.clear();
        txt_resultado.setText("");
    }

    @FXML
    void quebrar(ActionEvent event) {
        txt_resultado.setText(b.quebrar());
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
