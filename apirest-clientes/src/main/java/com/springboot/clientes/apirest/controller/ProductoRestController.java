package com.springboot.clientes.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.clientes.apirest.model.dto.ProductoDto;
import com.springboot.clientes.apirest.model.entity.Producto;
import com.springboot.clientes.apirest.service.IProductoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api/v1/productos")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ProductoDto productoDto, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Producto producto = null;
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			producto = productoService.save(mapper.map(productoDto, Producto.class));
		} catch (DataAccessException e) {
			response.put("message", "error insertando el producto en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "producto insertando con exito en la base de datos");
		response.put("producto", mapper.map(producto, ProductoDto.class));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getProductos(@RequestParam(required = false, defaultValue = "0") int page){
		if(page > 0) --page;
		Page<Producto> pProductos = productoService.findAll(PageRequest.of(page, 10));
		return new ResponseEntity<>(pProductos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProducto(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Producto producto = null;
		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "error encontrando el producto en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			response.put("message", "error encontrando el producto en la base de datos");
			response.put("error", "el producto no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("message", "producto encontrado con exito en la base de datos");
		response.put("producto", mapper.map(producto, ProductoDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductoDto productoDto, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Producto producto = productoService.findById(id);
		if (producto == null) {
			response.put("message", "error al consultar producto en la base de datos");
			response.put("error", "el producto no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			producto.setNombre(productoDto.getNombre());
			producto.setPrecio(productoDto.getPrecio());
			producto = productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("message", "error encontrando el producto en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "producto actualizado con exito en la base de datos");
		response.put("producto", mapper.map(producto, ProductoDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if (producto == null) {
			response.put("message", "error eliminando el producto en la base de datos");
			response.put("error", "el producto no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			productoService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("message", "error eliminando el producto en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
