package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    // // Load the image as a resource
    // InputStream inputStream = getClass().getResourceAsStream("/pic/kkbs.jpg");
    // Image image = new Image(inputStream);
    
    // ImageView imageView = new ImageView(image);
    // Group root = new Group();
    // root.getChildren().add(imageView);
    
    // Scene scene = new Scene(root, 400, 300);
    scene = new Scene(loadFXML("Monopoly DekmorKhor"),1706,1000);
    stage.setX(10);
    stage.setY(10);
    //scene.setRoot(loadFXML("ImageTest"));
    stage.setScene(scene);
    stage.setTitle("This is a test app");
    stage.show();
}


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}