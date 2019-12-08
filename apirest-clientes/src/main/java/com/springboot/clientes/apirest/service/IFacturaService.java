package com.springboot.clientes.apirest.service;

import java.util.List;

import com.springboot.clientes.apirest.model.entity.Factura;
import com.springboot.clientes.apirest.model.entity.Producto;

public interface IFacturaService {
	
	public Factura save(Factura f);
	
	public List<Factura> findAll();
	
	public Factura findById(Long id);
	
	public void deleteById(Long id);
	
	public Boolean existById(Long id);
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);

}
