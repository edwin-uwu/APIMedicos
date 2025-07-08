package com.hernandez.edwin.api.domain.medico;

import com.hernandez.edwin.api.domain.direccion.Direccion;

public record DatosDetalleMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetalleMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(),medico.getEspecialidad(),medico.getDireccion());
    }
}
