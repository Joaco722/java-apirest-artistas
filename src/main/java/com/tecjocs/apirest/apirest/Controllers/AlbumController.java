package com.tecjocs.apirest.apirest.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tecjocs.apirest.apirest.Repositories.AlbumRepository;
import com.tecjocs.apirest.apirest.DTO.AlbumDTO;
import com.tecjocs.apirest.apirest.Entities.Album;
import com.tecjocs.apirest.apirest.Mappers.AlbumMapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/albumes")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<AlbumDTO> obtenerTodos() {
        return albumRepository.findAll().stream()
            .map(AlbumMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> obtenerPorId(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album de id: " +id+" no encontrado"));
        return ResponseEntity.ok(AlbumMapper.toDTO(album));
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> crear(@RequestBody AlbumDTO dto) {
        Album album = AlbumMapper.toEntity(dto);
        Album guardado = albumRepository.save(album);
        return new ResponseEntity<>(AlbumMapper.toDTO(guardado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> actualizar(@PathVariable Long id, @RequestBody AlbumDTO dto) {
        Album existente = albumRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album de id: " +id+" no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setFechaLanzamiento(dto.getFechaLanzamiento());
        existente.setGenero(dto.getGenero());
        existente.setPortadaUrl(dto.getPortadaUrl());

        Album actualizado = albumRepository.save(existente);
        return ResponseEntity.ok(AlbumMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album de id: " +id+" no encontrado"));
        albumRepository.delete(album);
        return ResponseEntity.ok("Album eliminado correctamente");
    }

    @GetMapping("/por-artista/{artistaId}")
    public ResponseEntity<List<AlbumDTO>> obtenerAlbumsPorArtista(@PathVariable Long artistaId) {
    List<Album> albums = albumRepository.findByArtistaId(artistaId);
    List<AlbumDTO> albumDTOs = albums.stream()
        .map(AlbumMapper::toDTO)
        .collect(Collectors.toList());

    return ResponseEntity.ok(albumDTOs);
}

}

