package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class StartMenu{
    @FXML
    private void twoPlayerButton() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void threePlayerButton() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void fourPlayerButton() throws IOException {
        App.setRoot("primary");
    }
}