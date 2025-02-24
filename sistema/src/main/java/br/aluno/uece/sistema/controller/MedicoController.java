package br.aluno.uece.sistema.controller;

import br.aluno.uece.sistema.model.Medico;
import br.aluno.uece.sistema.service.MedicoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.aluno.uece.sistema.repository.MedicoRepository;
import br.aluno.uece.sistema.dto.*;

import java.util.List;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/por-especialidade")
    @ResponseBody
    public List<Medico> getMedicosPorEspecialidade(@RequestParam String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    @GetMapping("/teste")
    @ResponseBody
    public List<Medico> teste() {
        return medicoRepository.findAll();
    }

    @GetMapping("/cadastrar")
    public String exibirCadastro(Model model) {
        return "medico";
    }

    @PostMapping("/cadastrar")
    public String cadastrarMedico(@ModelAttribute MedicoDTO medicoDTO, Model model) {
        try {
            medicoService.salvar(medicoDTO);
            return "redirect:/medico/sucesso";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar médico: " + e.getMessage());
            return "medico";
        }
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucessomedico";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "loginmedico";
    }

    @PostMapping("/login")
    public String autenticarMedico(@RequestParam String email,
                                   @RequestParam String senha,
                                   Model model,
                                   HttpSession session) {
        return "redirect:/consulta/consultas-medico";
    }

    @GetMapping("/home")
    public String homeMedicos() {
        return "consultasmedico";
    }

    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        return ResponseEntity.ok(medicoService.buscarTodos());
    }


    @GetMapping("/especialidade/{especialidade}")
    @ResponseBody
    public ResponseEntity<List<MedicoDTO>> buscarMedicosPorEspecialidade(
            @PathVariable String especialidade) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidade(especialidade));
    }

    @GetMapping("/buscar/nome/{nome}")
    @ResponseBody
    public ResponseEntity<List<MedicoDTO>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(medicoService.buscarPorNome(nome));
    }

    @GetMapping("/buscar/plano/{planoDeSaude}")
    @ResponseBody
    public ResponseEntity<List<MedicoDTO>> buscarPorPlano(@PathVariable String planoDeSaude) {
        return ResponseEntity.ok(medicoService.buscarPorPlanoDeSaude(planoDeSaude));
    }

    @GetMapping("/medicos")
    @ResponseBody
    public ResponseEntity<List<MedicoDTO>> buscarMedicos(
            @RequestParam String especialidade,
            HttpSession session) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidade(especialidade));
    }
}