package com.ygor.sistemabiblioteca.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ygor.sistemabiblioteca.Dto.DadosCadastroLivros;
import com.ygor.sistemabiblioteca.Dto.DadosDetalhamentoLivros;
import com.ygor.sistemabiblioteca.Dto.DadosEstatisticaLivros;
import com.ygor.sistemabiblioteca.Dto.DadosEstatisticasPorAutor;
import com.ygor.sistemabiblioteca.Dto.DadosListagemLivros;
import com.ygor.sistemabiblioteca.Entity.Livros;
import com.ygor.sistemabiblioteca.Services.LivrosService;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/livros")
public class LivrosController {
	
	@Autowired
	private LivrosService livrosService;
	
	@PostMapping(consumes = "multipart/form-data")
	@Transactional
	public ResponseEntity<DadosDetalhamentoLivros> cadastrar(
	        @RequestParam String nome,
	        @RequestParam String autor,
	        @RequestParam String isbn,
	        @RequestParam int quantidade,
	        @RequestParam("imagem") MultipartFile imagem) throws Exception {

	    DadosCadastroLivros dados = new DadosCadastroLivros(nome, autor, isbn, quantidade);

	    Livros livro = livrosService.cadastrar(dados, imagem);

	    return ResponseEntity.ok(new DadosDetalhamentoLivros(livro));
	}
	
	@GetMapping
	public ResponseEntity<List<DadosListagemLivros>> listar() {
		var lista = livrosService.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/estatisticas")
	public ResponseEntity<DadosEstatisticaLivros> estatisticas() {
	    var dados = livrosService.estatisticas();
	    return ResponseEntity.ok(dados);
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		livrosService.inativar(id);
		 return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/estatisticas/autor")
	public ResponseEntity<List<DadosEstatisticasPorAutor>> estatisticasPorAutor() {
	    return ResponseEntity.ok(livrosService.estatisticasPorAutor());
	}
	
	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> buscarImagem(@PathVariable Long id) {

	    Livros livro = livrosService.buscarPorId(id);

	    if (livro.getImagem() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok()
	            .header("Content-Type", "image/jpeg")
	            .body(livro.getImagem());
	}


}
