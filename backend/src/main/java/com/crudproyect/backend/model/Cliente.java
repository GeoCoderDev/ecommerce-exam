package com.crudproyect.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Table("t_clientes")
public class Cliente {

    @Id
    private Long id_cliente;

    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private LocalDateTime fechaCreacion;
}
