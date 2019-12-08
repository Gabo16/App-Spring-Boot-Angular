package com.springboot.clientes.apirest.model.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleDto{

	private Long id;
	private String nombre;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
