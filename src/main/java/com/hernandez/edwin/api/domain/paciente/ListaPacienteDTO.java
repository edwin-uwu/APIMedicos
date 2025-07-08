package com.hernandez.edwin.api.domain.paciente;

public record ListaPacienteDTO(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public ListaPacienteDTO(Paciente datosPaciente){
        this(datosPaciente.getId(),datosPaciente.getNombre(),datosPaciente.getEmail(),datosPaciente.getDocumento());
    }
}
