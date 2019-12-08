package com.springboot.clientes.apirest.model.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioCreateDto {

	private Long id;
	@Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "username solo puede tener: letras, números, guiones y guiones bajos")
	@Size(min = 6, message = "username debe tener almenos 6 caracteres")
	private String username;
	@Size(min = 8, message = "password debe tener almenos 8 caracteres")
	private String password;
	@Pattern(regexp = "^[A-Za-z ]*$", message = "nombre solo puede tener: letras")
	@NotEmpty(message = "nombre es requerido")
	private String nombre;
	@Pattern(regexp = "^[A-Za-z ]*$", message = "apellido solo puede tener: letras")
	@NotEmpty(message = "apellido es requerido")
	private String apellido;
	@NotEmpty(message = "email es requerido")
	@Email(message = "email debe ser una dirección valida")
	private String email;
	private Boolean enabled = true;

	private List<RoleDto> roles;

}
