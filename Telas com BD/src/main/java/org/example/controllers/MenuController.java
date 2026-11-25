package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.App;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button bTCabelo;

    @FXML
    private Button btArvore;

    @FXML
    private Button btBicicleta;

    @FXML
    private Button btGravata;

    @FXML
    private Button btPessoa;

    @FXML
    private Button btRosto;

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
    void irGravata(ActionEvent event) throws IOException {
        App.setRoot("gravata");
    }

    @FXML
    void irPessoa(ActionEvent event) throws IOException {
        App.setRoot("pessoa");
    }

    @FXML
    void irRosto(ActionEvent event) throws IOException {
        App.setRoot("rosto");
    }

}
