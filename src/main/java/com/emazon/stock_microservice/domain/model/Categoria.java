package com.emazon.stock_microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres.")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @NotEmpty(message = "La descripción no puede estar vacía.")
    @Size(max = 90, message = "La descripción no puede tener más de 90 caracteres.")
    @Column(nullable = false, length = 90)
    private String descripcion;

    // Relación con Articulo
    @ManyToMany(mappedBy = "categorias")
    private List<Articulo> articulos;
}



