package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ColecaoController {

    @FXML
    private Button btn_adicionar;

    @FXML
    private Button btn_guardar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_retirar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_quantidade;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_tipo;

    @FXML
    private TextField txt_valor;

    @FXML
    private Button btn_criar;

    private Colecao c;

    @FXML
    void criar(ActionEvent event) {
        String tipo = txt_tipo.getText();
        int quantidade = Integer.parseInt(txt_quantidade.getText());
        String valor = txt_valor.getText();

        c = new Colecao (tipo, quantidade, valor);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Coleção criada com sucesso!");

        alerta.showAndWait();
    }
    @FXML
    void adicionar(ActionEvent event) {

        txt_resultado.setText((c.adicionar()));

    }

    @FXML
    void guardar(ActionEvent event) {

        txt_resultado.setText((c.guardar()));

    }

    @FXML
    void limpar(ActionEvent event) {
        txt_valor.clear();
        txt_tipo.clear();
        txt_quantidade.clear();
        txt_resultado.setText("");
    }

    @FXML
    void retirar(ActionEvent event) {

        txt_resultado.setText((c.retirar()));

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
