package com.example.demo.controller;

import com.example.demo.entity.Nota;
import com.example.demo.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/notas")
@CrossOrigin(origins = "http://localhost:8080")
public class NotaController {
	@Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<Nota>> readAll(){
        try {
            List<Nota> notas = notaService.readAll();
            if(notas.isEmpty()) {
                return new ResponseEntity<List<Nota>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(notas, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    public ResponseEntity<Nota> crear(@Valid @RequestBody Nota nota) {
        try {
        	Nota c = notaService.create(nota);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaId(@PathVariable("id") Long id){
        try {
        	Nota c = notaService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Nota> delNota(@PathVariable("id") Long id){
        try {
            notaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNota(@PathVariable("id") Long id, @Valid @RequestBody Nota nota){

        Optional<Nota> c = notaService.read(id);
        if(c.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(notaService.update(nota), HttpStatus.OK);
        }

    }    

}
