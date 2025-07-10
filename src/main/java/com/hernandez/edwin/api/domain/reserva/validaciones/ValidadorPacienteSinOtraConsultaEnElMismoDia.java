package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.reserva.ConsultaRepository;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(ReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario,ultimoHorario);
        if(!pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionException("Paciente ya tiene una consulta reservada para ese dia");
        }
    }
}
