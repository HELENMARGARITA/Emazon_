package com.emazon.stock_microservice.web;

import com.emazon.stock_microservice.application.CategoriaService;
import com.emazon.stock_microservice.application.exceptions.CategoriaNotFoundException;
import com.emazon.stock_microservice.domain.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/categorias")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Listar todas las categorías con paginación
    @GetMapping("/paginadas")
    public ResponseEntity<Page<Categoria>> listarCategoriasPaginadas(Pageable pageable) {
        Page<Categoria> categorias = categoriaService.listarCategoriasPaginadas(pageable);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Obtener una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (CategoriaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar categorías por nombre de manera paginada
    @GetMapping("/buscar")
    public ResponseEntity<Page<Categoria>> buscarCategoriasPorNombre(@RequestParam String nombre, Pageable pageable) {
        Page<Categoria> categorias = categoriaService.buscarCategoriasPorNombre(nombre, pageable);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoriaActualizada) {
        try {
            Categoria categoria = categoriaService.actualizarCategoria(id, categoriaActualizada);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (CategoriaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una categoría por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        try {
            categoriaService.eliminarCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CategoriaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

