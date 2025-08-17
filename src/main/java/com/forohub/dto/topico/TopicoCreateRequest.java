package com.forohub.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicoCreateRequest(
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank String mensaje,
        @NotBlank @Size(max = 100) String curso
) {}
