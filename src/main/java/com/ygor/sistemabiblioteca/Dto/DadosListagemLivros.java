package com.ygor.sistemabiblioteca.Dto;

import com.ygor.sistemabiblioteca.Entity.Livros;

public record DadosListagemLivros(Long id, String nome, String autor, int quatidade, String isbn ) {

	
	public DadosListagemLivros(Livros livros) {
		this(livros.getId(), livros.getNome(), livros.getAutor(), livros.getQuantidade(), livros.getIsbn());
	}
}
