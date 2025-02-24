package br.aluno.uece.sistema.repository;

import br.aluno.uece.sistema.model.ListaEspera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {
    List<ListaEspera> findByMedicoIdAndDataOrderByDataRegistroAsc(Long medicoId, LocalDate data);
}

