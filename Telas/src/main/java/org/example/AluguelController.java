package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AluguelController {

    @FXML
    private Button bt_calc_aluguel;

    @FXML
    private TextField txt_dias;

    @FXML
    private TextField txt_distancia;

    @FXML
    private Label txt_aluguel;

    @FXML
    void calcular_aluguel(ActionEvent event) {
        Aluguel valor = new Aluguel();
        txt_aluguel.setText(valor.alugar(Integer.parseInt(txt_dias.getText()), Double.parseDouble(txt_distancia.getText())));
    }


    @FXML
    void qnt_dias(ActionEvent event) {

    }

    @FXML
    void qnt_distancia(ActionEvent event) {

    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }
}
