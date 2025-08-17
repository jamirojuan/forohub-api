package com.forohub.repositories;

import com.forohub.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndAutor(String titulo, String autor);
}
