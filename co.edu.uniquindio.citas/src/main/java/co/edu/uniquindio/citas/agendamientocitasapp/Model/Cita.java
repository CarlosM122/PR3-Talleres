package co.edu.uniquindio.citas.agendamientocitasapp.Model;


import java.time.LocalDate;


public class Cita {
    private LocalDate fecha;
    private Empleado empleadoAsociado;
    private Cliente clienteAsociado;

    public Cita() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleadoAsociado() {
        return empleadoAsociado;
    }

    public void setEmpleadoAsociado(Empleado empleadoAsociado) {
        this.empleadoAsociado = empleadoAsociado;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }
}
