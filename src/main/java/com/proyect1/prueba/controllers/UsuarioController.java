package com.proyect1.prueba.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyect1.prueba.entities.Usuario;
import com.proyect1.prueba.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	UsuarioRepository usuariorepository;
	
	@GetMapping("/usuarios")
	public List<Usuario> findall() {
		return usuariorepository.findAll();
	}
	@PostMapping("/usuarios")
	public Usuario registrar(@RequestParam(name="nombres") String nombres,@RequestParam(name="correo") String correo,@RequestParam(name="contraseña")String contraseña)throws Exception {
		Usuario usuario=new Usuario();
		usuario.setNombres(nombres);
		usuario.setCorreo(correo);
		usuario.setContraseña(contraseña);
		return usuariorepository.save(usuario);
	}
}
