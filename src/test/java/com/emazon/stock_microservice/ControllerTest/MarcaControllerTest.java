package com.emazon.stock_microservice.ControllerTest;

import com.emazon.stock_microservice.application.service.MarcaService;
import com.emazon.stock_microservice.domain.model.Marca;
import com.emazon.stock_microservice.web.MarcaController;
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

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MarcaControllerTest {

    @Mock
    private MarcaService marcaService;

    @InjectMocks
    private MarcaController marcaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(marcaController).build();
    }

    @Test
    void testListarMarcas() throws Exception {
        Marca marca = new Marca(1L, "Sony", "Marca de electrónica");
        when(marcaService.listarMarcas()).thenReturn(Arrays.asList(marca));

        mockMvc.perform(get("/api/marcas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Sony"))
                .andExpect(jsonPath("$[0].descripcion").value("Marca de electrónica"));
    }

    @Test
    void testCrearMarca() throws Exception {
        Marca marca = new Marca(1L, "Sony", "Marca de electrónica");
        when(marcaService.crearMarca(marca)).thenReturn(marca);

        mockMvc.perform(post("/api/marcas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Sony\",\"descripcion\":\"Marca de electrónica\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Sony"))
                .andExpect(jsonPath("$.descripcion").value("Marca de electrónica"));
    }

    @Test
    void testObtenerMarcaPorId() throws Exception {
        Marca marca = new Marca(1L, "Sony", "Marca de electrónica");
        when(marcaService.obtenerMarcaPorId(1L)).thenReturn(marca);

        mockMvc.perform(get("/api/marcas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Sony"))
                .andExpect(jsonPath("$.descripcion").value("Marca de electrónica"));
    }

    @Test
    void testEliminarMarca() throws Exception {
        mockMvc.perform(delete("/api/marcas/1"))
                .andExpect(status().isOk());

        verify(marcaService).eliminarMarca(1L);
    }

    @Test
    void testListarMarcasPaginado() throws Exception {
        Marca marca = new Marca(1L, "Sony", "Marca de electrónica");
        when(marcaService.listarMarcasPaginado(0, 10, "asc", "nombre"))
                .thenReturn(Collections.singletonList(marca));

        mockMvc.perform(get("/api/marcas?page=0&size=10&sort=nombre,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Sony"))
                .andExpect(jsonPath("$[0].descripcion").value("Marca de electrónica"));
    }
}

