package com.proyect1.prueba.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect1.prueba.entities.Usuario;
import com.proyect1.prueba.repository.UsuarioRepository;

@Service
@Transactional

public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByUsernameAndPassword(String correo_usu, String password_usu) {
		return usuarioRepository.findByUsernameAndPassword(correo_usu, password_usu);
	}

	@Override
	public Usuario findByUsername(String correo_usu) {
		return usuarioRepository.findByUsername(correo_usu);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
	}

	@Override
	public void save(Usuario usuarios) {
		usuarioRepository.save(usuarios);
		
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
		
	}

}
