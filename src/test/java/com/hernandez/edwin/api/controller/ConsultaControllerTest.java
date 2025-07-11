package com.hernandez.edwin.api.controller;

import com.hernandez.edwin.api.domain.medico.Especialidad;
import com.hernandez.edwin.api.domain.reserva.DetalleConsulta;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;
import com.hernandez.edwin.api.domain.reserva.ReservaDeConsultas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ReservaConsulta> datosReservaConsultaJson;
    @Autowired
    private JacksonTester<DetalleConsulta> datosDetalleConsultaJson;

    @MockBean
    private ReservaDeConsultas reservaDeConsultas;

    @Test
    @DisplayName("Deberia devolver http 400 cuando la request no tenga datos")
    @WithMockUser
    void reserva_ecenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
    @Test
    @DisplayName("Deberia devolver http 200 cuando la request reciba json valido")
    @WithMockUser
    void reserva_ecenario2() throws Exception {
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DetalleConsulta(null,2l,5l,fecha);
        when(reservaDeConsultas.reservar(any())).thenReturn(datosDetalle);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosReservaConsultaJson.write(
                                new ReservaConsulta(2l,5l,fecha,especialidad)
                        ).getJson()
                        )
                )
                .andReturn().getResponse();
        var jsonEsperado = datosDetalleConsultaJson.write(
                datosDetalle
        ).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}