package com.proyect1.prueba.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyect1.prueba.entities.Usuario;
import com.proyect1.prueba.services.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> usuarios() {
		logger.info("call usuarios");
		
		List<Usuario> usuarios = usuarioService.findAll();
		logger.info("usuarios: " + usuarios);
		
		return usuarios;
	}
	
	
	@PostMapping("/usuarios")
	public Usuario crear( @RequestParam("nombre_usu") String nombre_usu,@RequestParam("correo_usu") String correo_usu,@RequestParam("password_usu") String password_usu) throws Exception {
		logger.info("call crear(" + nombre_usu + ", " + correo_usu + ", " + password_usu + ")");
		
		Usuario usuario = new Usuario();
		usuario.setNombre_usu(nombre_usu);
		usuario.setCorreo_usu(correo_usu);
		usuario.setPassword_usu(password_usu);		
		usuarioService.save(usuario);
		return usuario;
	}
	
	@DeleteMapping("/usuarios/{id_usu}")
	public ResponseEntity<String> eliminar(@PathVariable Long id_usu) {
		logger.info("call eliminar: " + id_usu);
		
		usuarioService.deleteById(id_usu);
		
		return ResponseEntity.ok().body("Registro eliminado");
	}
	
	@GetMapping("/usuarios/{id_usu}")
	public Usuario obtener(@PathVariable Long id_usu) {
		logger.info("call obtener: " + id_usu);
		
		Usuario usuarios = usuarioService.findById(id_usu);
		
		return usuarios;
	}
	

		private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
		
		
		@PostMapping("login")
		public Usuario login(@RequestParam String correo_usu, @RequestParam String password_usu) throws Exception{
			logger.info("login("+correo_usu+", "+password_usu+")");
			
			Usuario usuario = usuarioService.findByUsernameAndPassword(correo_usu, password_usu);
			logger.info("Login success: " + usuario);
			
			if(usuario == null)
				throw new Exception("Usuario y/o clave invalido");
			return usuario;
		}
	
}
