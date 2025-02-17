package br.aluno.uece.sistema.controller;

import br.aluno.uece.sistema.service.ConsultaService;
import br.aluno.uece.sistema.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar")
    public Consulta agendarConsulta(
            @RequestParam Long medicoId,
            @RequestParam Long pacienteId,
            @RequestParam String dataHora) {
        return consultaService.agendarConsulta(medicoId, pacienteId, LocalDateTime.parse(dataHora));
    }

    @DeleteMapping("/cancelar/{id}")
    public void cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
    }

    @PostMapping("/realizar/{id}")
    public Consulta realizarConsulta(
            @PathVariable Long id,
            @RequestParam String descricao) {
        return consultaService.realizarConsulta(id, descricao);
    }

    @PostMapping("/avaliar/{id}")
    public Consulta avaliarConsulta(
            @PathVariable Long id,
            @RequestParam int avaliacao,
            @RequestParam String comentario) {
        return consultaService.avaliarConsulta(id, avaliacao, comentario);
    }

    @GetMapping("/medico/{medicoId}/dia/{data}")
    public List<Consulta> getConsultasDoMedicoNoDia(
            @PathVariable Long medicoId,
            @PathVariable String data) {
        return consultaService.findByMedicoAndDataHoraBetween(
                medicoId,
                LocalDate.parse(data).atStartOfDay(),
                LocalDate.parse(data).plusDays(1).atStartOfDay()
        );
    }
}