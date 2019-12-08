package com.springboot.clientes.apirest.model.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacturaDto {

	private Long id;
	private String descripcion;
	private String observacion;
	@JsonIgnoreProperties({ "facturas" })
	private ClienteDto cliente;
	private List<ItemFacturaDto> items;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Double getTotal() {
		Double total = 0.00;
		for (ItemFacturaDto item : items) {
			total += item.getImporte();
		}
		return total;
	}

}
