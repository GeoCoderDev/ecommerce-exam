package com.crudproyect.backend.controller;

import com.crudproyect.backend.dto.PedidoDTO;
import com.crudproyect.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Flux<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PedidoDTO>> getPedidoById(@PathVariable String id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<PedidoDTO>> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.createPedido(pedidoDTO)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PedidoDTO>> updatePedido(@PathVariable String id, @RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.updatePedido(id, pedidoDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deletePedido(@PathVariable String id) {
        return pedidoService.deletePedido(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
