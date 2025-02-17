package br.aluno.uece.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.aluno.uece.sistema")
public class SistemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaApplication.class, args);
	}

}
