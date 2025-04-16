package com.biblioteca.api.repository;

import com.biblioteca.api.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
