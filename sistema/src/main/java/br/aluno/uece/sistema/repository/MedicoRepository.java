package br.aluno.uece.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositório Médico
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidade(String especialidade);
    List<Medico> findByNomeContaining(String nome);
    List<Medico> findByPlanoDeSaude(String planoDeSaude);
}
