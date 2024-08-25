package co.edu.uniquindio.citas.agendamientocitasapp.Controller;

import co.edu.uniquindio.citas.agendamientocitasapp.Factory.ModelFactory;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.ClienteDto;
import javafx.scene.control.DatePicker;

public class AgendamientoCita_Controller {
    ModelFactory modelFactory;

    public AgendamientoCita_Controller(){
        modelFactory = ModelFactory.getInstance();
    }


    public boolean disponibilidadFecha(DatePicker dateInfo) {
        return modelFactory.disponibilidadFecha(dateInfo);
    }

    public void crearCita(ClienteDto clienteDto, DatePicker dateInfo) {
        modelFactory.crearCita(clienteDto,dateInfo);
    }
}
