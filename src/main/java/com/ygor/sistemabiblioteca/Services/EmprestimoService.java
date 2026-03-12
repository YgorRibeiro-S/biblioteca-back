package com.ygor.sistemabiblioteca.Services;

import com.ygor.sistemabiblioteca.Entity.Livros;
import com.ygor.sistemabiblioteca.Entity.Usuarios;
import com.ygor.sistemabiblioteca.Repository.LivrosRepository;
import com.ygor.sistemabiblioteca.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    private final LivrosRepository livrosRepository;
    private final UsuariosRepository usuariosRepository;

    public EmprestimoService(LivrosRepository livrosRepository, UsuariosRepository usuariosRepository) {
        this.livrosRepository = livrosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public String emprestarLivro(long livroId, long usuarioId) {
        Livros livro = livrosRepository.findById(livroId).orElse(null);
        if (livro == null) return "Livro não encontrado";

        Usuarios usuario = usuariosRepository.findById(usuarioId).orElse(null);
        if (usuario == null) return "Usuário não encontrado";

        if (!livro.emprestarPara(usuario)) return "Livro indisponível para empréstimo";

        livrosRepository.save(livro);
        return "Empréstimo realizado com sucesso para " + usuario.getNome();
    }

    @Transactional
    public String devolverLivro(long livroId) {
        Livros livro = livrosRepository.findById(livroId).orElse(null);
        if (livro == null) return "Livro não encontrado";

        livro.devolver();
        livrosRepository.save(livro);
        return "Livro devolvido com sucesso!";
    }
}