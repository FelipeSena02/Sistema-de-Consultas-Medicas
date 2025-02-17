package br.aluno.uece.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.aluno.uece.sistema.model.*;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoAndDataHoraBetween(Medico medico, LocalDateTime start, LocalDateTime end);
    List<Consulta> findByPaciente(Paciente paciente);
}
