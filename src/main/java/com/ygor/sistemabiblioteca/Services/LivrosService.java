package com.ygor.sistemabiblioteca.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ygor.sistemabiblioteca.Dto.DadosCadastroLivros;
import com.ygor.sistemabiblioteca.Dto.DadosEstatisticaLivros;
import com.ygor.sistemabiblioteca.Dto.DadosEstatisticasPorAutor;
import com.ygor.sistemabiblioteca.Dto.DadosListagemLivros;
import com.ygor.sistemabiblioteca.Entity.Livros;
import com.ygor.sistemabiblioteca.Repository.LivrosRepository;

import jakarta.transaction.Transactional;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository repository;
	
	@Transactional
	public Livros cadastrar(DadosCadastroLivros dados, MultipartFile imagem) throws Exception {

	    Livros livro = new Livros(dados);

	    livro.setImagem(imagem.getBytes());

	    return repository.save(livro);
	}
	
	public Livros buscarPorId(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
	}
	
	public List<DadosListagemLivros> listar(){
		return repository.findAllByStatusTrue()
				.stream()
				.map(DadosListagemLivros::new)
				.toList();
	}
	
	public DadosEstatisticaLivros estatisticas() {

	    long totalLivros = repository.countByStatusTrue();

	    Long soma = repository.somaTotalExemplares();
	    long totalExemplares = soma != null ? soma : 0L;

	    long livrosSemEstoque = repository.countByQuantidadeAndStatusTrue(0);

	    return new DadosEstatisticaLivros(
	            totalLivros,
	            totalExemplares,
	            livrosSemEstoque
	    );
	}
	
	@Transactional
	public void inativar(Long id) {
		var livros = repository.getReferenceById(id);
		livros.inativar();
	}
	
	public List<DadosEstatisticasPorAutor> estatisticasPorAutor() {
	    return repository.buscarEstatisticasPorAutor().stream()
	            .map(o -> new DadosEstatisticasPorAutor(
	                    (String)o[0],
	                    ((Long)o[1]),
	                    ((Long)o[2])
	            ))
	            .toList();
	}

}
