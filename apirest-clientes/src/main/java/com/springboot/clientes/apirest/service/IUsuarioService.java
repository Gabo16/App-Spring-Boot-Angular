package com.springboot.clientes.apirest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.clientes.apirest.model.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);

}
