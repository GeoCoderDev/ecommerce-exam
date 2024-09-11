package com.crudproyect.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;
    private LocalDateTime fechaCreacion;
}
