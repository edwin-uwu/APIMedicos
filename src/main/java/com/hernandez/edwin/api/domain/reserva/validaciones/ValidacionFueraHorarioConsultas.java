package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;

import java.time.DayOfWeek;

public class ValidacionFueraHorarioConsultas {
    public void validar(ReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreLimite = fechaConsulta.getHour() > 18;
        if(domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreLimite)
        {
            throw  new ValidacionException("Horario seleccionado fuera del horario de atención de  la clínica");
        }
    }
}
