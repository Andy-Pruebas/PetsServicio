package com.proyect1.prueba.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyect1.prueba.entities.Mascota;
import com.proyect1.prueba.services.MascotaService;

@RestController
@RequestMapping("/api")
public class MascotaController {
	private static final Logger logger = LoggerFactory.getLogger(MascotaController.class);

	@Value("${app.storage.path}")
	private String STORAGEPATH;

	@Autowired
	private MascotaService mascotaservice;

	@GetMapping("/mascotas")
	public List<Mascota> mascotas() {
		logger.info("call mascotas");
		List<Mascota> mascotas = mascotaservice.findAll();
		logger.info("mascotas" + mascotas);
		return mascotas;
	}

	@GetMapping("/mascotas/images/{filename:.+}")
	public ResponseEntity<Resource> files(@PathVariable String filename) throws Exception {
		logger.info("call images: " + filename);
		Path path = Paths.get(STORAGEPATH).resolve(filename);
		logger.info("Path: " + path);
		if (!Files.exists(path)) {
			return ResponseEntity.notFound().build();
		}

		Resource resource = new UrlResource(path.toUri());
		logger.info("Resource: " + resource);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength())).body(resource);
	}

	@PostMapping("/mascotas")
	public Mascota crear(@RequestParam(name = "foto", required = false) MultipartFile foto,
			@RequestParam("nombre") String nombre, @RequestParam("raza") String raza, @RequestParam("edad") String edad)
			throws Exception {
		logger.info("call crear (" + nombre + "," + raza + "," + edad + "," + foto + ")");
		Mascota mascota = new Mascota();
		mascota.setNombre(nombre);
		mascota.setRaza(raza);
		mascota.setEdad(edad);
		if (foto != null && !foto.isEmpty()) {
			String filename = System.currentTimeMillis()
					+ foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf("."));
			mascota.setFoto(filename);
			if (Files.notExists(Paths.get(STORAGEPATH))) {
				Files.createDirectories(Paths.get(STORAGEPATH));
			}
			Files.copy(foto.getInputStream(), Paths.get(STORAGEPATH).resolve(filename));
		}
		mascotaservice.save(mascota);
		return mascota;
	}
	@GetMapping("/mascotas/{id}")
	public Mascota obtener(@PathVariable Long id) {
		logger.info("call obtener: " + id);
		
		Mascota mascota = mascotaservice.findById(id);
		
		return mascota;
	}
}
