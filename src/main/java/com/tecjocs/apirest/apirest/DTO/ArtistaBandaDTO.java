package com.tecjocs.apirest.apirest.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtistaBandaDTO {
    private Long id;
    private String nombre;
    private String tipo; // como texto: "SOLISTA" o "BANDA"
    private String paisOrigen;
    private String descripcion;
    private String imagenUrl;
}

