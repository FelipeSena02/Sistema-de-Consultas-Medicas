package br.aluno.uece.sistema.dto;

import br.aluno.uece.sistema.model.Consulta;
import br.aluno.uece.sistema.model.StatusConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaDTO {
    private Long id;
    private Long medicoId;
    private String medicoNome;
    private Long pacienteId;
    private String pacienteNome;
    private LocalDate data;
    private LocalTime hora;
    private StatusConsulta status;
    private Integer avaliacao;
    private String comentario;

    public ConsultaDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.medicoId = consulta.getMedico().getId();
        this.medicoNome = consulta.getMedico().getNome();
        this.pacienteId = consulta.getPaciente().getId();
        this.pacienteNome = consulta.getPaciente().getNome();
        this.data = consulta.getData();
        this.hora = consulta.getHora();
        this.status = consulta.getStatus();
        this.avaliacao = consulta.getAvaliacao();
        this.comentario = consulta.getComentario();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public String getComentario() {
        return comentario;
    }
}