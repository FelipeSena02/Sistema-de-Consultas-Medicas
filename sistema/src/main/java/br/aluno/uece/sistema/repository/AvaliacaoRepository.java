package br.aluno.uece.sistema.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.aluno.uece.sistema.model.Avaliacao;


import java.util.List;
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByConsultaId(Long consultaId);
}
