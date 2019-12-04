package com.proyect1.prueba.services;
import java.util.List;
import com.proyect1.prueba.entities.Usuario;
public interface UsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public void save(Usuario usuario);
}
