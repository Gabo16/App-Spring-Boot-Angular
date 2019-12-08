package com.springboot.clientes.apirest.model.dto;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFacturaDto {
	
	private Long id;
	private Integer cantidad;
	private ProductoDto producto;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Double getImporte() {
		return this.cantidad.doubleValue()*producto.getPrecio();
	}

}
