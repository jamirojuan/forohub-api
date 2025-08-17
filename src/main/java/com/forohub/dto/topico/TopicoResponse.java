package com.forohub.dto.topico;

import com.forohub.entities.Topico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Topico.Status status,
        String autor,
        String curso,
        Integer respuestas
) {
    public static TopicoResponse from(Topico t) {
        return new TopicoResponse(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(),
                t.getStatus(), t.getAutor(), t.getCurso(), t.getRespuestas());
    }
}
