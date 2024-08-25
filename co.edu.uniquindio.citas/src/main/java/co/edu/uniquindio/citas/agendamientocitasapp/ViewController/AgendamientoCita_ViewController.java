package co.edu.uniquindio.citas.agendamientocitasapp.ViewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.citas.agendamientocitasapp.Controller.AgendamientoCita_Controller;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.ClienteDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AgendamientoCita_ViewController {
    AgendamientoCita_Controller agendamientoCita_Controller;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DateInfo;

    @FXML
    private Button agendarCitabtn;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onAgendarCita(ActionEvent event) {
        agendarCita();
    }

    @FXML
    void onCancelar(ActionEvent event) {
        cancelarAgendamiento();
    }

    @FXML
    void initialize() {
        agendamientoCita_Controller = new AgendamientoCita_Controller();
    }

    private void agendarCita() {
        if(verificarDatos()){
            if(agendamientoCita_Controller.disponibilidadFecha(DateInfo)){
                    ClienteDto clienteDto = crearClienteDto();
                    agendamientoCita_Controller.crearCita(clienteDto,DateInfo);
                    mostrarMensaje("Cita","Cita Agendada","Su cita fue agendada correctamente para el dia: " + DateInfo.getValue(), Alert.AlertType.INFORMATION);
            }else {
                mostrarMensaje("Advertencia","Fecha Ocupada","La fecha solicitada no se encuentra disponible.", Alert.AlertType.WARNING);
            }
        }
    }

    private void cancelarAgendamiento() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        DateInfo.setValue(null);
    }

    private ClienteDto crearClienteDto() {
        return new ClienteDto(
                txtNombre.getText(),
                txtCedula.getText(),
                txtTelefono.getText()
        );
    }

    private boolean verificarDatos() {
        boolean resultado = true;
        LocalDate fecha = DateInfo.getValue();
        if (txtCedula.getText().equals("") || txtNombre.getText().equals("")||txtTelefono.getText().equals("")) {
            mostrarMensaje("Error","Campos Nulos","Hay campos necesario sin rellenar.", Alert.AlertType.ERROR);
            resultado = false;
        } else if (fecha.isBefore(LocalDate.now())) {
            mostrarMensaje("Error","Fecha Nula o Incorrecta","La fecha ingresada es nula o anterior a la fecha actual.", Alert.AlertType.ERROR);
            resultado = false;
        }
        return resultado;
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

}