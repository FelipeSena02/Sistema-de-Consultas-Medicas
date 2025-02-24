package br.aluno.uece.sistema.service;
import br.aluno.uece.sistema.model.Avaliacao;
import br.aluno.uece.sistema.dto.AvaliacaoDTO;
import br.aluno.uece.sistema.repository.AvaliacaoRepository;
import br.aluno.uece.sistema.repository.ConsultaRepository;
import br.aluno.uece.sistema.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void salvarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Consulta consulta = consultaRepository.findById(avaliacaoDTO.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setConsulta(consulta);
        avaliacao.setEstrelas(avaliacaoDTO.getEstrelas());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        avaliacaoRepository.save(avaliacao);
    }
}
