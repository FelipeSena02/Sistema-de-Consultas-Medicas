package br.aluno.uece.sistema.repository;

import br.aluno.uece.sistema.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByNomeContaining(String nome);
    List<Medico> findByEspecialidade(String especialidade);

    @Query("SELECT DISTINCT m FROM Medico m LEFT JOIN m.planosAceitos p WHERE " +
            "(:especialidade IS NULL OR m.especialidade = :especialidade) AND " +
            "(:nome IS NULL OR m.nome LIKE %:nome%) AND " +
            "(:planoSaude IS NULL OR :planoSaude MEMBER OF m.planosAceitos)")
    List<Medico> buscarMedicosFiltrados(
            @Param("nome") String nome,
            @Param("especialidade") String especialidade,
            @Param("planoSaude") String planoSaude);

    @Query("SELECT m FROM Medico m WHERE :plano MEMBER OF m.planosAceitos")
    List<Medico> findByPlanoDeSaude(@Param("plano") String plano);
}