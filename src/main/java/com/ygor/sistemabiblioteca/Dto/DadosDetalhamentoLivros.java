package com.ygor.sistemabiblioteca.Dto;

import com.ygor.sistemabiblioteca.Entity.Livros;

public record DadosDetalhamentoLivros(Long id, String nome, String autor, int quantidade, String isbn) {

	public DadosDetalhamentoLivros(Livros livros) {
		this(livros.getId(), livros.getNome(), livros.getAutor(), livros.getQuantidade(),livros.getIsbn());
	}
}
