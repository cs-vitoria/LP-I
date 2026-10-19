package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private Button btAtividade1;

    @FXML
    private Button btAtividade2;

    @FXML
    void entrarDOIS(ActionEvent event) throws IOException {
        App.setRoot("atividade_dois");
    }

    @FXML
    void entrarUM(ActionEvent event) throws IOException {
        App.setRoot("atividade_um");
    }

}
