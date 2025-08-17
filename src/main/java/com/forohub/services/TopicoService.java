package com.forohub.services;

import com.forohub.dto.topico.TopicoCreateRequest;
import com.forohub.dto.topico.TopicoResponse;
import com.forohub.dto.topico.TopicoUpdateRequest;
import com.forohub.entities.Topico;
import com.forohub.repositories.TopicoRepository;
import com.forohub.security.GlobalExceptionsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository repository;


    @Transactional
    public TopicoResponse crear(TopicoCreateRequest req) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String autor = (auth != null ? auth.getName() : "anon");

        if(repository.existsByTituloAndAutor(req.titulo(),autor)){
            throw new GlobalExceptionsHandler.DuplicateResourceException("Ya existe un topico con ese titulo");
        }
        Topico topico = Topico.builder()
                .titulo(req.titulo())
                .mensaje(req.mensaje())
                .curso(req.curso())
                .autor(autor)
                .status(Topico.Status.ABIERTO)
                .build();
        repository.save(topico);
        return TopicoResponse.from(topico);
    }

    @Transactional(readOnly = true)
    public List<TopicoResponse> listar() {
        return repository.findAll().stream().map(TopicoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public TopicoResponse obtener(Long id) {
        return repository.findById(id).map(TopicoResponse::from)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }

    @Transactional
    public TopicoResponse actualizar(Long id, TopicoUpdateRequest req) {
        Topico t = repository.findById(id).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        if (req.titulo() != null) t.setTitulo(req.titulo());
        if (req.mensaje() != null) t.setMensaje(req.mensaje());
        if (req.status() != null) t.setStatus(req.status());
        if (req.curso() != null) t.setCurso(req.curso());
        return TopicoResponse.from(t);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
