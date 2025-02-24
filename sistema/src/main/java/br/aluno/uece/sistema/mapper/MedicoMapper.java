package br.aluno.uece.sistema.mapper;

import br.aluno.uece.sistema.dto.MedicoDTO;
import br.aluno.uece.sistema.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getId());
        dto.setNome(medico.getNome());
        dto.setEspecialidade(medico.getEspecialidade());
        dto.setEmail(medico.getEmail());
        dto.setSenha(medico.getSenha());
        dto.setPlanosAceitos(medico.getPlanoSaude());
        return dto;
    }

    public Medico toEntity(MedicoDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setEspecialidade(dto.getEspecialidade());
        medico.setEmail(dto.getEmail());
        medico.setSenha(dto.getSenha());
        medico.setPlanoSaude(dto.getPlanosAceitos());
        return medico;
    }
}