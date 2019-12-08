package com.springboot.clientes.apirest.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.clientes.apirest.model.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
