package com.springboot.clientes.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.clientes.apirest.model.dto.FacturaDto;
import com.springboot.clientes.apirest.model.entity.Factura;
import com.springboot.clientes.apirest.model.entity.Producto;
import com.springboot.clientes.apirest.service.IFacturaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/v1/facturas")
public class FacturaRestController {
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("{id}")
	public ResponseEntity<?> getFactura(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Factura factura = null;
		try {
			factura = facturaService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "error encontrando la factura en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(factura == null) {
			response.put("message", "error encontrando la factura en la base de datos");
			response.put("error", "la factura no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.put("factura", mapper.map(factura, FacturaDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		if(!facturaService.existById(id)) {
			response.put("message", "error eliminando la factura en la base de datos");
			response.put("error", "la factura no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			facturaService.deleteById(id);	
		} catch (DataAccessException e) {
			response.put("message", "error eliminando la factura en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/filtrado-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term){
		List<Producto> productos = facturaService.findByNombreContainingIgnoreCase(term);
		return productos;
	}
	
	@Secured("ROLE_USER")
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody FacturaDto facturaDto) {
		Map<String, Object> response = new HashMap<>();
		Factura factura = null;
		try {
			factura = facturaService.save(mapper.map(facturaDto, Factura.class));
		} catch (DataAccessException e) {
			response.put("message", "error insertando la factura en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "factura insertada con exito en la base de datos");
		response.put("factura", mapper.map(factura, FacturaDto.class));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
