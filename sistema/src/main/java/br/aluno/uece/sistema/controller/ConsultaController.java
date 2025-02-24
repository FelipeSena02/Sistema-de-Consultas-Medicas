package br.aluno.uece.sistema.controller;

import br.aluno.uece.sistema.model.Consulta;
import br.aluno.uece.sistema.service.ConsultaService;
import br.aluno.uece.sistema.service.MedicoService;
import br.aluno.uece.sistema.dto.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    // Endpoints que retornam views HTML
    @GetMapping("/agendar")
    public String exibirAgendamento(Model model) {
        return "agendar";
    }

    @GetMapping("/cancelar")
    public String exibirCancelarConsulta() {
        return "cancelar";
    }

    @GetMapping("/avaliar")
    public String exibirAvaliacao() {
        return "avaliar";
    }

    @GetMapping("/realizar")
    public String exibirRealizacao() {
        return "realizar";
    }

    // Endpoints de requisição
    @GetMapping("/verificar-disponibilidade")
    @ResponseBody
    public ResponseEntity<DisponibilidadeResponseDTO> verificarDisponibilidade(
            @RequestParam Long medicoId,
            @RequestParam LocalDate data) {
        DisponibilidadeResponseDTO disponibilidade = consultaService.verificarDisponibilidade(medicoId, data);
        return ResponseEntity.ok(disponibilidade);
    }

    @PostMapping("/agendar-ajax")
    @ResponseBody
    public ResponseEntity<?> agendarConsultaAjax(@RequestBody AgendamentoRequestDTO dto) {
        try {
            var resultado = consultaService.agendar(dto);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", resultado.isListaEspera()
                            ? "Você foi adicionado à lista de espera com sucesso!"
                            : "Consulta agendada com sucesso!"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/listar/{medicoId}/{data}")
    @ResponseBody
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorMedicoEData(
            @PathVariable Long medicoId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorMedicoEData(medicoId, data);

        if (consultas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(consultas);
    }



    @GetMapping("/medico/especialidade/{especialidade}")
    @ResponseBody
    public List<MedicoDTO> listarMedicosPorEspecialidade(
            @PathVariable String especialidade) {
        return medicoService.buscarPorEspecialidade(especialidade);
    }

    @PostMapping("/agendar")
    public String agendarConsulta(
            @RequestParam Long medicoId,
            @RequestParam Long pacienteId,
            @RequestParam String dataHora,
            Model model) {
        try {
            return "redirect:/consulta/sucesso";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "agendar";
        }
    }

    @PostMapping("/cancelar")
    public String processarCancelarConsulta(
            @RequestParam("appointmentId") Long id,
            Model model) {
        try {
            consultaService.cancelarConsulta(id);
            return "redirect:/consulta/lista";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "cancelar";
        }
    }

    @PostMapping("/avaliar")
    public String avaliarConsulta(
            @RequestParam("appointmentId") Long id,
            @RequestParam int avaliacao,
            @RequestParam("comments") String comentario,
            Model model) {
        try {
            consultaService.avaliarConsulta(id, avaliacao, comentario);
            return "redirect:/consulta/lista";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "avaliar";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        try {
            consultaService.cancelarConsulta(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/medico/dashboard/{medicoId}")
    public String dashboardMedico(@PathVariable Long medicoId, Model model) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasMedico(medicoId);
        model.addAttribute("consultas", consultas);
        return "consultasmedico";
    }


    @GetMapping("/consultas-medico")
    public String listarConsultasMedico(Model model) {
        Long medicoId = 1L;
        System.out.println("Buscando consultas para o médico ID: " + medicoId);

        List<ConsultaDTO> consultas = consultaService.listarConsultasMedico(medicoId);
        System.out.println("Consultas encontradas: " + consultas.size());

        model.addAttribute("consultas", consultas);
        return "consultasmedico";
    }




    @PostMapping("/finalizar")
    @ResponseBody
    public Map<String, String> finalizarConsulta(@RequestParam Long consultaId,
                                                 @RequestParam String descricao) {
        try {
            consultaService.adicionarDescricao(consultaId, descricao);
            return Map.of("status", "success", "message", "Descrição salva com sucesso");
        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }






}