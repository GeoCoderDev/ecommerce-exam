package com.crudproyect.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private LocalDateTime fechaCreacion;
}
