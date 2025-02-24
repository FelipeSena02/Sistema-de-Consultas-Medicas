package br.aluno.uece.sistema.dto;

import java.time.LocalDate;
import java.time.LocalTime;

// AgendamentoResultadoDTO.java
public class AgendamentoResultadoDTO {
    private Long consultaId;
    private LocalDate data;
    private LocalTime hora;
    private String medicoNome;
    private String pacienteNome;
    private boolean listaEspera;  // Novo campo

    public AgendamentoResultadoDTO(Long consultaId, LocalDate data, LocalTime hora,
                                   String medicoNome, String pacienteNome, boolean listaEspera) {
        this.consultaId = consultaId;
        this.data = data;
        this.hora = hora;
        this.medicoNome = medicoNome;
        this.pacienteNome = pacienteNome;
        this.listaEspera = listaEspera;
    }


    // Getters
    public Long getConsultaId() {
        return consultaId;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isListaEspera() {  // Novo getter
        return listaEspera;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }
}

