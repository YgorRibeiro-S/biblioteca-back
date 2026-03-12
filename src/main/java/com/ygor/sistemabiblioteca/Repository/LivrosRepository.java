package com.ygor.sistemabiblioteca.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ygor.sistemabiblioteca.Entity.Livros;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

	
	List<Livros> findAllByStatusTrue();
	
	long countByStatusTrue();

    long countByQuantidadeAndStatusTrue(int quantidade);

    @Query("""
           SELECT COALESCE(SUM(l.quantidade), 0)
           FROM Livros l
           WHERE l.status = true
           """)
    Long somaTotalExemplares();
    
    @Query("""
    	       SELECT l.autor, COUNT(l), SUM(l.quantidade)
    	       FROM Livros l
    	       WHERE l.status = true
    	       GROUP BY l.autor
    	       """)
    	List<Object[]> buscarEstatisticasPorAutor();
}
