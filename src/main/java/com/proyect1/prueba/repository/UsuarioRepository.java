package com.proyect1.prueba.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proyect1.prueba.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.correo_usu=:correo_usu AND u.password_usu=:password_usu")
	Usuario findByUsernameAndPassword(@Param("correo_usu") String correo_usu, @Param("password_usu") String password_usu);

	@Query("SELECT u FROM Usuario u WHERE u.correo_usu=:correo_usu")
	Usuario findByUsername(@Param("correo_usu") String correo_usu);

}
