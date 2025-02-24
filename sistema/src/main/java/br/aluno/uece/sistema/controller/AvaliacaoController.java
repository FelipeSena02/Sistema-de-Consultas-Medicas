package br.aluno.uece.sistema.controller;

import org.springframework.stereotype.Controller;
import br.aluno.uece.sistema.service.AvaliacaoService;
import br.aluno.uece.sistema.model.Avaliacao;
import br.aluno.uece.sistema.dto.AvaliacaoDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/avaliar")
    public String mostrarPaginaAvaliacao() {
        return "avaliar";
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<?> salvarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        try {
            avaliacaoService.salvarAvaliacao(avaliacaoDTO);
            return ResponseEntity.ok().body("Avaliação salva com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar avaliação: " + e.getMessage());
        }
    }
}

