package com.ygor.sistemabiblioteca.Controllers;

import org.springframework.web.bind.annotation.*;

import com.ygor.sistemabiblioteca.Services.EmprestimoService;

@RestController
@RequestMapping("/livros")
public class EmprestimosController {

    private final EmprestimoService emprestimoService;

    public EmprestimosController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/emprestar/{livroId}")
    public String emprestar(@PathVariable long livroId, @RequestParam long usuarioId) {
        return emprestimoService.emprestarLivro(livroId, usuarioId);
    }

    @PostMapping("/devolver/{livroId}")
    public String devolver(@PathVariable long livroId) {
        return emprestimoService.devolverLivro(livroId);
    }
}