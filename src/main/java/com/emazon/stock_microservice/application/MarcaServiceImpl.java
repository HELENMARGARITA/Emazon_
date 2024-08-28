package com.emazon.stock_microservice.application;

import com.emazon.stock_microservice.domain.model.Marca;
import com.emazon.stock_microservice.infrastructure.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Marca crearMarca(@Valid Marca marca) {
        Optional<Marca> existingMarca = marcaRepository.findByNombre(marca.getNombre());
        if (existingMarca.isPresent()) {
            throw new IllegalArgumentException("El nombre de la marca ya existe.");
        }
        return marcaRepository.save(marca);
    }

    @Override
    public Optional<Marca> obtenerMarcaPorNombre(String nombre) {
        return marcaRepository.findByNombre(nombre);
    }

    @Override
    public Page<Marca> listarMarcasOrdenadas(String orden, Pageable pageable) {
        if ("asc".equalsIgnoreCase(orden)) {
            return marcaRepository.findAllByOrderByNombreAsc(pageable);
        } else if ("desc".equalsIgnoreCase(orden)) {
            return marcaRepository.findAllByOrderByNombreDesc(pageable);
        } else {
            throw new IllegalArgumentException("Orden no válido. Use 'asc' o 'desc'.");
        }
    }

    @Override
    public Marca obtenerMarcaPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada."));
    }

    @Override
    public void eliminarMarca(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new IllegalArgumentException("Marca no encontrada.");
        }
        marcaRepository.deleteById(id);
    }
}

