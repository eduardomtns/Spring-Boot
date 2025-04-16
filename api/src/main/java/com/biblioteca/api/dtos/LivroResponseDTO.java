package com.biblioteca.api.dtos;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String isbn
){
}
