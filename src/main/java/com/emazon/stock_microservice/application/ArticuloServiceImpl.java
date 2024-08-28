package com.emazon.stock_microservice.application;

import com.emazon.stock_microservice.domain.model.Articulo;
import com.emazon.stock_microservice.infrastructure.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public Articulo crearArticulo(Articulo articulo) {
        if (articulo == null) {
            throw new IllegalArgumentException("Articulo no puede ser nulo.");
        }
        return articuloRepository.save(articulo);
    }

    @Override
    public Page<Articulo> listarArticulos(Pageable pageable) {
        try {
            return articuloRepository.findAll(pageable);
        } catch (Exception e) {
            // Aquí puedes registrar el error o lanzar una excepción personalizada
            throw new RuntimeException("Error al listar artículos", e);
        }
    }

    @Override
    public Articulo obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Articulo no encontrado."));
    }

    @Override
    public void eliminarArticulo(Long id) {
        if (!articuloRepository.existsById(id)) {
            throw new IllegalArgumentException("Articulo no encontrado.");
        }
        articuloRepository.deleteById(id);
    }

    @Override
    public Page<Articulo> buscarArticulosPorNombre(String nombre, Pageable pageable) {
        return articuloRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }

    @Override
    public Page<Articulo> buscarArticulosPorPrecio(BigDecimal precio, Pageable pageable) {
        return articuloRepository.findByPrecioGreaterThanEqual(precio, pageable);
    }

    @Override
    public Page<Articulo> buscarArticulosPorNombreYCategoria(String nombre, Long categoriaId, Pageable pageable) {
        return articuloRepository.findByNombreContainingAndCategoriaId(nombre, categoriaId, pageable);
    }

    @Override
    public Optional<Articulo> buscarArticuloPorNombre(String nombre) {
        return articuloRepository.findByNombre(nombre);
    }
}

