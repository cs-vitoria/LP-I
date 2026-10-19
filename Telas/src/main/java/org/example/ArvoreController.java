package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ArvoreController {

    @FXML
    private Button btn_criar;

    @FXML
    private Button bt_cair;

    @FXML
    private Button bt_crescer;

    @FXML
    private Button bt_florescer;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_especie;

    @FXML
    private TextField txt_folhagem;

    @FXML
    private TextField txt_idade;

    @FXML
    private Label txt_resultado;

    private Arvore a;

    @FXML
    void criar(ActionEvent event) {
        String folhagem = txt_folhagem.getText();
        String especie = txt_especie.getText();
        int idade = Integer.parseInt(txt_idade.getText());

        a = new Arvore(folhagem, especie, idade);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Árvore criada com sucesso!");

        alerta.showAndWait();
    }

    @FXML
    void cair(ActionEvent event) {
        txt_resultado.setText(a.cair());
    }

    @FXML
    void crescer(ActionEvent event) {
        txt_resultado.setText(a.crescer());
    }

    @FXML
    void florescer(ActionEvent event) {
        txt_resultado.setText(a.florescer());
    }

    @FXML
    void limpar(ActionEvent event) {
    txt_folhagem.clear();
    txt_especie.clear();
    txt_idade.clear();
    txt_resultado.setText("");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
