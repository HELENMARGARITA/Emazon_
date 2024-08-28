package com.emazon.stock_microservice.application;

import com.emazon.stock_microservice.domain.model.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface ArticuloService {

    Articulo crearArticulo(Articulo articulo);

    Page<Articulo> listarArticulos(Pageable pageable);

    Articulo obtenerArticuloPorId(Long id);

    void eliminarArticulo(Long id);

    Page<Articulo> buscarArticulosPorNombre(String nombre, Pageable pageable);

    Page<Articulo> buscarArticulosPorPrecio(BigDecimal precio, Pageable pageable);

    Page<Articulo> buscarArticulosPorNombreYCategoria(String nombre, Long categoriaId, Pageable pageable);

    Optional<Articulo> buscarArticuloPorNombre(String nombre);
}
