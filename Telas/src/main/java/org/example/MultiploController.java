package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MultiploController {

    @FXML
    private Button bt_calcular;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_num1;

    @FXML
    private TextField txt_num2;

    @FXML
    private Label txt_resultado;

    @FXML
    void calcular_multiplo(ActionEvent event) throws IOException {
Multiplo numero = new Multiplo();
txt_resultado.setText(numero.descobrir_multiplo(Integer.parseInt(txt_num1.getText()),Integer.parseInt(txt_num2.getText())));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }


}
