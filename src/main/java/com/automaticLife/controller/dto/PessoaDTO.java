package com.automaticLife.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO{
	
	private String nome;
	private String telefone;
	private Date dataNascimento;

}
