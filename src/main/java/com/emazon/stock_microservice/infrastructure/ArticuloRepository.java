package com.emazon.stock_microservice.infrastructure;

import com.emazon.stock_microservice.domain.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    @Query("SELECT a FROM Articulo a WHERE a.nombre = :nombre")
    Optional<Articulo> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Articulo a WHERE a.precio = :precio")
    Page<Articulo> findByPrecio(@Param("precio") BigDecimal precio, Pageable pageable);

    @Query("SELECT a FROM Articulo a WHERE a.nombre LIKE %:nombre% AND :categoriaId MEMBER OF a.categorias")
    Page<Articulo> findByNombreAndCategoria(@Param("nombre") String nombre, @Param("categoriaId") Long categoriaId, Pageable pageable);
}


