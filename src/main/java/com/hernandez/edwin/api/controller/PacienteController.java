package com.hernandez.edwin.api.controller;

import com.hernandez.edwin.api.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid RegistroPacienteDTO paciente, UriComponentsBuilder uriComponentsBuilder){
        var pacient = new Paciente(paciente);
        repository.save(pacient);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetallePaciente(pacient));
    }
    @GetMapping
    public ResponseEntity<Page<ListaPacienteDTO>> listar(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion){
        var pagina = repository.findAllByActivoTrue(paginacion)
                .map(ListaPacienteDTO::new);
        return ResponseEntity.ok(pagina);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid ActualizacionPacienteDTO paciente){
        var pacienteEncontrado = repository.getReferenceById(paciente.id());
        pacienteEncontrado.actualizarPaciente(paciente);

        return ResponseEntity.ok(new DetallePaciente(pacienteEncontrado));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var pacienteEncontrado = repository.getReferenceById(id);
        pacienteEncontrado.eliminar();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalle(@PathVariable Long id){
        var pacienteEncontrado = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetallePaciente(pacienteEncontrado));
    }
}
