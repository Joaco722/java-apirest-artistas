package com.tecjocs.apirest.apirest.Mappers;

import com.tecjocs.apirest.apirest.DTO.CancionDTO;
import com.tecjocs.apirest.apirest.Entities.Album;
import com.tecjocs.apirest.apirest.Entities.Cancion;

public class CancionMapper {

    public static CancionDTO toDTO(Cancion cancion) {
        CancionDTO dto = new CancionDTO();
        dto.setId(cancion.getId());
        dto.setNombre(cancion.getNombre());
        dto.setDuracion(cancion.getDuracion());
        dto.setTrackNumber(cancion.getTrackNumber());

        if (cancion.getAlbum() != null) {
            dto.setAlbumId(cancion.getAlbum().getId());
        }

        return dto;
    }

    public static Cancion toEntity(CancionDTO dto) {
        Cancion cancion = new Cancion();
        cancion.setId(dto.getId());
        cancion.setNombre(dto.getNombre());
        cancion.setDuracion(dto.getDuracion());
        cancion.setTrackNumber(dto.getTrackNumber());

        if (dto.getAlbumId() != null) {
            Album album = new Album();
            album.setId(dto.getAlbumId());
            cancion.setAlbum(album);
        }

        return cancion;
    }
}

