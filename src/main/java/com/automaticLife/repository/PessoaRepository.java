package com.automaticLife.repository;

import org.springframework.stereotype.Repository;

import com.automaticLife.repository.entity.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query(value = "SELECT * FROM pessoa p WHERE DAY(p.data_nascimento) = :dia AND MONTH(p.data_nascimento) = :mes", nativeQuery = true)
	List<Pessoa> buscarAniversariantesDia(@Param("mes") String mes, @Param("dia") String dia);
}