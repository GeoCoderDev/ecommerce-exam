package com.crudproyect.backend.repository;

import com.crudproyect.backend.model.Pedido;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends ReactiveMongoRepository<Pedido, String> {
}
