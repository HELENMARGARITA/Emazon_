package com.emazon.stock_microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "marcas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres.")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @NotEmpty(message = "La descripción no puede estar vacía.")
    @Size(max = 120, message = "La descripción no puede tener más de 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String descripcion;
}
