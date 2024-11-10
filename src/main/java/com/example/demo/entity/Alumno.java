package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="alumnos")
public class Alumno {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellidos")
    private String apellidos;
    @Column(name="DNI")
    private String DNI;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "alumno")
    @JsonIgnore
    private List<Nota> notas;

}
