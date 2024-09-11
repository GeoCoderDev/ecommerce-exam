package com.crudproyect.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoDTO {

    private String idPedido;
    private ClienteDTO cliente;
    private List<ProductoDTO> productos;
    private Double total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;

    @Data
    @Builder
    public static class ClienteDTO {
        private String idCliente;
        private String nombre;
        private String apellido;
        private String correoElectronico;
        private String numeroTelefono;
        private String direccion;
    }

    @Data
    @Builder
    public static class ProductoDTO {
        private String idProducto;
        private String nombre;
        private String descripcion;
        private Double precio;
        private Integer cantidad;
    }
}
