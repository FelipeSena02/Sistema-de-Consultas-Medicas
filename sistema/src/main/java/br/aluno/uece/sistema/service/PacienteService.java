package br.aluno.uece.sistema.service;

import br.aluno.uece.sistema.model.Paciente;
import br.aluno.uece.sistema.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeContaining(nome);
    }

    public Paciente buscarPorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

}