package com.hernandez.edwin.api.domain.paciente;

import com.hernandez.edwin.api.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record ActualizacionPacienteDTO(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direcion
) {
}
