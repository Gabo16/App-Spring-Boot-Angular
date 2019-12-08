package com.springboot.clientes.apirest.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.clientes.apirest.model.entity.Factura;

public interface IFacturaDao extends JpaRepository<Factura, Long> {

}
