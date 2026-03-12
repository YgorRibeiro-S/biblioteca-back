package com.ygor.sistemabiblioteca.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLivros(
		
		@NotBlank(message = "Preencha os campos obrigatórios")
		 String nome,
		 String autor,
		 String isbn,
		 @NotNull
		 int quantidade) {

}
