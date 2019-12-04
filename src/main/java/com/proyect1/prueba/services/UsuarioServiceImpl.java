package com.proyect1.prueba.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect1.prueba.entities.Usuario;
import com.proyect1.prueba.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuariorepository;
	@Override
	public List<Usuario> findAll() {
		return usuariorepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuariorepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
	}

	@Override
	public void save(Usuario usuario) {
		usuariorepository.save(usuario);
	}

}
