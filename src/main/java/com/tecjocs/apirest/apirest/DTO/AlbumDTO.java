package com.tecjocs.apirest.apirest.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlbumDTO {
    private Long id;
    private String nombre;
    private String fechaLanzamiento;
    private String genero;
    private String portadaUrl;
    private Long artistaId;
    private List<CancionDTO> canciones;
}

