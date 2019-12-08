package com.springboot.clientes.apirest.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto {
	
	private Long id;
	@NotEmpty(message = "nombre del producto es requerido")
	private String nombre;
	@NotNull(message = "precio del producto es requerido")
	private Double precio;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
