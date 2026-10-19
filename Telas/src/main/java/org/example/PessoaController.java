package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class PessoaController {

    @FXML
    private Button btn_correr;

    @FXML
    private Button btn_gritar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pedalar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_altura;

    @FXML
    private TextField txt_idade;

    @FXML
    private TextField txt_nome;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Pessoa p;

    @FXML
    void criar(ActionEvent event) {
        String nome = txt_nome.getText();
        int idade = Integer.parseInt(txt_idade.getText());
        Double altura = Double.parseDouble(txt_altura.getText());

        p = new Pessoa(nome, idade, altura);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(null);
        alerta.setContentText("Pessoa criada com sucesso!");

        alerta.showAndWait();
    }
    @FXML
    void correr(ActionEvent event) {
        txt_resultado.setText((p.correr()));

    }

    @FXML
    void gritar(ActionEvent event) {
        txt_resultado.setText((p.gritar()));

    }

    @FXML
    void limpar(ActionEvent event) {
        txt_nome.clear();
        txt_idade.clear();
        txt_altura.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pedalar(ActionEvent event) {
        txt_resultado.setText((p.pedalar()));

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

}
