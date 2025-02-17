package br.aluno.uece.sistema.service;

import br.aluno.uece.sistema.repository.*;
import br.aluno.uece.sistema.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta agendarConsulta(Long medicoId, Long pacienteId, LocalDateTime dataHora) {
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<Consulta> consultasNoDia = consultaRepository.findByMedicoAndDataHoraBetween(medico, dataHora.toLocalDate().atStartOfDay(), dataHora.toLocalDate().plusDays(1).atStartOfDay());
        if (consultasNoDia.size() >= 4) {
            throw new RuntimeException("Médico já tem 4 consultas agendadas nesse dia");
        }

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(dataHora);

        return consultaRepository.save(consulta);
    }

    public void cancelarConsulta(Long consultaId) {
        consultaRepository.deleteById(consultaId);
    }

    public Consulta realizarConsulta(Long consultaId, String descricao) {
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setDescricao(descricao);

        return consultaRepository.save(consulta);
    }

    public Consulta avaliarConsulta(Long consultaId, int avaliacao, String comentario) {
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setAvaliacao(avaliacao);
        consulta.setComentario(comentario);

        return consultaRepository.save(consulta);
    }
    public List<Consulta> findByMedicoAndDataHoraBetween(Long medicoId, LocalDateTime start, LocalDateTime end) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return consultaRepository.findByMedicoAndDataHoraBetween(medico, start, end);
    }

}
