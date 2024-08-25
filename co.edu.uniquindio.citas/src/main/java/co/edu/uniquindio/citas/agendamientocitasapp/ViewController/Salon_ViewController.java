package co.edu.uniquindio.citas.agendamientocitasapp.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.citas.agendamientocitasapp.CitasApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Salon_ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agendamientoBtn;

    @FXML
    private Button listasBtn;

    @FXML
    void onAgendamiento(ActionEvent event) {
        openAgendamientoView();
    }

    @FXML
    void onListas(ActionEvent event) {
        openListadoCitas();
    }


    @FXML
    void initialize() {

    }

    private void openAgendamientoView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CitasApplication.class.getResource("AgendamientoCita_View.fxml"));
            Parent root = fxmlLoader.load();
            Stage nuevaVentana = new Stage();
            Scene scene = new Scene(root);
            nuevaVentana.setTitle("Perfect Nails");
            nuevaVentana.setScene(scene);
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openListadoCitas() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CitasApplication.class.getResource("ListadoCitas_View.fxml"));
            Parent root = fxmlLoader.load();
            Stage nuevaVentana = new Stage();
            Scene scene = new Scene(root);
            nuevaVentana.setTitle("Perfect Nails");
            nuevaVentana.setScene(scene);
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}