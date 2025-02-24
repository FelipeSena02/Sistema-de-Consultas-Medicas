package br.aluno.uece.sistema.dto;

import java.time.LocalDate;
import java.time.LocalTime;

// AgendamentoRequestDTO.java
public class AgendamentoRequestDTO {
    private Long medicoId;
    private Long pacienteId;
    private LocalDate data;
    private LocalTime hora;
    private Boolean listaEspera;

    // Getters e Setters
    public Long getMedicoId() {
        return medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Boolean getListaEspera(){
        return listaEspera;
    }

    public void setListaEspera(Boolean listaEspera){
        this.listaEspera = listaEspera;
    }
}

