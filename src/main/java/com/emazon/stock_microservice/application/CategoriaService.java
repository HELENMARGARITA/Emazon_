package com.emazon.stock_microservice.application;

import com.emazon.stock_microservice.domain.model.Categoria;
import com.emazon.stock_microservice.infrastructure.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Valid
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva categoría
    public Categoria crearCategoria(@Valid Categoria categoria) {
        Optional<Categoria> existingCategoria = categoriaRepository.findByNombre(categoria.getNombre());
        if (existingCategoria.isPresent()) {
            throw new IllegalArgumentException("El nombre de la categoría ya existe.");
        }
        return categoriaRepository.save(categoria);
    }

    // Listar todas las categorías
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Listar todas las categorías con paginación
    public Page<Categoria> listarCategoriasPaginadas(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    // Obtener una categoría por su ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada."));
    }

    // Buscar categorías por nombre de manera paginada
    public Page<Categoria> buscarCategoriasPorNombre(String nombre, Pageable pageable) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }

    // Actualizar una categoría existente
    public Categoria actualizarCategoria(Long id, @Valid Categoria categoriaActualizada) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada."));

        if (!categoriaExistente.getNombre().equals(categoriaActualizada.getNombre())) {
            Optional<Categoria> categoriaConMismoNombre = categoriaRepository.findByNombre(categoriaActualizada.getNombre());
            if (categoriaConMismoNombre.isPresent()) {
                throw new IllegalArgumentException("El nombre de la categoría ya existe.");
            }
        }

        categoriaExistente.setNombre(categoriaActualizada.getNombre());
        categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());

        return categoriaRepository.save(categoriaExistente);
    }

    // Eliminar una categoría por su ID
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoría no encontrada.");
        }
        categoriaRepository.deleteById(id);
    }
}


