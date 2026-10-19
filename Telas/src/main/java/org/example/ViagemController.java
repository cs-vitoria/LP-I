package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ViagemController {

    @FXML
    private Button bt_calc_viagem;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_voltar;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_distancia;

    @FXML
    private TextField txt_valor;

    @FXML
    void calcular_viagem(ActionEvent event) throws IOException  {

    }

    @FXML
    void limpar(ActionEvent event) throws IOException {
        txt_valor.clear();
        txt_distancia.clear();
        txt_resultado.setText("");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }

}
