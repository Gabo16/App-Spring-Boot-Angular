package com.springboot.clientes.apirest.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioDto {

	private Long id;
	private String username;
	private String email;
	private String nombre;
	private String apellido;
	private Boolean enabled = true;

	private List<RoleDto> roles;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
