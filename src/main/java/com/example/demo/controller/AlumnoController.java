package com.example.demo.controller;

import com.example.demo.entity.Alumno;
import com.example.demo.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/alumnos")
@CrossOrigin(origins = "http://localhost:8080")
public class AlumnoController {
	@Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> readAll(){
        try {
            List<Alumno> alumnos = alumnoService.readAll();
            if(alumnos.isEmpty()) {
                return new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alumnos, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    public ResponseEntity<Alumno> crear(@Valid @RequestBody Alumno alumno) {
        try {
        	Alumno c = alumnoService.create(alumno);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoId(@PathVariable("id") Long id){
        try {
        	Alumno c = alumnoService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> delAlumno(@PathVariable("id") Long id){
        try {
            alumnoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable("id") Long id, @Valid @RequestBody Alumno alumno){

        Optional<Alumno> c = alumnoService.read(id);
        if(c.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(alumnoService.update(alumno), HttpStatus.OK);
        }

    }
}