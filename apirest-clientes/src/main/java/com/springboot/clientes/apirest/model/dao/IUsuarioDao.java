package com.springboot.clientes.apirest.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.clientes.apirest.model.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

}
