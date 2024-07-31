package com.automaticLife.Classes;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String telefone;
	private Date dataNascimento;
	
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String telefone, Date dataNascimento) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}
	
	public Pessoa(int id, String nome, String telefone, Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
