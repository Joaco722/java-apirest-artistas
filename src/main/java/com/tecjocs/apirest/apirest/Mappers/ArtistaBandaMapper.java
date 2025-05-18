package com.tecjocs.apirest.apirest.Mappers;

import com.tecjocs.apirest.apirest.DTO.ArtistaBandaDTO;
import com.tecjocs.apirest.apirest.Entities.ArtistaBanda;
import com.tecjocs.apirest.apirest.Entities.TipoArtista;

public class ArtistaBandaMapper {

    public static ArtistaBandaDTO toDTO(ArtistaBanda artista) {
        ArtistaBandaDTO dto = new ArtistaBandaDTO();
        dto.setId(artista.getId());
        dto.setNombre(artista.getNombre());
        dto.setTipo(artista.getTipo().name()); // Enum a String
        dto.setPaisOrigen(artista.getPaisOrigen());
        dto.setDescripcion(artista.getDescripcion());
        dto.setImagenUrl(artista.getImagenUrl());
        return dto;
    }

    public static ArtistaBanda toEntity(ArtistaBandaDTO dto) {
        ArtistaBanda artista = new ArtistaBanda();
        artista.setId(dto.getId());
        artista.setNombre(dto.getNombre());
        artista.setTipo(TipoArtista.valueOf(dto.getTipo())); // String a Enum
        artista.setPaisOrigen(dto.getPaisOrigen());
        artista.setDescripcion(dto.getDescripcion());
        artista.setImagenUrl(dto.getImagenUrl());
        return artista;
    }
}
