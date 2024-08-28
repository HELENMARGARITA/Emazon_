package com.emazon.stock_microservice.ControllerTest;

import com.emazon.stock_microservice.application.service.ArticuloService;
import com.emazon.stock_microservice.domain.model.Articulo;
import com.emazon.stock_microservice.web.ArticuloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticuloControllerTest {

    @Mock
    private ArticuloService articuloService;

    @InjectMocks
    private ArticuloController articuloController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articuloController).build();
    }

    @Test
    void testListarArticulos() throws Exception {
        Articulo articulo = new Articulo(1L, "Artículo 1", "Descripción del artículo 1", 10, 100.0);
        when(articuloService.listarArticulos()).thenReturn(Arrays.asList(articulo));

        mockMvc.perform(get("/api/articulos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Artículo 1"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripción del artículo 1"))
                .andExpect(jsonPath("$[0].cantidad").value(10))
                .andExpect(jsonPath("$[0].precio").value(100.0));
    }

    @Test
    void testCrearArticulo() throws Exception {
        Articulo articulo = new Articulo(1L, "Artículo 1", "Descripción del artículo 1", 10, 100.0);
        when(articuloService.crearArticulo(articulo)).thenReturn(articulo);

        mockMvc.perform(post("/api/articulos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Artículo 1\",\"descripcion\":\"Descripción del artículo 1\",\"cantidad\":10,\"precio\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Artículo 1"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del artículo 1"))
                .andExpect(jsonPath("$.cantidad").value(10))
                .andExpect(jsonPath("$.precio").value(100.0));
    }

    @Test
    void testObtenerArticuloPorId() throws Exception {
        Articulo articulo = new Articulo(1L, "Artículo 1", "Descripción del artículo 1", 10, 100.0);
        when(articuloService.obtenerArticuloPorId(1L)).thenReturn(articulo);

        mockMvc.perform(get("/api/articulos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Artículo 1"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del artículo 1"))
                .andExpect(jsonPath("$.cantidad").value(10))
                .andExpect(jsonPath("$.precio").value(100.0));
    }

    @Test
    void testEliminarArticulo() throws Exception {
        mockMvc.perform(delete("/api/articulos/1"))
                .andExpect(status().isOk());

        verify(articuloService).eliminarArticulo(1L);
    }
}
