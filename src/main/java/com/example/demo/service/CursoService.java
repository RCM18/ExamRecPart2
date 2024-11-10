package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Curso;

public interface CursoService {
	Curso create(Curso curso);
	Curso update(Curso curso);
    Optional<Curso> read(Long id);
    void delete(Long id);
    List<Curso> readAll();

}
