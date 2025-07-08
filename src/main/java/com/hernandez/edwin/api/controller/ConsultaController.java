package com.hernandez.edwin.api.controller;

import com.hernandez.edwin.api.domain.reserva.DetalleConsulta;
import com.hernandez.edwin.api.domain.reserva.ReservaConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity reserva(@RequestBody @Valid ReservaConsulta datos){
        System.out.println(datos);
        return ResponseEntity.ok(new DetalleConsulta(null, null,null,null));
    }
}
