package com.crudproyect.backend.service;

import com.crudproyect.backend.dto.PedidoDTO;
import com.crudproyect.backend.model.Pedido;

import com.crudproyect.backend.repository.PedidoRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Flux<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll()
                .map(this::convertToDTO);
    }

    public Mono<PedidoDTO> getPedidoById(String id) {
        return pedidoRepository.findById(id)
                .map(this::convertToDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")));
    }

    public Mono<PedidoDTO> createPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = convertToEntity(pedidoDTO);
        return pedidoRepository.save(pedido)
                .map(this::convertToDTO);
    }

    public Mono<PedidoDTO> updatePedido(String id, PedidoDTO pedidoDTO) {
        return pedidoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(existingPedido -> {
                    Pedido updatedPedido = convertToEntity(pedidoDTO);
                    updatedPedido.set_id(id);
                    return pedidoRepository.save(updatedPedido);
                })
                .map(this::convertToDTO);
    }

    public Mono<Void> deletePedido(String id) {
        return pedidoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(pedidoRepository::delete);
    }

private PedidoDTO convertToDTO(Pedido pedido) {
    if (pedido == null) {
        return null;
    }

    PedidoDTO.PedidoDTOBuilder builder = PedidoDTO.builder()
            .idPedido(pedido.get_id())
            .total(pedido.getTotal())
            .fechaCreacion(pedido.getFechaCreacion())
            .fechaEntrega(pedido.getFechaEntrega());

    if (pedido.getCliente() != null) {
        builder.cliente(PedidoDTO.ClienteDTO.builder()
                .idCliente(pedido.getCliente().getIdCliente())
                .nombre(pedido.getCliente().getNombre())
                .apellido(pedido.getCliente().getApellido())
                .correoElectronico(pedido.getCliente().getCorreoElectronico())
                .numeroTelefono(pedido.getCliente().getNumeroTelefono())
                .direccion(pedido.getCliente().getDireccion())
                .build());
    }

    if (pedido.getProductos() != null) {
        builder.productos(pedido.getProductos().stream()
                .filter(producto -> producto != null)
                .map(producto -> PedidoDTO.ProductoDTO.builder()
                        .idProducto(producto.getIdProducto())
                        .nombre(producto.getNombre())
                        .descripcion(producto.getDescripcion())
                        .precio(producto.getPrecio())
                        .cantidad(producto.getCantidad())
                        .build())
                .toList());
    }

    return builder.build();
}

    private Pedido convertToEntity(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) {
                return null;
        }

        Pedido.PedidoBuilder builder = Pedido.builder()
                ._id(pedidoDTO.getIdPedido())
                .total(pedidoDTO.getTotal())
                .fechaCreacion(pedidoDTO.getFechaCreacion())
                .fechaEntrega(pedidoDTO.getFechaEntrega());

        if (pedidoDTO.getCliente() != null) {
                builder.cliente(Pedido.Cliente.builder()
                        .idCliente(pedidoDTO.getCliente().getIdCliente())
                        .nombre(pedidoDTO.getCliente().getNombre())
                        .apellido(pedidoDTO.getCliente().getApellido())
                        .correoElectronico(pedidoDTO.getCliente().getCorreoElectronico())
                        .numeroTelefono(pedidoDTO.getCliente().getNumeroTelefono())
                        .direccion(pedidoDTO.getCliente().getDireccion())
                        .build());
        }

        if (pedidoDTO.getProductos() != null) {
                builder.productos(pedidoDTO.getProductos().stream()
                        .filter(productoDTO -> productoDTO != null)
                        .map(productoDTO -> Pedido.Producto.builder()
                                .idProducto(productoDTO.getIdProducto())
                                .nombre(productoDTO.getNombre())
                                .descripcion(productoDTO.getDescripcion())
                                .precio(productoDTO.getPrecio())
                                .cantidad(productoDTO.getCantidad())
                                .build())
                        .toList());
        }

        return builder.build();
    }
}
