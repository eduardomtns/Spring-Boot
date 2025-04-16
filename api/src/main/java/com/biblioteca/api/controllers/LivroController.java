package com.biblioteca.api.controllers;

import com.biblioteca.api.dtos.LivroRequestDTO;
import com.biblioteca.api.dtos.LivroResponseDTO;
import com.biblioteca.api.services.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO create(@RequestBody LivroRequestDTO dto) throws Exception {
        return service.saveLivro(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LivroResponseDTO findById(@PathVariable Long id) throws Exception {
        return service.getLivroById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LivroResponseDTO update(@PathVariable Long id, @RequestBody LivroRequestDTO dto) throws Exception {
        return service.updateLivro(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        service.deleteLivro(id);
    }
}
