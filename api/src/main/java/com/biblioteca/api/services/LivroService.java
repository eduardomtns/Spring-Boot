package com.biblioteca.api.services;

import com.biblioteca.api.dtos.LivroRequestDTO;
import com.biblioteca.api.dtos.LivroResponseDTO;
import com.biblioteca.api.entities.Livro;
import com.biblioteca.api.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO getLivroById(Long id) throws Exception {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));
        return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn());
    }

    public LivroResponseDTO updateLivro(Long id, LivroRequestDTO livroUpdateDto) throws Exception {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));

        if (livroUpdateDto.titulo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Titulo não pode ser vazio.");
        }

        if (livroUpdateDto.isbn().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN não pode ser vazio.");
        }

        livro.setTitulo(livroUpdateDto.titulo());
        livro.setIsbn(livroUpdateDto.isbn());

        livroRepository.save(livro);

        return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn());
    }

    public void deleteLivro(Long id) throws Exception {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado."));
        livroRepository.delete(livro);
    }

    public LivroResponseDTO saveLivro(LivroRequestDTO livroRequestDto) {
        if(livroRequestDto.titulo().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Titulo não pode ser vazio.");
        }

        if(livroRequestDto.isbn().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN não pode ser vazio.");
        }

        Livro livro = new Livro();
        livro.setTitulo(livroRequestDto.titulo());
        livro.setIsbn(livroRequestDto.isbn());

        livroRepository.save(livro);

        return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn());
    }
}