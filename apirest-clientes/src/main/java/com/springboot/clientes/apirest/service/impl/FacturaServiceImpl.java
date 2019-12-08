package com.springboot.clientes.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.clientes.apirest.model.dao.IFacturaDao;
import com.springboot.clientes.apirest.model.dao.IProductoDao;
import com.springboot.clientes.apirest.model.entity.Factura;
import com.springboot.clientes.apirest.model.entity.Producto;
import com.springboot.clientes.apirest.service.IFacturaService;

@Service
public class FacturaServiceImpl implements IFacturaService{
	
	@Autowired
	private IFacturaDao facturaDao;

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public Factura save(Factura f) {
		return facturaDao.save(f);
	}

	@Override
	public List<Factura> findAll() {
		return facturaDao.findAll();
	}

	@Override
	public Factura findById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	public Boolean existById(Long id) {
		return facturaDao.existsById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombreContainingIgnoreCase(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	}

}
