package com.proyect1.prueba.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.proyect1.prueba.entities.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {

	@Override
	List<Mascota> findAll();
}
