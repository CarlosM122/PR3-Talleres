package co.edu.uniquindio.citas.agendamientocitasapp.Factory;

import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.CitaDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.ClienteDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Mapper.SalonMapper;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cita;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cliente;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Empleado;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Salon;
import co.edu.uniquindio.citas.agendamientocitasapp.Utils.SalonUtilts;
import co.edu.uniquindio.citas.agendamientocitasapp.ViewController.ListadoCitas_ViewController;
import javafx.scene.control.DatePicker;
import java.util.Random;

import java.time.LocalDate;
import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    Salon salon;


    public static ModelFactory getInstance(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        salon = SalonUtilts.inicializarDatos();
    }

    public boolean disponibilidadFecha(DatePicker dateInfo) {
        return salon.disponibilidadFecha(dateInfo);
    }

    public void crearCita(ClienteDto clienteDto, DatePicker dateInfo) {
        LocalDate fecha = dateInfo.getValue();
        Cliente cliente = SalonMapper.INSTANCE.clienteDtoToCliente(clienteDto);
        Cita cita = new Cita();
        cita.setFecha(fecha);
        cita.setClienteAsociado(cliente);
        List<Empleado> empleados = salon.getEmpleadoList();
        if(!empleados.isEmpty()){
            Random random = new Random();
            int indice = random.nextInt(empleados.size());
            Empleado empleadoAleatorio = empleados.get(indice);
            cita.setEmpleadoAsociado(empleadoAleatorio);
        }else {
            throw new RuntimeException("No hay empleados disponibles para asignar.");
        }
        salon.getCitaList().add(cita);
        salon.notifyObservers();
    }

    public List<CitaDto> obtenerCitasDto() {
        return SalonMapper.INSTANCE.citaListToCitaDtoList(salon.getCitaList());
    }

    public void agregarObservador(ListadoCitas_ViewController listadoCitasViewController) {
        salon.addObserver(listadoCitasViewController);
    }

    public void eliminarCita(String fecha) {
        salon.eliminarCita(fecha);
    }
}