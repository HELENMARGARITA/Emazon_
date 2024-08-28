package com.emazon.stock_microservice.infrastructure;

import com.emazon.stock_microservice.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Busca una categoría por su nombre
    Optional<Categoria> findByNombre(String nombre);

    // Lista todas las categorías de manera paginada
    Page<Categoria> findAll(Pageable pageable);

    // Busca categorías por su nombre de manera paginada
    Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}


