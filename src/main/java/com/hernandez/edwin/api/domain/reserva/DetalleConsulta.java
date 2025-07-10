package com.hernandez.edwin.api.domain.reserva;

import java.time.LocalDateTime;

public record DetalleConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
    public DetalleConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getFecha());
    }
}
