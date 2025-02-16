package br.aluno.uece.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositório Paciente
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNomeContaining(String nome);
}
