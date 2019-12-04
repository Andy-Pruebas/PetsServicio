package com.proyect1.prueba.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.proyect1.prueba.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	List<Usuario> findAll();
}
