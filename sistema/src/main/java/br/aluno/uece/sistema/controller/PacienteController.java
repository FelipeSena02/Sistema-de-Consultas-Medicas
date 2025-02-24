package br.aluno.uece.sistema.controller;

import br.aluno.uece.sistema.model.Paciente;
import br.aluno.uece.sistema.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Exibe a página de cadastro do paciente
    @GetMapping("/cadastrar")
    public String exibirCadastro(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente";
    }

    // Processa o formulário de cadastro e redireciona para uma página home
    @PostMapping("/cadastrar")
    public String cadastrarPaciente(Paciente paciente) {
        pacienteService.salvar(paciente);
        return "redirect:/paciente/home";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "loginpaciente";
    }

    // Endpoint para processar o login
    @PostMapping("/login")
    public String autenticarPaciente(@RequestParam String email,
                                     @RequestParam String senha,
                                     Model model,
                                     HttpSession session) {
        Paciente paciente = pacienteService.buscarPorEmail(email);

        if (paciente != null && paciente.getSenha().equals(senha)) {
            session.setAttribute("pacienteLogado", paciente);
            return "redirect:/paciente/home";
        } else {
            model.addAttribute("erro", "Email ou senha inválidos!");
            return "loginpaciente";
        }
    }

    @GetMapping("/home")
    public String homePaciente(){
        return "consultaspaciente";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Paciente> listarPacientes() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/buscar/{nome}")
    @ResponseBody
    public List<Paciente> buscarPacientesPorNome(@PathVariable String nome) {
        return pacienteService.buscarPorNome(nome);
    }
}