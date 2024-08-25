package co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Mapper;

import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.CitaDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Mappings.Dto.ClienteDto;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cita;
import co.edu.uniquindio.citas.agendamientocitasapp.Model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalonMapper {
    SalonMapper INSTANCE = Mappers.getMapper(SalonMapper.class);

    @Named("clienteDtoToCliente")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "cedula", source = "cedula")
    @Mapping(target = "telefono", source = "telefono")
    Cliente clienteDtoToCliente(ClienteDto clienteDto);

    @Mapping(target = "fecha", expression = "java(cita.getFecha().toString())")
    @Mapping(target = "nombreCliente", source = "clienteAsociado.nombre")
    @Mapping(target = "cedulaCliente", source = "clienteAsociado.cedula")
    @Mapping(target = "telefonoCliente", source = "clienteAsociado.telefono")
    @Mapping(target = "nombreEmpleado", source = "empleadoAsociado.nombre")
    @Mapping(target = "idEmpleado", source = "empleadoAsociado.idEmpleado")
    @Mapping(target = "apellidoEmpleado", source = "empleadoAsociado.apellido")
    CitaDto citaToCitaDto(Cita cita);

    List<CitaDto> citaListToCitaDtoList(List<Cita> citas);
}
