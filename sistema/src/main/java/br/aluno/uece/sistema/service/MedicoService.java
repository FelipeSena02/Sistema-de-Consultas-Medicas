package br.aluno.uece.sistema.service;

import br.aluno.uece.sistema.model.Medico;
import br.aluno.uece.sistema.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.aluno.uece.sistema.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void salvar(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoDTO.getNome());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        medico.setEmail(medicoDTO.getEmail());
        medico.setSenha(medicoDTO.getSenha());
        medico.setPlanoSaude(medicoDTO.getPlanosAceitos());

        // Convertendo a string de planos em lista
        List<String> planos = Arrays.asList(medicoDTO.getPlanosAceitos().split(","));
        medico.setPlanosAceitos(planos);

        medicoRepository.save(medico);
    }

    public List<MedicoDTO> buscarTodos() {
        return medicoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MedicoDTO> buscarPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MedicoDTO> buscarPorNome(String nome) {
        return medicoRepository.findByNomeContaining(nome).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MedicoDTO> buscarPorPlanoDeSaude(String plano) {
        return medicoRepository.findByPlanoDeSaude(plano).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setNome(medico.getNome());
        dto.setEspecialidade(medico.getEspecialidade());
        dto.setEmail(medico.getEmail());
        dto.setPlanosAceitos(String.join(",", medico.getPlanosAceitos()));
        return dto;
    }
}