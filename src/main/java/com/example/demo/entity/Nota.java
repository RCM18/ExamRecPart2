package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="notas")
public class Nota {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private Long id;
	    @Column(name="nota 1")
	    private Double nota_1;
	    @Column(name="nota 2")
	    private Double nota_2;
	    @Column(name="nota 3")
	    private Double nota_3;
	    @ManyToOne
	    @JoinColumn(name="alumno_id", nullable = false)
	    private Alumno alumno;
	    @ManyToOne
	    @JoinColumn(name="curso_id", nullable = false)
	    private Curso curso;
	}


