package com.springboot.clientes.apirest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.springboot.clientes.apirest.model.dto.ClienteDto;
import com.springboot.clientes.apirest.model.entity.Cliente;
import com.springboot.clientes.apirest.service.IClienteService;
import com.springboot.clientes.apirest.service.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IUploadFileService uploadService;

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteDto c, BindingResult result) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			cliente = clienteService.save(mapper.map(c, Cliente.class));
		} catch (DataAccessException e) {
			response.put("message", "error al insertar cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "el cliente ha sido creado con exito!");
		response.put("cliente", mapper.map(cliente, ClienteDto.class));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_USER")
	@GetMapping
	public List<ClienteDto> getClientes() {
		List<ClienteDto> cDto = clienteService.findAll().stream().map(c -> mapper.map(c, ClienteDto.class))
				.collect(Collectors.toList());
		return cDto;
	}

	@Secured("ROLE_USER")
	@GetMapping("/page/{page}")
	public ResponseEntity<?> getClientes(@PathVariable int page) {
		Page<Cliente> pClientes = clienteService.findAll(PageRequest.of(page, 5));
		List<ClienteDto> clientes = pClientes.getContent().stream().map(c -> mapper.map(c, ClienteDto.class))
				.collect(Collectors.toList());
		Map<String, Object> response = new HashMap<>();
		response.put("content", clientes);
		response.put("pageable", pClientes.getPageable());
		response.put("totalPages", pClientes.getTotalPages());
		response.put("totalElements", pClientes.getTotalElements());
		response.put("last", pClientes.isLast());
		response.put("size", pClientes.getSize());
		response.put("number", pClientes.getNumber());
		response.put("sort", pClientes.getSort());
		response.put("numberOfElements", pClientes.getNumberOfElements());
		response.put("first", pClientes.isFirst());
		response.put("empty", pClientes.isEmpty());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("cliente/{id}")
	public ResponseEntity<?> getCliente(@PathVariable(name = "id") Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "error al consultar cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("message", "error al consultar cliente en la base de datos");
			response.put("error", "el cliente no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.put("cliente", mapper.map(cliente, ClienteDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCliente(@Valid @RequestBody ClienteDto c, BindingResult result,
			@PathVariable(name = "id") Long id) {
		Cliente clienteFound = clienteService.findById(id);
		Cliente clienteUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (clienteFound == null) {
			response.put("message", "el cliente no existe en la base de datos");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteFound.setNombre(c.getNombre());
			clienteFound.setApellido(c.getApellido());
			clienteFound.setEmail(c.getEmail());
			clienteUpdated = clienteService.save(clienteFound);
		} catch (DataAccessException e) {
			response.put("message", "error al actualizar cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "el cliente ha sido actualizado con exito!");
		response.put("cliente", mapper.map(clienteUpdated, ClienteDto.class));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable(name = "id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (!clienteService.existById(id)) {
				response.put("message", "el cliente no existe en la base de datos");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			Cliente c = clienteService.findById(id);
			uploadService.eliminar(c.getFoto());
			clienteService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("messsage", "error eliminando cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/uploads")
	public ResponseEntity<?> uploadImage(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente c = clienteService.findById(id);
		String fotoAnterior = c.getFoto();
		String nombreArchivo = null;
		if (!archivo.isEmpty()) {
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("messsage", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			uploadService.eliminar(fotoAnterior);
			c.setFoto(nombreArchivo);
			clienteService.save(c);
			response.put("cliente", mapper.map(c, ClienteDto.class));
			response.put("message", "Has subido exitosamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
