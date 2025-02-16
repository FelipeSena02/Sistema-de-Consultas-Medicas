package br.aluno.uece.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Serviço Paciente
@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> findByNomeContaining(String nome) {
        return pacienteRepository.findByNomeContaining(nome);
    }
}
