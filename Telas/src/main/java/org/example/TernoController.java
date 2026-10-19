package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class TernoController {

    @FXML
    private Button btn_criar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_passar;

    @FXML
    private Button btn_rasgar;

    @FXML
    private Button btn_sujar;

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

    private Terno t;

    @FXML
    void criar(ActionEvent event) {
        String cor = txt_cor.getText();
        String tamanho = txt_tamanho.getText();
        String tecido = txt_tecido.getText();

        t = new Terno(cor, tamanho, tecido);

        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null); // Remove o cabeçalho para o alerta ficar mais limpo
        alerta.setContentText("Terno criado com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_tamanho.clear();
        txt_tecido.clear();
        txt_resultado.setText("");
    }

    @FXML
    void passar(ActionEvent event) {
    txt_resultado.setText((t.passar()));
    }

    @FXML
    void rasgar(ActionEvent event) {
        txt_resultado.setText((t.rasgar()));
    }

    @FXML
    void sujar(ActionEvent event) {
        txt_resultado.setText((t.sujar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
