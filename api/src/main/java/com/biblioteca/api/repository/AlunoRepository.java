package com.biblioteca.api.repository;

import com.biblioteca.api.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
