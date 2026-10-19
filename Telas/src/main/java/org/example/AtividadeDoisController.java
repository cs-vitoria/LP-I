package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AtividadeDoisController {

    @FXML
    private Button bTCabelo;

    @FXML
    private Button btArvore;

    @FXML
    private Button btBicicleta;

    @FXML
    private Button btCamisa;

    @FXML
    private Button btColecao;

    @FXML
    private Button btDesenho;

    @FXML
    private Button btExposicao;

    @FXML
    private Button btGravata;

    @FXML
    private Button btMascara;

    @FXML
    private Button btPessoa;

    @FXML
    private Button btRosto;

    @FXML
    private Button btTerno;

    @FXML
    private Button btn_voltar;

    @FXML
    void irArvore(ActionEvent event) throws IOException {
        App.setRoot("arvore");
    }

    @FXML
    void irBicicleta(ActionEvent event) throws IOException {
        App.setRoot("bicicleta");
    }

    @FXML
    void irCabelo(ActionEvent event) throws IOException {
        App.setRoot("cabelo");
    }

    @FXML
    void irCamista(ActionEvent event) throws IOException {
        App.setRoot("camisa");
    }
    @FXML
    void irColecao(ActionEvent event) throws IOException {
        App.setRoot("colecao");
    }

    @FXML
    void irDesenho(ActionEvent event) throws IOException {
        App.setRoot("desenho");
    }

    @FXML
    void irExposicao(ActionEvent event) throws IOException {
        App.setRoot("exposicao");
    }

    @FXML
    void irGravata(ActionEvent event) throws IOException {
        App.setRoot("gravata");
    }

    @FXML
    void irMascara(ActionEvent event) throws IOException {
        App.setRoot("mascara");
    }

    @FXML
    void irPessoa(ActionEvent event) throws IOException {
        App.setRoot("pessoa");
    }

    @FXML
    void irRosto(ActionEvent event) throws IOException {
        App.setRoot("rosto");
    }

    @FXML
    void irTerno(ActionEvent event) throws IOException {
        App.setRoot("terno");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("menu_principal");
    }

}
