package com.hernandez.edwin.api.domain.reserva;

import com.hernandez.edwin.api.domain.ValidacionException;
import com.hernandez.edwin.api.domain.medico.Medico;
import com.hernandez.edwin.api.domain.medico.MedicoRepository;
import com.hernandez.edwin.api.domain.paciente.Paciente;
import com.hernandez.edwin.api.domain.paciente.PacienteRepository;
import com.hernandez.edwin.api.domain.reserva.validaciones.ValidadorDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public DetalleConsulta reservar(ReservaConsulta datos){

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id informado");
        }
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el id informado");
        }

        //Validaciones
        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null){
            throw new ValidacionException("No existe un medico disponible en el horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null,medico,paciente,datos.fecha());
        repository.save(consulta);

        return new DetalleConsulta(consulta);
    }

    private Medico elegirMedico(ReservaConsulta datos) {

        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(),datos.fecha());
    }
}
