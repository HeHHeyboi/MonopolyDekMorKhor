package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class StartController {
    @FXML
    private void twoPlayerButton() throws IOException {
        App.setRoot("MonopolyDekmorKhor2");
    }
    @FXML
    private void threePlayerButton() throws IOException {
        App.setRoot("MonopolyDekmorkhor3");
    }
    @FXML
    private void fourPlayerButton() throws IOException {
        App.setRoot("MonopolyDekmorkhor4");
    }
}
