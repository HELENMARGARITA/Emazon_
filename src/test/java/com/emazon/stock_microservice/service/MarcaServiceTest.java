package com.emazon.stock_microservice.service;

import com.emazon.stock_microservice.domain.model.Marca;
import com.emazon.stock_microservice.infrastructure.repository.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaService marcaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearMarca() {
        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("MarcaTest");

        when(marcaRepository.save(marca)).thenReturn(marca);

        Marca result = marcaService.crearMarca(marca);

        verify(marcaRepository).save(marca);
        assertEquals("MarcaTest", result.getNombre());
    }

    @Test
    void testObtenerMarcaPorId() {
        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("MarcaTest");

        when(marcaRepository.findById(1L)).thenReturn(Optional.of(marca));

        Marca result = marcaService.obtenerMarcaPorId(1L);

        verify(marcaRepository).findById(1L);
        assertEquals("MarcaTest", result.getNombre());
    }

    // Agrega más pruebas según sea necesario
}

