package com.hernandez.edwin.api.domain.reserva;

import java.time.LocalDateTime;

public record DetalleConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
}
