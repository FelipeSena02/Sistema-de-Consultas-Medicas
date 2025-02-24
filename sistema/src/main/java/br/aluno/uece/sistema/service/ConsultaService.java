package br.aluno.uece.sistema.service;

import br.aluno.uece.sistema.dto.*;
import br.aluno.uece.sistema.model.*;
import br.aluno.uece.sistema.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaDTO agendarConsulta(Long medicoId, Long pacienteId, LocalDate data, LocalTime hora) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        if (consultaRepository.existsByMedicoAndDataAndHora(medico, data, hora)) {
            throw new RuntimeException("Horário já está ocupado para este médico");
        }

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setData(data);
        consulta.setHora(hora);
        consulta.setStatus(StatusConsulta.AGENDADA);

        consulta = consultaRepository.save(consulta);
        return new ConsultaDTO(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarConsultasPorMedicoEData(Long medicoId, LocalDate data) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        return consultaRepository.findByMedicoAndData(medico, data)
                .stream()
                .map(ConsultaDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DisponibilidadeResponseDTO verificarDisponibilidade(Long medicoId, LocalDate data) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        List<Consulta> consultasExistentes = consultaRepository.findByMedicoAndData(medico, data);
        List<LocalTime> horariosOcupados = consultasExistentes.stream()
                .map(Consulta::getHora)
                .collect(Collectors.toList());

        List<LocalTime> horariosDisponiveis = gerarHorariosDisponiveis(horariosOcupados);
        return new DisponibilidadeResponseDTO(data, horariosDisponiveis);
    }

    @Transactional
    public AgendamentoResultadoDTO agendar(AgendamentoRequestDTO dto) {
        ConsultaDTO consulta = agendarConsulta(
                dto.getMedicoId(),
                dto.getPacienteId(),
                dto.getData(),
                dto.getHora()
        );

        return new AgendamentoResultadoDTO(
                consulta.getId(),
                consulta.getData(),
                consulta.getHora(),
                consulta.getMedicoNome(),
                consulta.getPacienteNome(),
                false
        );
    }

    @Transactional
    public void cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new RuntimeException("Consulta já está cancelada");
        }

        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }

    @Transactional
    public ConsultaDTO avaliarConsulta(Long consultaId, int avaliacao, String comentario) {
        if (avaliacao < 1 || avaliacao > 5) {
            throw new IllegalArgumentException("A avaliação deve estar entre 1 e 5");
        }

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        consulta.setAvaliacao(avaliacao);
        consulta.setComentario(comentario);

        return new ConsultaDTO(consultaRepository.save(consulta));
    }

    private List<LocalTime> gerarHorariosDisponiveis(List<LocalTime> horariosOcupados) {
        LocalTime inicioExpediente = LocalTime.of(8, 0);
        LocalTime fimExpediente = LocalTime.of(18, 0);

        List<LocalTime> todosHorarios = new ArrayList<>();
        LocalTime horarioAtual = inicioExpediente;

        while (!horarioAtual.isAfter(fimExpediente)) {
            if (!horariosOcupados.contains(horarioAtual)) {
                todosHorarios.add(horarioAtual);
            }
            horarioAtual = horarioAtual.plusHours(1);
        }

        return todosHorarios;
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarConsultasMedico(Long medicoId) {
        System.out.println("Buscando consultas para o médico ID: " + medicoId);

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        List<Consulta> consultas = consultaRepository.findByMedicoId(medicoId);
        System.out.println("Quantidade de consultas encontradas: " + consultas.size());

        return consultas.stream()
                .map(ConsultaDTO::new)
                .collect(Collectors.toList());
    }

    public void adicionarDescricao(Long consultaId, String descricao) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setDescricao(descricao);
        consultaRepository.save(consulta);
    }

}