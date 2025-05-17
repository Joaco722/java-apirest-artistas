package com.tecjocs.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecjocs.apirest.apirest.Repositories.ArtistaBandaRepository;
import com.tecjocs.apirest.apirest.Entities.ArtistaBanda;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


;


@RestController
@RequestMapping("/artistas")
public class ArtistaBandaController {

    @Autowired
    private ArtistaBandaRepository artistaBandaRepository;

    @GetMapping  
    public List<ArtistaBanda> getAllArtistas(){
        return artistaBandaRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ArtistaBanda getArtista(@PathVariable Long id){
        return artistaBandaRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el artista de ID: " + id ));
    }

    @PostMapping
    public ArtistaBanda crearArtista(@RequestBody ArtistaBanda artistaBanda) {   
        return artistaBandaRepository.save(artistaBanda);
    }

    @PutMapping("/{id}")
    public ArtistaBanda actualizarArtista(@PathVariable Long id, @RequestBody ArtistaBanda artista) {
        ArtistaBanda artistaBanda = artistaBandaRepository.findById(id).orElseThrow(()->new RuntimeException("No se encuentra el artista de ID: "+id));
        artistaBanda.setNombre(artista.getNombre());
        artistaBanda.setTipo(artista.getTipo());
        artistaBanda.setDescripción(artista.getDescripción());
        artistaBanda.setPaísOrigen(artista.getPaísOrigen());
        artistaBanda.setImagenUrl(artista.getImagenUrl());
        return artistaBandaRepository.save(artistaBanda);
    }

    @DeleteMapping("/{id}")
    public String borrarArtista(@PathVariable Long id){
        ArtistaBanda artistaBanda = artistaBandaRepository.findById(id).orElseThrow(()->new RuntimeException("No se encuentra el artista de ID: "+id));

        artistaBandaRepository.delete(artistaBanda);
        return "El artista de ID: " + id + " fue eliminado correctamente";
    }
    

}
