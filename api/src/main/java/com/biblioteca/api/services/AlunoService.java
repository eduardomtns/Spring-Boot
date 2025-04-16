package com.biblioteca.api.services;

import com.biblioteca.api.dtos.AlunoRequestDTO;
import com.biblioteca.api.dtos.AlunoResponseDTO;
import com.biblioteca.api.entities.Aluno;
import com.biblioteca.api.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDTO getAlunoById(Long id) throws Exception {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));
        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getEmail());
    }

    public AlunoResponseDTO updateAluno(Long id, AlunoRequestDTO alunoUpdateDto) throws Exception {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));

        if (alunoUpdateDto.nome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não pode ser vazio.");
        }

        if (alunoUpdateDto.matricula().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula não pode ser vazia.");
        }

        if (alunoUpdateDto.email().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não pode ser vazio.");
        }

        aluno.setNome(alunoUpdateDto.nome());
        aluno.setMatricula(alunoUpdateDto.matricula());
        aluno.setEmail(alunoUpdateDto.email());

        alunoRepository.save(aluno);

        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getEmail());
    }

    public void deleteAluno(Long id) throws Exception {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));
        alunoRepository.delete(aluno);
    }

    public AlunoResponseDTO saveAluno(AlunoRequestDTO alunoRequestDto) {
        if (alunoRequestDto.nome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome não pode ser vazio.");
        }

        if (alunoRequestDto.matricula().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula não pode ser vazia.");
        }

        if (alunoRequestDto.email().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não pode ser vazio.");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequestDto.nome());
        aluno.setMatricula(alunoRequestDto.matricula());
        aluno.setEmail(alunoRequestDto.email());

        alunoRepository.save(aluno);

        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getEmail());
    }
}