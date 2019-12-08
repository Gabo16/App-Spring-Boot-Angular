package com.springboot.clientes.apirest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.clientes.apirest.model.entity.Producto;

public interface IProductoService {
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);

}
