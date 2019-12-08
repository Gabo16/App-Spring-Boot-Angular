package com.springboot.clientes.apirest.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.clientes.apirest.service.IUploadFileService;

@Service
public class UploaadFileServiceImpl implements IUploadFileService {

	private final static String DIRECTORIO_UPLOADS = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("uploads").resolve("noimage.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString().concat("_").concat(archivo.getOriginalFilename())
				.replace(" ", "_");
		Path rutaArchivo = getPath(nombreArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if(!nombreFoto.contains("noimage.png")) {
			Path rutaFotoAnteriot = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnteriot.toFile();
			archivoFotoAnterior.delete();
			return true;
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOADS).resolve(nombreFoto).toAbsolutePath();
	}

}
