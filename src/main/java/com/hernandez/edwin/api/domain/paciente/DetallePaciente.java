package com.hernandez.edwin.api.domain.paciente;

import com.hernandez.edwin.api.domain.direccion.Direccion;

public record DetallePaciente(
        Long id,
        Boolean activo,
        String nombre,
        String email,
        String telefono,
        String documento,
        Direccion direccion
) {
    public DetallePaciente(Paciente datos){
        this(datos.getId(),datos.getActivo(),datos.getNombre(),datos.getEmail(),datos.getTelefono(),datos.getDocumento(),datos.getDireccion());
    }
}
