package com.tecjocs.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecjocs.apirest.apirest.Entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long>{

}
