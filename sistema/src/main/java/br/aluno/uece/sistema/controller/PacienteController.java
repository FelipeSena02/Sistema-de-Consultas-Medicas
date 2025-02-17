package br.aluno.uece.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.aluno.uece.sistema.model.*;
import br.aluno.uece.sistema.service.*;
import java.util.List;

// Controlador Paciente
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/nome/{nome}")
    public List<Paciente> getPacientesByNome(@PathVariable String nome) {
        return pacienteService.findByNomeContaining(nome);
    }
}
