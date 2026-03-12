package com.ygor.sistemabiblioteca.Dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
		
		@NotNull
		String nome, 
		String email) {

}
