package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.paciente.PacienteRepository;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;

public class ValidadorPacienteActivo {
    private PacienteRepository pacienteRepository;
    public void validar(ReservaConsulta datos){
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo){
            throw  new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
