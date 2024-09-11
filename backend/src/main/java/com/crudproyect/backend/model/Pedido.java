package com.crudproyect.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String _id;

    private Cliente cliente;
    private List<Producto> productos;
    private Double total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;

    @Data
    @Builder
    public static class Cliente {
        private String idCliente;
        private String nombre;
        private String apellido;
        private String correoElectronico;
        private String numeroTelefono;
        private String direccion;
    }

    @Data
    @Builder
    public static class Producto {
        private String idProducto;
        private String nombre;
        private String descripcion;
        private Double precio;
        private Integer cantidad;
    }
}
