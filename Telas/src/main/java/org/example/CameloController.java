package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CameloController {

    @FXML
    private Button btcalc_camelo;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_camelo;

    @FXML
    private Label txt_resultado;
    @FXML
    private Button btn_limpar;

    @FXML
    void limpar(ActionEvent event) {
txt_camelo.clear();
txt_resultado.setText("");
    }
    @FXML
    void calcular_camelo(ActionEvent event) {
        Camelo camelo = new Camelo();
        txt_resultado.setText(camelo.calcular_camelos(Integer.parseInt(txt_camelo.getText())));
    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }
}

