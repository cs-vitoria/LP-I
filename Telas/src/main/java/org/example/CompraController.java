package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CompraController {

    @FXML
    private Button btn_calc;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_acucar;

    @FXML
    private TextField txt_arroz;

    @FXML
    private TextField txt_cafe;

    @FXML
    private TextField txt_feijao;

    @FXML
    private TextField txt_fuba;

    @FXML
    private TextField txt_macarrao;

    @FXML
    private TextField txt_molho;

    @FXML
    private TextField txt_oleo;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_sal;

    @FXML
    private TextField txt_trigo;

    @FXML
    void calcular_media(ActionEvent event) {
        try {
            Compra compra = new Compra();
            String resultado = compra.calcular_compra(
                    Double.parseDouble(txt_arroz.getText()),
                    Double.parseDouble(txt_feijao.getText()),
                    Double.parseDouble(txt_oleo.getText()),
                    Double.parseDouble(txt_acucar.getText()),
                    Double.parseDouble(txt_cafe.getText()),
                    Double.parseDouble(txt_macarrao.getText()),
                    Double.parseDouble(txt_trigo.getText()),
                    Double.parseDouble(txt_fuba.getText()),
                    Double.parseDouble(txt_molho.getText()),
                    Double.parseDouble(txt_sal.getText())
            );
            txt_resultado.setText(resultado);
        } catch (NumberFormatException e) {
            txt_resultado.setText("Preencha todos os campos com números válidos!");
        }
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_arroz.clear();
        txt_feijao.clear();
        txt_oleo.clear();
        txt_acucar.clear();
        txt_cafe.clear();
        txt_macarrao.clear();
        txt_trigo.clear();
        txt_fuba.clear();
        txt_molho.clear();
        txt_sal.clear();
        txt_resultado.setText("");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
            App.setRoot("atividade_um");
        }
}