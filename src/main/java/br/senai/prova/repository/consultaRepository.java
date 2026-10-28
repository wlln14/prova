package br.senai.prova.repository;

import br.senai.prova.model.consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface consultaRepository extends JpaRepository<consulta, Long> {
}
