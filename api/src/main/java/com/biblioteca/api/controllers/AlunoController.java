package com.biblioteca.api.controllers;

import com.biblioteca.api.dtos.AlunoRequestDTO;
import com.biblioteca.api.dtos.AlunoResponseDTO;
import com.biblioteca.api.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponseDTO addAluno(@RequestBody AlunoRequestDTO dto) throws Exception {
        return alunoService.saveAluno(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AlunoResponseDTO getAlunoById(@PathVariable Long id) throws Exception {
        return alunoService.getAlunoById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AlunoResponseDTO updateAluno(@PathVariable Long id, @RequestBody AlunoRequestDTO dto) throws Exception {
        return alunoService.updateAluno(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAluno(@PathVariable Long id) throws Exception {
        alunoService.deleteAluno(id);
    }
}
