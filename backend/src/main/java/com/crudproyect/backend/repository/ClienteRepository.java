package com.crudproyect.backend.repository;

import com.crudproyect.backend.model.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Long> {
}
