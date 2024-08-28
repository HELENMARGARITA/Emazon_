package com.emazon.stock_microservice.service;

import com.emazon.stock_microservice.application.service.ArticuloService;
import com.emazon.stock_microservice.domain.model.Articulo;
import com.emazon.stock_microservice.infrastructure.repository.ArticuloRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ArticuloServiceTest {

    @Autowired
    private ArticuloService articuloService;

    @MockBean
    private ArticuloRepository articuloRepository;

    @Test
    public void testCrearArticulo() {
        Articulo articulo = new Articulo();
        articulo.setNombre("Nuevo Artículo");

        Mockito.when(articuloRepository.save(Mockito.any(Articulo.class))).thenReturn(articulo);

        Articulo resultado = articuloService.crearArticulo(articulo);

        assertEquals("Nuevo Artículo", resultado.getNombre());
    }

    @Test
    public void testObtenerArticuloPorId() {
        Articulo articulo = new Articulo();
        articulo.setId(1L);

        Mockito.when(articuloRepository.findById(1L)).thenReturn(Optional.of(articulo));

        Articulo resultado = articuloService.obtenerArticuloPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testObtenerArticuloPorIdNoEncontrado() {
        Mockito.when(articuloRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            articuloService.obtenerArticuloPorId(1L);
        });
    }
}

