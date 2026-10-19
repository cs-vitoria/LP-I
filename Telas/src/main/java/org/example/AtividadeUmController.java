package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AtividadeUmController {

    @FXML
    private Button btCamelo;

    @FXML
    private Button btCarro;

    @FXML
    private Button btCompra;

    @FXML
    private Button btLogin;

    @FXML
    private Button btMultiplo;

    @FXML
    private Button btTemp;

    @FXML
    private Button btViagem;

    @FXML
    private Button bt_voltar;

    @FXML
    void irCamelo(ActionEvent event) throws IOException {
        App.setRoot("camelo");
    }

    @FXML
    void irCarro(ActionEvent event) throws IOException {
        App.setRoot("aluguel");
    }

    @FXML
    void irCompra(ActionEvent event) throws IOException {
        App.setRoot("compra");
    }

    @FXML
    void irLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    void irMultiplo(ActionEvent event) throws IOException {
        App.setRoot("multiplo");
    }

    @FXML
    void irTemp(ActionEvent event) throws IOException {
        App.setRoot("temperatura");
    }

    @FXML
    void irViagem(ActionEvent event) throws IOException {
        App.setRoot("viagem");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("menu_principal");
    }

}
