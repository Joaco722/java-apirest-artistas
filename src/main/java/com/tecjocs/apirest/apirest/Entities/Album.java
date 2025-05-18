package com.tecjocs.apirest.apirest.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Album {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaLanzamiento;
    private String genero;
    private String portadaUrl;

    @ManyToOne
    private ArtistaBanda artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @JsonManagedReference 
    private List<Cancion> canciones = new ArrayList<>();
}

