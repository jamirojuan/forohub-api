package com.forohub.controllers;

import com.forohub.dto.topico.TopicoCreateRequest;
import com.forohub.dto.topico.TopicoResponse;
import com.forohub.dto.topico.TopicoUpdateRequest;
import com.forohub.services.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoResponse> crear(@RequestBody @Valid TopicoCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
    }

    @GetMapping
    public List<TopicoResponse> listar() {
        return service.listar();
    }

    @GetMapping("{id}")
    public TopicoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("{id}")
    public TopicoResponse actualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
