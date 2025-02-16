package br.aluno.uece.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Serviço Médico
@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public List<Medico> findByEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public List<Medico> findByNomeContaining(String nome) {
        return medicoRepository.findByNomeContaining(nome);
    }

    public List<Medico> findByPlanoDeSaude(String planoDeSaude) {
        return medicoRepository.findByPlanoDeSaude(planoDeSaude);
    }
}
