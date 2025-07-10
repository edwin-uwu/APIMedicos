package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.reserva.ConsultaRepository;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(ReservaConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(),datos.fecha());
        if(!medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidacionException("Medico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}
