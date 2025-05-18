package com.tecjocs.apirest.apirest.Mappers;

import com.tecjocs.apirest.apirest.DTO.AlbumDTO;
import com.tecjocs.apirest.apirest.Entities.Album;
import com.tecjocs.apirest.apirest.Entities.ArtistaBanda;

public class AlbumMapper {

    public static AlbumDTO toDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setNombre(album.getNombre());
        dto.setFechaLanzamiento(album.getFechaLanzamiento());
        dto.setGenero(album.getGenero());
        dto.setPortadaUrl(album.getPortadaUrl());

        dto.setArtistaId(album.getArtista() != null ? album.getArtista().getId() : null);

        if (album.getCanciones() != null) {
            dto.setCanciones(
                album.getCanciones()
                     .stream()
                     .map(CancionMapper::toDTO)
                     .toList()
            );
        }

        return dto;
    }

    public static Album toEntity(AlbumDTO dto) {
        Album album = new Album();
        album.setId(dto.getId());
        album.setNombre(dto.getNombre());
        album.setFechaLanzamiento(dto.getFechaLanzamiento());
        album.setGenero(dto.getGenero());
        album.setPortadaUrl(dto.getPortadaUrl());

        if (dto.getArtistaId() != null) {
            ArtistaBanda artista = new ArtistaBanda();
            artista.setId(dto.getArtistaId());
            album.setArtista(artista);
        }

        // Las canciones no se mapean aquí.

        return album;
    }
}
