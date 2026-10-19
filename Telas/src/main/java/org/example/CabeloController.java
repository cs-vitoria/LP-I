package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class CabeloController {

    @FXML
    private Button btn_arrumar;

    @FXML
    private Button btn_lavar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_prender;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_tamanho;

    @FXML
    private TextField txt_textura;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Cabelo c;

    @FXML
    void criar(ActionEvent event) {
        String cor = txt_cor.getText();
        Double tamanho = Double.parseDouble(txt_textura.getText());
        String textura = txt_tamanho.getText();

        c = new Cabelo(cor, tamanho, textura);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Cabelo criado com sucesso!");

        alerta.showAndWait();
    }
    @FXML
    void arrumar(ActionEvent event) {
        txt_resultado.setText((c.arrumar()));
    }

    @FXML
    void lavar(ActionEvent event) {
        txt_resultado.setText((c.lavar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_cor.clear();
        txt_tamanho.clear();
        txt_textura.clear();
        txt_resultado.setText("");
    }

    @FXML
    void prender(ActionEvent event) {
        txt_resultado.setText((c.prender()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
