package com.automaticLife.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {

	@NotBlank(message = "name")
	private String name;

	@NotBlank(message = "phoneNumber")
	private String phoneNumer;

	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;
}
