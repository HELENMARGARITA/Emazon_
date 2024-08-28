package com.emazon.stock_microservice.web;

import com.emazon.stock_microservice.application.ArticuloService;
import com.emazon.stock_microservice.domain.model.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    // Crear un nuevo artículo
    @PostMapping
    public Articulo crearArticulo(@RequestBody Articulo articulo) {
        return articuloService.crearArticulo(articulo);
    }

    // Listar todos los artículos
    @GetMapping
    public Page<Articulo> listarArticulos(Pageable pageable) {
        return articuloService.listarArticulos(pageable);
    }

    // Obtener un artículo por su ID
    @GetMapping("/{id}")
    public Articulo obtenerArticuloPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id);
    }

    // Buscar artículos por nombre de manera paginada
    @GetMapping("/buscar")
    public Page<Articulo> buscarArticulosPorNombre(@RequestParam String nombre, Pageable pageable) {
        return articuloService.buscarArticulosPorNombre(nombre, pageable);
    }

    // Buscar artículos por precio de manera paginada
    @GetMapping("/buscar-precio")
    public Page<Articulo> buscarArticulosPorPrecio(@RequestParam BigDecimal precio, Pageable pageable) {
        return articuloService.buscarArticulosPorPrecio(precio, pageable);
    }

    // Buscar artículos por nombre y categoría de manera paginada
    @GetMapping("/buscar-categoria")
    public Page<Articulo> buscarArticulosPorNombreYCategoria(
            @RequestParam String nombre,
            @RequestParam Long categoriaId,
            Pageable pageable) {
        return articuloService.buscarArticulosPorNombreYCategoria(nombre, categoriaId, pageable);
    }

    // Eliminar un artículo por su ID
    @DeleteMapping("/{id}")
    public void eliminarArticulo(@PathVariable Long id) {
        articuloService.eliminarArticulo(id);
    }
}


@PostMapping
    public Articulo crearArticulo(@RequestBody Articulo articulo) {
        return articuloService.crearArticulo(articulo);
    }

    @GetMapping
    public Page<Articulo> listarArticulos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "nombre") String sortBy) {

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);
        return articuloService.listarArticulos(pageRequest);
    }

    @GetMapping("/{id}")
    public Articulo obtenerArticuloPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarArticulo(@PathVariable Long id) {
        articuloService.eliminarArticulo(id);
    }
}
