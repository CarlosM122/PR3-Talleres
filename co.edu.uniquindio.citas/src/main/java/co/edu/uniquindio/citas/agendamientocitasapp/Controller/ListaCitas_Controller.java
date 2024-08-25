package co.edu.uniquindio.citas.agendamientocitasapp.Controller;

import co.edu.uniquindio.citas.agendamientocitasapp.Factory.ModelFactory;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.CitaDto;
import co.edu.uniquindio.citas.agendamientocitasapp.ViewController.ListadoCitas_ViewController;
import javafx.scene.control.TextField;

import java.util.List;

public class ListaCitas_Controller {
    ModelFactory modelFactory;

    public ListaCitas_Controller(){
        modelFactory=ModelFactory.getInstance();
    }

    public List<CitaDto> obtenerCitasDto() {
        return modelFactory.obtenerCitasDto();
    }

    public void agregarObservador(ListadoCitas_ViewController listadoCitasViewController) {
        modelFactory.agregarObservador(listadoCitasViewController);
    }

    public void eliminarCita(String fecha) {
        modelFactory.eliminarCita(fecha);
    }
}
