package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.medico.MedicoRepository;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;

public class ValidadorMedicoActivo {
    private MedicoRepository medicoRepository;
    public void validar(ReservaConsulta datos){
        if(datos.idMedico() == null){
            return;
        }
        var medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());
        if(!medicoEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con medico excluido");
        }
    }
}
