package co.edu.uniquindio.citas.agendamientocitasapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CitasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CitasApplication.class.getResource("Salon_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Perfect Nails");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}