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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.clientes.apirest.model.dto.UsuarioCreateDto;
import com.springboot.clientes.apirest.model.dto.UsuarioDto;
import com.springboot.clientes.apirest.model.entity.Role;
import com.springboot.clientes.apirest.model.entity.Usuario;
import com.springboot.clientes.apirest.service.IUsuarioService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api/v1/usuarios")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;
		List<Role> roles = usuarioCreateDto.getRoles().stream().map(r -> mapper.map(r, Role.class)).collect(Collectors.toList());
		usuarioCreateDto.setRoles(null);
		String passwordBcrypt = passwordEncoder.encode(usuarioCreateDto.getPassword());
		usuarioCreateDto.setPassword(passwordBcrypt);
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuario = usuarioService.save(mapper.map(usuarioCreateDto, Usuario.class));
			usuario.setRoles(roles); 
			usuario = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("message", "error insertando el usuario en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "usuario insertado en la base de datos con exito");
		response.put("usuario", mapper.map(usuario, UsuarioDto.class));
		return  new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<?> getUsuarios(@RequestParam(required = false, defaultValue = "0") int page){
		Map<String, Object> response = new HashMap<>();
		if(page > 0) --page;
		Page<Usuario> pUsuarios = usuarioService.findAll(PageRequest.of(page, 5));
		
		if(pUsuarios.getContent().size() > 0) {
			List<Usuario> usuarios = pUsuarios.getContent().stream().map(u -> {
				u.setPassword(null);
				return u;
			}).collect(Collectors.toList());
			response.put("content", usuarios);
			response.put("pageable", pUsuarios.getPageable());
			response.put("totalPages", pUsuarios.getTotalPages());
			response.put("totalElements", pUsuarios.getTotalElements());
			response.put("last", pUsuarios.isLast());
			response.put("size", pUsuarios.getSize());
			response.put("number", pUsuarios.getNumber());
			response.put("sort", pUsuarios.getSort());
			response.put("numberOfElements", pUsuarios.getNumberOfElements());
			response.put("first", pUsuarios.isFirst());
			response.put("empty", pUsuarios.isEmpty());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(pUsuarios, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;
		try {
			usuario = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "error encontrando el usuario en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(usuario == null) {
			response.put("message", "error encontrando el usuario en la base de datos");
			response.put("error", "el usuario no se encuentra en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("message", "el usuario ha sido encontrado con exito");
		response.put("usuario", mapper.map(usuario, UsuarioDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UsuarioCreateDto usuarioDto, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(id);
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		List<Role> roles  = usuarioDto.getRoles().stream().map(r -> mapper.map(r, Role.class)).collect(Collectors.toList());
		if(usuario == null) {
			response.put("message", "error encontrando el usuario en la base de datos");
			response.put("error", "el usuario no se encuentra en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuario.setNombre(usuarioDto.getNombre());
			usuario.setApellido(usuarioDto.getApellido());
			usuario.setEmail(usuarioDto.getEmail());
			usuario.setUsername(usuarioDto.getUsername());
			usuario.setRoles(roles);
			usuario = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("message", "error encontrando el usuario en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "usuario actualizadon con exito");
		response.put("usuario", mapper.map(usuario, UsuarioDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
