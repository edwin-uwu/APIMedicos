package com.hernandez.edwin.api.domain.medico;

import com.hernandez.edwin.api.domain.direccion.DatosDireccion;
import com.hernandez.edwin.api.domain.paciente.Paciente;
import com.hernandez.edwin.api.domain.paciente.RegistroPacienteDTO;
import com.hernandez.edwin.api.domain.reserva.Consulta;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deberia de volver null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        //given o arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = registrarMedico("Medico1","medico@gmail.com","12345",Especialidad.CARDIOLOGIA);
        var paciente= registrarPaciente("Paciente1","paciente@gmail.com","12346");
        registrarConsulta(medico,paciente,lunesSiguienteALas10);

        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA,lunesSiguienteALas10);
        assertThat(medicoLibre).isNull();
    }
    @Test
    @DisplayName("Deberia devolver medico cuando el medico buscado esta disponible  en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico = registrarMedico("Medico1","medico@gmail.com","12345",Especialidad.CARDIOLOGIA);


        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA,lunesSiguienteALas10);
        assertThat(medicoLibre).isEqualTo(medico);
    }
    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        em.persist(new Consulta(null,medico,paciente,fecha));
    }
    private Medico registrarMedico(String nombre,String email,String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre,email,documento,especialidad));
        em.persist(medico);
        return medico;
    }
    private Paciente registrarPaciente(String nombre,String email,String documento){
        var paciente = new Paciente(datosPaciente(nombre,email,documento));
        em.persist(paciente);
        return paciente;
    }
    private DatosRegistroMedicos datosMedico(String nombre,String email,String documento, Especialidad especialidad){
        return new DatosRegistroMedicos(nombre,email,"5576867887",documento,especialidad,datosDireccion());
    }
    private RegistroPacienteDTO datosPaciente(String nombre,String email, String documento){
        return new RegistroPacienteDTO(nombre,email,"1123456789",documento,datosDireccion());
    }
    private DatosDireccion datosDireccion(){
        return new DatosDireccion("calle x","","","distrito y","ciudad z","1233","1");
    }
}