package com.proyect1.prueba.services;

import java.util.List;
import com.proyect1.prueba.entities.Mascota;

public interface MascotaService {
	public List<Mascota> findAll();

	public Mascota findById(Long id);

	public void save(Mascota mascota);

	public void deleteById(Long id);
}
