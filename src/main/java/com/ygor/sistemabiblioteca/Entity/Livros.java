package com.ygor.sistemabiblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ygor.sistemabiblioteca.Dto.DadosCadastroLivros;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity(name = "Livros")
@Table(name = "livros")
@EqualsAndHashCode(of = "id")
public class Livros {
	
	public Livros(DadosCadastroLivros dados) {
		this.nome=dados.nome();
		this.autor=dados.autor();
		this.quantidade=dados.quantidade();
		this.isbn=dados.isbn();
		this.status = true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String autor;
	private int quantidade;
	private String isbn;
	private boolean status = true;
	
	@JsonIgnore
	@ManyToOne
    private Usuarios usuarioAtual;
	
	public Livros() {
		
	}
	
	public Livros(Long id, String nome, String autor, int quantidade, String isbn) {
		this.id = id;
		this.nome = nome;
		this.autor = autor;
		this.quantidade = quantidade;
		this.isbn = isbn;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	  public boolean emprestarPara(Usuarios usuarios) {
	        if (!status || quantidade <= 0) return false;
	        quantidade--;
	        usuarioAtual = usuarios;
	        if (quantidade == 0) status = false;
	        return true;
	    }
	  
	  public boolean devolver() {
	        quantidade++;
	        usuarioAtual = null;
	        status = true;
	        return true;
	    }
	
	public void inativar() {
		this.status = false;
	}
	
	@Lob
	private byte[] imagem;

	public byte[] getImagem() {
	    return imagem;
	}

	public void setImagem(byte[] imagem) {
	    this.imagem = imagem;
	}
	
}
