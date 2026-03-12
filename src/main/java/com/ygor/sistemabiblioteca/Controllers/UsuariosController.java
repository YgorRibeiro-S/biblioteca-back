package com.ygor.sistemabiblioteca.Controllers;

import com.ygor.sistemabiblioteca.Dto.DadosCadastroUsuario;
import com.ygor.sistemabiblioteca.Entity.Usuarios;
import com.ygor.sistemabiblioteca.Services.UsuariosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody DadosCadastroUsuario dados) {
        return usuariosService.cadastrarUsuario(dados);
    }

    
    @GetMapping("/listar")
    public List<Usuarios> listar() {
        return usuariosService.listarUsuarios();
    }
}