package com.springboot.clientes.apirest.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClienteDto {
	
	private Long id;
	@NotEmpty(message = "Nombre del cliente es requerido")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Nombre solo debe contener letras")
	@Size(min = 3, message = "Nombre del cliente debe tener al menos 3 caracteres")
	private String nombre;
	@NotEmpty(message = "Apellido del cliente es requerido")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Apellido solo debe contener letras")
	@Size(min = 3, message = "Apellido del cliente debe tener al menos 3 caracteres")
	private String apellido;
	@NotEmpty(message = "Email del cliente es requerido")
	@Email(message = "Dirección de email no válida")
	private String email;
	private String foto;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@JsonIgnoreProperties({"cliente"})
	private List<FacturaDto> facturas;

}
