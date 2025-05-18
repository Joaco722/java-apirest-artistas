package com.tecjocs.apirest.apirest.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tecjocs.apirest.apirest.DTO.CancionDTO;
import com.tecjocs.apirest.apirest.Entities.Cancion;
import com.tecjocs.apirest.apirest.Mappers.CancionMapper;
import com.tecjocs.apirest.apirest.Repositories.CancionRepository;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping
    public List<CancionDTO> obtenerTodos() {
        return cancionRepository.findAll().stream()
            .map(CancionMapper::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CancionDTO> obtenerPorId(@PathVariable Long id) {
        Cancion album = cancionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Canción de id: " +id+" no encontrado"));
        return ResponseEntity.ok(CancionMapper.toDTO(album));
    }

    @PostMapping
    public ResponseEntity<CancionDTO> crear(@RequestBody CancionDTO dto) {
        Cancion cancion = CancionMapper.toEntity(dto);
        Cancion guardado = cancionRepository.save(cancion);
        return new ResponseEntity<>(CancionMapper.toDTO(guardado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CancionDTO> actualizar(@PathVariable Long id, @RequestBody CancionDTO dto) {
        Cancion existente = cancionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Canción de id: " +id+" no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDuracion(dto.getDuracion());
        existente.setTrackNumber(dto.getTrackNumber());

        Cancion actualizado = cancionRepository.save(existente);
        return ResponseEntity.ok(CancionMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        Cancion album = cancionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Canción de id: " +id+" no encontrado"));
        cancionRepository.delete(album);
        return ResponseEntity.ok("Canción eliminada correctamente");
    }
}
