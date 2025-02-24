package br.aluno.uece.sistema.dto;

import java.util.ArrayList;
import java.util.List;

public class MedicoDTO {
    private Long id;
    private String nome;
    private String especialidade;
    private String email;
    private String senha;
    private String planosAceitos;
    private List<String> ultimasAvaliacoes = new ArrayList<>();


    public MedicoDTO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPlanosAceitos() {
        return planosAceitos;
    }

    public void setPlanosAceitos(String planosAceitos) {
        this.planosAceitos = planosAceitos;
    }

    public List<String> getUltimasAvaliacoes() {
        return ultimasAvaliacoes;
    }

    public void setUltimasAvaliacoes(List<String> ultimasAvaliacoes) {
        this.ultimasAvaliacoes = ultimasAvaliacoes;
    }
}
