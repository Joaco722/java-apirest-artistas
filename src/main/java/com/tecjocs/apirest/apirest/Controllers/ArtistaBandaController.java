package com.tecjocs.apirest.apirest.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tecjocs.apirest.apirest.Repositories.ArtistaBandaRepository;
import com.tecjocs.apirest.apirest.DTO.ArtistaBandaDTO;
import com.tecjocs.apirest.apirest.Entities.ArtistaBanda;
import com.tecjocs.apirest.apirest.Entities.TipoArtista;
import com.tecjocs.apirest.apirest.Mappers.ArtistaBandaMapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/artistas")
public class ArtistaBandaController {

    @Autowired
    private ArtistaBandaRepository artistaBandaRepository;

    @GetMapping
    public List<ArtistaBandaDTO> obtenerTodos() {
        return artistaBandaRepository.findAll().stream()
            .map(ArtistaBandaMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaBandaDTO> obtenerPorId(@PathVariable Long id) {
        ArtistaBanda artista = artistaBandaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista no encontrado"));
        return ResponseEntity.ok(ArtistaBandaMapper.toDTO(artista));
    }

    @PostMapping
    public ResponseEntity<ArtistaBandaDTO> crear(@RequestBody ArtistaBandaDTO dto) {
        ArtistaBanda artista = ArtistaBandaMapper.toEntity(dto);
        ArtistaBanda guardado = artistaBandaRepository.save(artista);
        return new ResponseEntity<>(ArtistaBandaMapper.toDTO(guardado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaBandaDTO> actualizar(@PathVariable Long id, @RequestBody ArtistaBandaDTO dto) {
        ArtistaBanda existente = artistaBandaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setTipo(TipoArtista.valueOf(dto.getTipo()));
        existente.setPaisOrigen(dto.getPaisOrigen());
        existente.setDescripcion(dto.getDescripcion());
        existente.setImagenUrl(dto.getImagenUrl());

        ArtistaBanda actualizado = artistaBandaRepository.save(existente);
        return ResponseEntity.ok(ArtistaBandaMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        ArtistaBanda artista = artistaBandaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista no encontrado"));
        artistaBandaRepository.delete(artista);
        return ResponseEntity.ok("Artista eliminado correctamente");
    }
}

