package br.aluno.uece.sistema.repository;

import br.aluno.uece.sistema.model.StatusConsulta;
import br.aluno.uece.sistema.model.Consulta;
import br.aluno.uece.sistema.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoAndData(Medico medico, LocalDate data);
    List<Consulta> findByMedicoId(Long medicoId);
    boolean existsByMedicoAndDataAndHora(Medico medico, LocalDate data, LocalTime hora);
    List<Consulta> findByMedicoIdOrderByDataDesc(Long medicoId);
    List<Consulta> findByPacienteIdOrderByDataDesc(Long pacienteId);
    List<Consulta> findByMedicoAndStatusNot(Medico medico, StatusConsulta status);

}
