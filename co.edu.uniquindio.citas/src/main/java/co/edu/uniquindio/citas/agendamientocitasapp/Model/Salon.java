package co.edu.uniquindio.citas.agendamientocitasapp.Model;


import co.edu.uniquindio.citas.agendamientocitasapp.Service.Observable;
import co.edu.uniquindio.citas.agendamientocitasapp.Service.Observer;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Salon implements Observable {
    private String nombre;
    private List<Cliente> clienteList = new ArrayList<Cliente>();
    private List<Empleado> empleadoList = new ArrayList<Empleado>();
    private List<Cita> citaList = new ArrayList<Cita>();
    private List<Observer> observerList = new ArrayList<>();

    public Salon() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    public boolean disponibilidadFecha(DatePicker dateInfo) {
        boolean disponibilidad = true;
        LocalDate fecha = dateInfo.getValue();
        for (Cita cita : citaList) {
            if (cita.getFecha().equals(fecha)) {
                return false;
            }
        }
        return disponibilidad;
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    public void eliminarCita(String fecha) {
        try {
            LocalDate LocalDatefechas = LocalDate.parse(fecha);
            for (Cita cita : citaList) {
                if (cita.getFecha().equals(LocalDatefechas)) {
                    citaList.remove(cita);
                    notifyObservers();
                    break;
                }
            }
        } catch (DateTimeParseException e) {
        }
    }
}