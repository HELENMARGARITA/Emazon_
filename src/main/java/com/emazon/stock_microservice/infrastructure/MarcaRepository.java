package com.emazon.stock_microservice.infrastructure;

import com.emazon.stock_microservice.domain.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    // Buscar una marca por nombre
    Optional<Marca> findByNombre(String nombre);

    // Listar marcas ordenadas por nombre
    Page<Marca> findAllByOrderByNombreAsc(Pageable pageable);

    Page<Marca> findAllByOrderByNombreDesc(Pageable pageable);
}


