package com.emazon.stock_microservice.service;


import com.emazon.stock_microservice.application.Categoria;
import com.emazon.stock_microservice.infrastructure.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

     @Test
    void testCrearCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Electrónica");
        categoria.setDescripcion("Productos electrónicos");

        Mockito.when(categoriaRepository.findByNombre("Electrónica")).thenReturn(Optional.empty());
        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria result = categoriaService.crearCategoria(categoria);

        assertNotNull(result);
        assertEquals("Electrónica", result.getNombre());
    }

    // Otras pruebas unitarias para listar y obtener por ID
    @Test
    void testObtenerCategoriaPorIdNotFound() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoriaService.obtenerCategoriaPorId(1L));
    }
}

