package com.proyect1.prueba.services;

import java.util.List;
import com.proyect1.prueba.entities.Usuario;

public interface UsuarioService {

	Usuario findByUsernameAndPassword(String correo_usu, String password_usu);

	Usuario findByUsername(String correo_usu);

	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public void save(Usuario usuarios);

	public void deleteById(Long id);
}
