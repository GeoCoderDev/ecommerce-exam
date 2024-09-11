package com.crudproyect.backend.service;

import com.crudproyect.backend.dto.ProductoDTO;
import com.crudproyect.backend.exception.ResourceNotFoundException;
import com.crudproyect.backend.model.Producto;
import com.crudproyect.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos (Flux)
    public Flux<ProductoDTO> listarProductos() {
        return productoRepository.findAll()
                .map(this::convertToDTO);
    }

    // Obtener producto por ID (Mono)
    public Mono<ProductoDTO> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::convertToDTO)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")));
    }

    // Crear nuevo producto (Mono)
    public Mono<ProductoDTO> crearProducto(ProductoDTO productoDTO) {
        Producto producto = convertToEntity(productoDTO);
        return productoRepository.save(producto)
                .map(this::convertToDTO);
    }

    // Actualizar producto por ID (Mono)
    public Mono<ProductoDTO> actualizarProducto(Long id, ProductoDTO productoDTO) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")))
                .flatMap(existingProducto -> {
                    Producto updatedProducto = convertToEntity(productoDTO);
                    updatedProducto.setId_producto(id);
                    return productoRepository.save(updatedProducto);
                })
                .map(this::convertToDTO);
    }

    // Eliminar producto por ID (Mono<Void>)
    public Mono<Void> eliminarProducto(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado")))
                .flatMap(productoRepository::delete);
    }

    // Convertir de entidad Producto a DTO
    private ProductoDTO convertToDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId_producto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .cantidadStock(producto.getCantidadStock())
                .fechaCreacion(producto.getFechaCreacion())
                .build();
    }

    // Convertir de DTO a entidad Producto
    private Producto convertToEntity(ProductoDTO productoDTO) {
        return Producto.builder()
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .precio(productoDTO.getPrecio())
                .cantidadStock(productoDTO.getCantidadStock())
                .fechaCreacion(productoDTO.getFechaCreacion())
                .build();
    }
}
