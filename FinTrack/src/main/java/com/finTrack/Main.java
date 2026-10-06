package com.finTrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bruno
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader
                = new FXMLLoader(
                        getClass().getResource(
                                "/com/finTrack/fintrack.fxml"
                        )
                );

        Scene scene
                = new Scene(loader.load());

        stage.setTitle("FinTrack Simples");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
