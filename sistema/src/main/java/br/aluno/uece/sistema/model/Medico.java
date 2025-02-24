package br.aluno.uece.sistema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false)
    private Integer estrelas = 0;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "plano_saude", nullable = false)
    private String planoSaude;



    @ElementCollection
    @CollectionTable(
            name = "medico_ultimas_avaliacoes",
            joinColumns = @JoinColumn(name = "medico_id")
    )
    @Column(name = "avaliacao")
    private List<String> ultimasAvaliacoes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "medico_planos_aceitos",
            joinColumns = @JoinColumn(name = "medico_id")
    )
    @Column(name = "plano")
    private List<String> planosAceitos = new ArrayList<>();

    // Construtores
    public Medico() {}

    public Medico(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public List<String> getUltimasAvaliacoes() { return ultimasAvaliacoes; }
    public void setUltimasAvaliacoes(List<String> ultimasAvaliacoes) {
        this.ultimasAvaliacoes = ultimasAvaliacoes;
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

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }


    public List<String> getPlanosAceitos() { return planosAceitos; }
    public void setPlanosAceitos(List<String> planosAceitos) {
        this.planosAceitos = planosAceitos;
    }

    // Métodos auxiliares
    public void adicionarAvaliacao(String avaliacao) {
        if (ultimasAvaliacoes.size() >= 5) {
            ultimasAvaliacoes.remove(0); // Remove a avaliação mais antiga
        }
        ultimasAvaliacoes.add(avaliacao);
    }

    public void adicionarPlano(String plano) {
        if (!planosAceitos.contains(plano)) {
            planosAceitos.add(plano);
        }
    }
}