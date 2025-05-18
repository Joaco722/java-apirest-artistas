package com.tecjocs.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecjocs.apirest.apirest.Entities.Album;

public interface AlbumRepository extends JpaRepository<Album,Long> {

    List<Album> findByArtistaBandaId(Long artistaId);

}
