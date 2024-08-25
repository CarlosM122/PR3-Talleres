package co.edu.uniquindio.citas.agendamientocitasapp.Utils;

import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cita;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cliente;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Empleado;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Salon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalonUtilts {

    public static Salon inicializarDatos() {
        Salon salon = new Salon();
        salon.setNombre("Perfect Nails");

        Empleado empleado = new Empleado();
        empleado.setNombre("Sara");
        empleado.setApellido("Hernandez");
        empleado.setIdEmpleado("FR56");
        salon.getEmpleadoList().add(empleado);

        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Camila");
        empleado1.setApellido("Barco");
        empleado1.setIdEmpleado("FR57");
        salon.getEmpleadoList().add(empleado1);

        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Valentina");
        empleado2.setApellido("Velez");
        empleado2.setIdEmpleado("FR58");
        salon.getEmpleadoList().add(empleado2);

        Cliente cliente = new Cliente();
        cliente.setNombre("Camila");
        cliente.setCedula("7769812");
        cliente.setTelefono("30087187");
        salon.getClienteList().add(cliente);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Cita cita = new Cita();
        cita.setFecha(LocalDate.parse("23/09/2024",formatter));
        cita.setClienteAsociado(cliente);
        cita.setEmpleadoAsociado(empleado);
        salon.getCitaList().add(cita);

        return salon;
    }
}
