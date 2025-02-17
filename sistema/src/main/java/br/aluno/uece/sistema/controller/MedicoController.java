package br.aluno.uece.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.aluno.uece.sistema.model.*;
import br.aluno.uece.sistema.service.*;
import java.util.List;

// Controlador Médico
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public Medico createMedico(@RequestBody Medico medico) {
        return medicoService.save(medico);
    }

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoService.findAll();
    }

    @GetMapping("/especialidade/{especialidade}")
    public List<Medico> getMedicosByEspecialidade(@PathVariable String especialidade) {
        return medicoService.findByEspecialidade(especialidade);
    }

    @GetMapping("/nome/{nome}")
    public List<Medico> getMedicosByNome(@PathVariable String nome) {
        return medicoService.findByNomeContaining(nome);
    }

    @GetMapping("/planoDeSaude/{planoDeSaude}")
    public List<Medico> getMedicosByPlanoDeSaude(@PathVariable String planoDeSaude) {
        return medicoService.findByPlanoDeSaude(planoDeSaude);
    }
}
