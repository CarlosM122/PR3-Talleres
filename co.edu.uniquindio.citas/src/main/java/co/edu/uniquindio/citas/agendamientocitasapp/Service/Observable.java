package co.edu.uniquindio.citas.agendamientocitasapp.Service;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
