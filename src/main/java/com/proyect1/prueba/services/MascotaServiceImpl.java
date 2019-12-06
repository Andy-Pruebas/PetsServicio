package com.proyect1.prueba.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect1.prueba.entities.Mascota;
import com.proyect1.prueba.repository.MascotaRepository;

@Service
@Transactional
public class MascotaServiceImpl implements MascotaService{
	
	@Autowired
	private MascotaRepository mascotarepository;
	
	@Override
	public List<Mascota> findAll() {
		return mascotarepository.findAll();
	}

	@Override
	public Mascota findById(Long id) {
		return mascotarepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
	}

	@Override
	public void save(Mascota mascota) {
		mascotarepository.save(mascota);
	}

	@Override
	public void deleteById(Long id) {
		mascotarepository.deleteById(id);
	}

}
