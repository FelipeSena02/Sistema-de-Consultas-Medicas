package br.aluno.uece.sistema.repository;

import br.aluno.uece.sistema.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNomeContaining(String nome);
    Paciente findByEmail(String email);
}