module co.edu.uniquindio.citas.agendamientocitasapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;


    opens co.edu.uniquindio.citas.agendamientocitasapp to javafx.fxml;
    exports co.edu.uniquindio.citas.agendamientocitasapp;
    exports co.edu.uniquindio.citas.agendamientocitasapp.Model;
    exports co.edu.uniquindio.citas.agendamientocitasapp.ViewController;
    opens co.edu.uniquindio.citas.agendamientocitasapp.Model;
    opens co.edu.uniquindio.citas.agendamientocitasapp.ViewController to javafx.fxml;
    exports co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Mapper;
    exports co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto;

}