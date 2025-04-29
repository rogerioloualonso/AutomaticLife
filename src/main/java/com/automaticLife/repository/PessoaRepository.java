package com.automaticLife.repository;

import org.springframework.stereotype.Repository;

import com.automaticLife.repository.entity.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, PagingAndSortingRepository<Pessoa, Integer> {

}