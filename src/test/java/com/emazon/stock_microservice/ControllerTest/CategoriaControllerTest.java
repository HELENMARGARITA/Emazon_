package com.emazon.stock_microservice.ControllerTest;

import com.emazon.stock_microservice.application.service.CategoriaService;
import com.emazon.stock_microservice.domain.model.Categoria;
import com.emazon.stock_microservice.web.CategoriaController;
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

public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    @Test
    void testListarCategorias() throws Exception {
        Categoria categoria = new Categoria(1L, "Electrónica", "Categoría para productos electrónicos");
        when(categoriaService.listarCategorias()).thenReturn(Arrays.asList(categoria));

        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Electrónica"))
                .andExpect(jsonPath("$[0].descripcion").value("Categoría para productos electrónicos"));
    }

    @Test
    void testCrearCategoria() throws Exception {
        Categoria categoria = new Categoria(1L, "Electrónica", "Categoría para productos electrónicos");
        when(categoriaService.crearCategoria(categoria)).thenReturn(categoria);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Electrónica\",\"descripcion\":\"Categoría para productos electrónicos\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Electrónica"))
                .andExpect(jsonPath("$.descripcion").value("Categoría para productos electrónicos"));
    }

    @Test
    void testObtenerCategoriaPorId() throws Exception {
        Categoria categoria = new Categoria(1L, "Electrónica", "Categoría para productos electrónicos");
        when(categoriaService.obtenerCategoriaPorId(1L)).thenReturn(categoria);

        mockMvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Electrónica"))
                .andExpect(jsonPath("$.descripcion").value("Categoría para productos electrónicos"));
    }

    @Test
    void testEliminarCategoria() throws Exception {
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isOk());

        verify(categoriaService).eliminarCategoria(1L);
    }

    @Test
    void testListarCategoriasPaginado() throws Exception {
        Categoria categoria = new Categoria(1L, "Electrónica", "Categoría para productos electrónicos");
        when(categoriaService.listarCategoriasPaginado(0, 10, "asc", "nombre"))
                .thenReturn(Collections.singletonList(categoria));

        mockMvc.perform(get("/api/categorias?page=0&size=10&sort=nombre,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Electrónica"))
                .andExpect(jsonPath("$[0].descripcion").value("Categoría para productos electrónicos"));
    }
}

