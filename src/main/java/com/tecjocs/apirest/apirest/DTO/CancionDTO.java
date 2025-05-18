package com.tecjocs.apirest.apirest.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CancionDTO {
    private Long id;
    private String nombre;
    private String duracion;
    private Integer trackNumber;
}
