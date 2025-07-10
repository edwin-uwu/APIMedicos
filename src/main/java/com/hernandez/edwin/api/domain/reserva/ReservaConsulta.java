package com.hernandez.edwin.api.domain.reserva;

import com.hernandez.edwin.api.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaConsulta(
        Long idMedico,
        @NotNull Long idPaciente,
        @NotNull @Future LocalDateTime fecha,
        Especialidad especialidad
) {
}
