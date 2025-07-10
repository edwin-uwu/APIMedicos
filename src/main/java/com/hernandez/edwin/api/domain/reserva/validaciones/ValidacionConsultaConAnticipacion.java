package com.hernandez.edwin.api.domain.reserva.validaciones;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacionConsultaConAnticipacion {
    public void validar(ReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora,fechaConsulta).toMinutes();
        if(diferenciaEnMinutos < 30){
            throw new ValidacionException("Horario seleccionado menos de 30 minutos de anticipacion");
        }
    }
}
