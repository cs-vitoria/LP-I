package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TemperaturaController {

    @FXML
    private Button btn_calc;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_abr;

    @FXML
    private TextField txt_ago;

    @FXML
    private TextField txt_dez;

    @FXML
    private TextField txt_fev;

    @FXML
    private TextField txt_jan;

    @FXML
    private TextField txt_jul;

    @FXML
    private TextField txt_jun;

    @FXML
    private TextField txt_mai;

    @FXML
    private TextField txt_mar;

    @FXML
    private TextField txt_nov;

    @FXML
    private TextField txt_out;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_set;

    @FXML
    void calcular_media(ActionEvent event) {
        Temperatura t = new Temperatura();
        txt_resultado.setText(t.media_temp(Double.parseDouble(txt_jan.getText()), Double.parseDouble(txt_fev.getText()),
                Double.parseDouble(txt_mar.getText()), Double.parseDouble(txt_abr.getText()), Double.parseDouble(txt_mai.getText()),
                Double.parseDouble(txt_jun.getText()), Double.parseDouble(txt_jul.getText()), Double.parseDouble(txt_ago.getText()),
                Double.parseDouble(txt_set.getText()), Double.parseDouble(txt_out.getText()), Double.parseDouble(txt_nov.getText()),
                Double.parseDouble(txt_dez.getText())));}

    @FXML
    void limpar(ActionEvent event) {
        txt_jan.clear();
        txt_fev.clear();
        txt_mar.clear();
        txt_abr.clear();
        txt_mai.clear();
        txt_jun.clear();
        txt_jul.clear();
        txt_ago.clear();
        txt_set.clear();
        txt_out.clear();
        txt_nov.clear();
        txt_dez.clear();
        txt_resultado.setText("");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }

}