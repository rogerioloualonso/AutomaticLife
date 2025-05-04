package com.automaticLife.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String telefone;
	private LocalDateTime dataNascimento;
	

	public Pessoa(String nome, String telefone, LocalDateTime dataNascimento) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}
	
}
