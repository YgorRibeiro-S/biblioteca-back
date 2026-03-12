package com.ygor.sistemabiblioteca.Repository;

import com.ygor.sistemabiblioteca.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {}