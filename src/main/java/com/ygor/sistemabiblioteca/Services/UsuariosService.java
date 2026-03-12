package com.ygor.sistemabiblioteca.Services;

import com.ygor.sistemabiblioteca.Entity.Usuarios;
import com.ygor.sistemabiblioteca.Dto.DadosCadastroUsuario;
import com.ygor.sistemabiblioteca.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    
    public String cadastrarUsuario(DadosCadastroUsuario dados) {
        Usuarios usuario = new Usuarios(dados.nome(), dados.email());
        usuariosRepository.save(usuario);
        return "Usuário cadastrado com sucesso: " + usuario.getNome();
    }

   
    public List<Usuarios> listarUsuarios() {
        return usuariosRepository.findAll();
    }
}