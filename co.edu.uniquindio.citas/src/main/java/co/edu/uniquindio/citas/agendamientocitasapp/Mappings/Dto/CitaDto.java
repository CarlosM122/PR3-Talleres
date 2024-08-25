package co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto;

public record CitaDto(
        String fecha,
        String nombreCliente,
        String cedulaCliente,
        String telefonoCliente,
        String nombreEmpleado,
        String idEmpleado,
        String apellidoEmpleado
) {
}
