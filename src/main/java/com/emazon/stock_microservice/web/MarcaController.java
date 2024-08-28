package com.emazon.stock_microservice.web;

import com.emazon.stock_microservice.application.MarcaService;
import com.emazon.stock_microservice.domain.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // Crear una nueva marca
    @PostMapping
    public Marca crearMarca(@RequestBody Marca marca) {
        return marcaService.crearMarca(marca);
    }

    // Obtener una marca por su ID
    @GetMapping("/{id}")
    public Marca obtenerMarcaPorId(@PathVariable Long id) {
        return marcaService.obtenerMarcaPorId(id);
    }

    // Buscar una marca por nombre
    @GetMapping("/buscar")
    public Marca obtenerMarcaPorNombre(@RequestParam String nombre) {
        return marcaService.obtenerMarcaPorNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada."));
    }

    // Listar todas las marcas con paginación y orden
    @GetMapping
    public Page<Marca> listarMarcasOrdenadas(@RequestParam(defaultValue = "asc") String orden, Pageable pageable) {
        return marcaService.listarMarcasOrdenadas(orden, pageable);
    }

    // Actualizar una marca existente
    @PutMapping("/{id}")
    public Marca actualizarMarca(@PathVariable Long id, @RequestBody Marca marcaActualizada) {
        Marca marcaExistente = marcaService.obtenerMarcaPorId(id);
        if (!marcaExistente.getNombre().equals(marcaActualizada.getNombre())) {
            if (marcaService.obtenerMarcaPorNombre(marcaActualizada.getNombre()).isPresent()) {
                throw new IllegalArgumentException("El nombre de la marca ya existe.");
            }
        }
        marcaExistente.setNombre(marcaActualizada.getNombre());
        marcaExistente.setDescripcion(marcaActualizada.getDescripcion());
        return marcaService.crearMarca(marcaExistente);
    }

    // Eliminar una marca por su ID
    @DeleteMapping("/{id}")
    public void eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);
    }
}



