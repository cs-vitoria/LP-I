package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class CamisaController {

    @FXML
    private Button btn_costurar;

    @FXML
    private Button btn_lavar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_secar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_tamanho;

    @FXML
    private TextField txt_tecido;

    @FXML
    private Button btn_criar;

    private Camisa c;

    @FXML
    void criar(ActionEvent event) {
        String cor = txt_cor.getText();
        String tamanho = txt_tamanho.getText();
        String tecido = txt_tecido.getText();

        c = new Camisa (cor, tamanho, tecido);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Camisa criada com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void costurar(ActionEvent event) {
        txt_resultado.setText((c.costurar()));
    }

    @FXML
    void lavar(ActionEvent event) {
        txt_resultado.setText((c.lavar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_tamanho.clear();
        txt_tecido.clear();
        txt_resultado.setText("");
    }

    @FXML
    void secar(ActionEvent event) {
        txt_resultado.setText((c.secar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
