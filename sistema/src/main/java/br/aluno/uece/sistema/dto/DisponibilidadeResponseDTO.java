package br.aluno.uece.sistema.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DisponibilidadeResponseDTO {
    private LocalDate data;
    private List<LocalTime> horariosDisponiveis;

    public DisponibilidadeResponseDTO(LocalDate data, List<LocalTime> horariosDisponiveis) {
        this.data = data;
        this.horariosDisponiveis = horariosDisponiveis;
    }

    // Getters
    public LocalDate getData() {
        return data;
    }

    public List<LocalTime> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }
}


