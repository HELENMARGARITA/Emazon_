package com.emazon.stock_microservice.application;

import com.emazon.stock_microservice.domain.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MarcaService {

    Marca crearMarca(Marca marca);

    Optional<Marca> obtenerMarcaPorNombre(String nombre);

    Page<Marca> listarMarcasOrdenadas(String orden, Pageable pageable);

    Marca obtenerMarcaPorId(Long id);

    void eliminarMarca(Long id);
}



