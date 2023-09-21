package com.jogos.jogos.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogos.jogos.models.Genero;
import com.jogos.jogos.repositories.GeneroRepository;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {

    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @PostMapping(value = "/insert_genero")
    public ResponseEntity<String> AddGenero(@RequestBody Genero genero) {
        generoRepository.InsertGenero(genero);

        return ResponseEntity.status(HttpStatus.OK).body("Genero Inserido");
    }

    @GetMapping(value = "/get_generos")
    public ResponseEntity<List<Genero>> AllGeneros() {
        List<Genero> generos = generoRepository.AllGeneros();

        if (generos == null || generos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(generos);
        }
    }

    @GetMapping(value = "/get_genero/{id_genero}")
    public ResponseEntity<Object> GetTheGenero(@PathVariable int id_genero) {
        Genero genero = generoRepository.GetGenero(id_genero);

        if (genero == null) {
            System.out.println(genero);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Género não Encontrado");
        } else {
            System.out.println(genero);
            return ResponseEntity.status(HttpStatus.FOUND).body(genero);
        }
    }

    @DeleteMapping(value = "/delete_genero/{id_genero}")
    public ResponseEntity<String> DeleteGenero(@PathVariable int id_genero) {

        int apagou = generoRepository.DeleteGenero(id_genero);

        if (apagou > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Género apagado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Género não existe");
        }

    }

    @PutMapping(value = "/update_genero/{id_genero}")
    public ResponseEntity<String> UpdateGenero(@PathVariable int id_genero, @RequestBody Genero genero){

        int alterado = generoRepository.UpdateGenero(id_genero, genero.getNome_genero());

        if (alterado > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Género alterado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Género não existe");
        }
    }
}
