package com.springboot.clientes.apirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.clientes.apirest.model.dao.IProductoDao;
import com.springboot.clientes.apirest.model.entity.Producto;
import com.springboot.clientes.apirest.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	@Override
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		productoDao.deleteById(id);
		
	}
	
	

}
