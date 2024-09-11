package com.crudproyect.backend.service;

import com.crudproyect.backend.dto.ClienteDTO;
import com.crudproyect.backend.exception.ResourceNotFoundException;
import com.crudproyect.backend.model.Cliente;
import com.crudproyect.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes (Flux)
    public Flux<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .map(this::convertToDTO);
    }

    // Obtener cliente por ID (Mono)
    public Mono<ClienteDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertToDTO)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cliente no encontrado")));
    }

    // Crear nuevo cliente (Mono)
    public Mono<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        return clienteRepository.save(cliente)
                .map(this::convertToDTO);
    }

    // Actualizar cliente por ID (Mono)
    public Mono<ClienteDTO> actualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cliente no encontrado")))
                .flatMap(existingCliente -> {
                    Cliente updatedCliente = convertToEntity(clienteDTO);
                    updatedCliente.setId_cliente(id);
                    return clienteRepository.save(updatedCliente);
                })
                .map(this::convertToDTO);
    }

    // Eliminar cliente por ID (Mono<Void>)
    public Mono<Void> eliminarCliente(Long id) {
        return clienteRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cliente no encontrado")))
                .flatMap(clienteRepository::delete);
    }

    // Convertir de entidad Cliente a DTO
    private ClienteDTO convertToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId_cliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correoElectronico(cliente.getCorreoElectronico())
                .numeroTelefono(cliente.getNumeroTelefono())
                .direccion(cliente.getDireccion())
                .fechaCreacion(cliente.getFechaCreacion())
                .build();
    }

    // Convertir de DTO a entidad Cliente
    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .correoElectronico(clienteDTO.getCorreoElectronico())
                .numeroTelefono(clienteDTO.getNumeroTelefono())
                .direccion(clienteDTO.getDireccion())
                .fechaCreacion(clienteDTO.getFechaCreacion())
                .build();
    }
}
