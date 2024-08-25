package co.edu.uniquindio.citas.agendamientocitasapp.ViewController;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.citas.agendamientocitasapp.Controller.ListaCitas_Controller;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.CitaDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.ClienteDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.EmpleadoDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Service.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ListadoCitas_ViewController implements Observer {

    ListaCitas_Controller listaCitasController;
    ObservableList<CitaDto> listaCitas = FXCollections.observableArrayList();
    CitaDto citaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CitaDto> TableViewCitas;

    @FXML
    private Button actualizarBtn;

    @FXML
    private Button eliminarBtn;

    @FXML
    private TableColumn<CitaDto, String> tcCedulaCliente;

    @FXML
    private TableColumn<CitaDto, String> tcFecha;

    @FXML
    private TableColumn<CitaDto, String> tcNombreEmpleado;

    @FXML
    private TableColumn<CitaDto, String> tcIDEmpleado;

    @FXML
    private TableColumn<CitaDto, String> tcNombreCliente;

    @FXML
    private TableColumn<CitaDto, String> tcTelefono;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtIDEmpleado;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onActualizar(ActionEvent event) {
        actualizarCita(citaSeleccionada);
    }

    @FXML
    void onEliminar(ActionEvent event) {
        if (citaSeleccionada == null) {
            mostrarMensaje("Advertencia", null, "Seleccione una cita para eliminar", Alert.AlertType.WARNING);
        } else {
            eliminarCita(citaSeleccionada.fecha());
        }
    }

    @FXML
    void initialize() {
        listaCitasController = new ListaCitas_Controller();
        TableViewCitas.setItems(listaCitas);
        listaCitasController.agregarObservador(this);
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerCitasDto();
        listenerSelection();
    }

    private void obtenerCitasDto() {
        listaCitas.addAll(listaCitasController.obtenerCitasDto());
    }

    private void actualizarCita(CitaDto citaSeleccionada) {
        if (citaSeleccionada != null) {
            if (verificarDatos()) {
                crearClienteDto();
                creaEmpleadoDto();
                CitaDto citaActualizada = crearCitaDto(crearClienteDto(), creaEmpleadoDto());
                int index = listaCitas.indexOf(citaSeleccionada);
                if (index >= 0) {
                    listaCitas.set(index, citaActualizada);
                    vaciarCampos();
                    TableViewCitas.refresh();
                }
            } else {
                mostrarMensaje("Error", "Errror De Actualización", "Hay campos necesarios vacios.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Errror De Actualización", "No se selecciono una cita para actualizar.", Alert.AlertType.ERROR);
        }
    }

    private void eliminarCita(String fecha) {
        listaCitasController.eliminarCita(fecha);
        vaciarCampos();
    }

    private void vaciarCampos() {
        txtCedula.setText("");
        txtIDEmpleado.setText("");
        txtNombreCliente.setText("");
        txtNombreEmpleado.setText("");
        txtTelefono.setText("");
    }

    private boolean verificarDatos() {
        if (txtCedula.getText().isEmpty() || txtIDEmpleado.getText().isEmpty() || txtNombreCliente.getText().isEmpty() || txtNombreEmpleado.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private ClienteDto crearClienteDto() {
        return new ClienteDto(
                txtNombreCliente.getText(),
                txtCedula.getText(),
                txtTelefono.getText()
        );
    }

    private EmpleadoDto creaEmpleadoDto() {
        return new EmpleadoDto(
                txtNombreEmpleado.getText(),
                "",
                txtIDEmpleado.getText()
        );
    }

    private CitaDto crearCitaDto(ClienteDto clienteDto, EmpleadoDto empleadoDto) {
        try {
            String nombreCliente = clienteDto.nombre();
            String cedulaCliente = clienteDto.cedula();
            String telefonoCliente = clienteDto.telefono();
            String nombreEmpleado = empleadoDto.nombre();
            String idEmpleado = empleadoDto.idEmpleado();

            return new CitaDto(
                    citaSeleccionada.fecha(),
                    nombreCliente,
                    cedulaCliente,
                    telefonoCliente,
                    nombreEmpleado,
                    idEmpleado,
                    ""
            );
        } catch (NullPointerException e) {
            mostrarMensaje("Error", "Datos Invalidos", "Se ingresaron datos invalidos", Alert.AlertType.ERROR);
            return null;
        }
    }

    private void initDataBinding() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tcNombreCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCliente()));
        tcCedulaCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaCliente()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefonoCliente()));
        tcNombreEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreEmpleado()));
        tcIDEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idEmpleado()));
        tcFecha.setCellValueFactory(cellData -> {
            String formattedDate = LocalDate.parse(cellData.getValue().fecha()).format(formatter);
            return new SimpleStringProperty(formattedDate);
        });
    }

    private void listenerSelection() {
        TableViewCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            citaSeleccionada = newSelection;
            mostrarInformacionCita(citaSeleccionada);
        });
    }

    private void mostrarInformacionCita(CitaDto citaSeleccionada) {
        if (citaSeleccionada != null) {
            txtTelefono.setText(citaSeleccionada.telefonoCliente());
            txtCedula.setText(citaSeleccionada.cedulaCliente());
            txtIDEmpleado.setText(citaSeleccionada.idEmpleado());
            txtNombreCliente.setText(citaSeleccionada.nombreCliente());
            txtNombreEmpleado.setText(citaSeleccionada.nombreEmpleado());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    @Override
    public void update() {
        listaCitas.setAll(listaCitasController.obtenerCitasDto());
        TableViewCitas.refresh();
    }
}