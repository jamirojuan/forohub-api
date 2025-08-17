package com.forohub.dto.topico;

import com.forohub.entities.Topico;
import jakarta.validation.constraints.Size;

public record TopicoUpdateRequest(
        @Size(max = 200) String titulo,
        String mensaje,
        Topico.Status status,
        @Size(max = 100) String curso
) {

}
