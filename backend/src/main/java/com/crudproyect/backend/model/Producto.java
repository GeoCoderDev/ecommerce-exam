package com.crudproyect.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Table("t_productos")
public class Producto {

    @Id
    private Long id_producto;

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;
    private LocalDateTime fechaCreacion;
}
