package com.example.demo.controller;

import com.example.demo.entity.Curso;
import com.example.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cursos")
@CrossOrigin(origins = "http://localhost:8080")
public class CursoController {
	@Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> readAll(){
        try {
            List<Curso> cursos = cursoService.readAll();
            if(cursos.isEmpty()) {
                return new ResponseEntity<List<Curso>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso) {
        try {
        	Curso c = cursoService.create(curso);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoId(@PathVariable("id") Long id){
        try {
        	Curso c = cursoService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> delCurso(@PathVariable("id") Long id){
        try {
            cursoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable("id") Long id, @Valid @RequestBody Curso curso){

        Optional<Curso> c = cursoService.read(id);
        if(c.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(cursoService.update(curso), HttpStatus.OK);
        }

    }

}
