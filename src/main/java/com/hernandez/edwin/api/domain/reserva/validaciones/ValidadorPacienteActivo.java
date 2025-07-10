package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.paciente.PacienteRepository;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository pacienteRepository;
    public void validar(ReservaConsulta datos){
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo){
            throw  new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
