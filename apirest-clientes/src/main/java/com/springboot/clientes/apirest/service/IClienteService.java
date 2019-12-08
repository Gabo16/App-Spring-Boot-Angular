package com.springboot.clientes.apirest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.clientes.apirest.model.entity.Cliente;

public interface IClienteService {
	
	public Cliente save(Cliente c);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public void deleteById(Long id);
	
	public Boolean existById(Long id);

}
