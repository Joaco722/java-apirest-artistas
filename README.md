# 🎵 API REST de Artistas, Álbumes y Canciones

Este proyecto es una **API REST** desarrollada con **Spring Boot** que permite gestionar información musical: artistas/bandas, álbumes y canciones. Está pensada con prácticas escalables, usando DTOs, mappers y relaciones unidireccionales para mantener la simplicidad y evitar problemas comunes como serializaciones infinitas.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3
  - Spring Web
  - Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- Maven (gestor de dependencias)
- Lombok
- DotEnv
- TablePlus (cliente visual para la base de datos - opcional)

---

## 🧠 Arquitectura del proyecto

El proyecto sigue una estructura modular con separación de responsabilidades:

```
src/
└── main/
    ├── java/
    │   └── com.tecjocs.apirest.apirest/
    │       ├── controllers/
    │       │   ├── ArtistaBandaController.java
    │       │   ├── AlbumController.java
    │       │   └── CancionController.java
    │       ├── dtos/
    │       │   ├── ArtistaBandaDTO.java
    │       │   ├── AlbumDTO.java
    │       │   ├── CancionDTO.java
    │       │   └── CancionResumenDTO.java
    │       ├── entities/
    │       │   ├── ArtistaBanda.java
    │       │   ├── Album.java
    │       │   ├── Cancion.java
    │       │   └── TipoArtista.java (enum)
    │       ├── mappers/
    │       │   ├── ArtistaBandaMapper.java
    │       │   ├── AlbumMapper.java
    │       │   └── CancionMapper.java
    │       └── repositories/
    │           ├── ArtistaBandaRepository.java
    │           ├── AlbumRepository.java
    │           └── CancionRepository.java
    └── resources/
        └── application.properties
```

---

## 📦 Funcionalidades implementadas

### 🎤 Artistas / Bandas

- Representados por la clase `ArtistaBanda`, con un `TipoArtista` (enum: BANDA o SOLISTA).
- Soporta CRUD completo.
- Relación **uno a muchos** con `Album`.

### 💿 Álbumes

- Incluye nombre, género, fecha de lanzamiento y portada.
- Cada álbum pertenece a un único artista (`ManyToOne`).
- Tiene una lista de canciones (`OneToMany`).
- DTO omite referencias innecesarias para evitar ciclos de JSON.

### 🎵 Canciones

- Cada canción tiene nombre, duración y número de pista (`trackNumber`).
- Están asociadas a un único álbum (`ManyToOne`).
- Se devuelve en el DTO sin los IDs internos para simplificar la vista.

---

## 🧰 Endpoints disponibles

### 🔹 Artistas

| Método | Ruta                | Descripción                        |
|--------|---------------------|------------------------------------|
| GET    | /artistas           | Listar todos los artistas          |
| GET    | /artistas/{id}      | Obtener un artista por ID          |
| POST   | /artistas           | Crear un nuevo artista o banda     |
| PUT    | /artistas/{id}      | Actualizar un artista              |
| DELETE | /artistas/{id}      | Eliminar un artista                |

### 🔹 Álbumes

| Método | Ruta                | Descripción                              |
|--------|---------------------|------------------------------------------|
| GET    | /albums             | Listar todos los álbumes                 |
| GET    | /albums/{id}        | Obtener un álbum por ID (con canciones)  |
| POST   | /albums             | Crear un álbum asociado a un artista     |

### 🔹 Canciones

| Método | Ruta                | Descripción                          |
|--------|---------------------|--------------------------------------|
| POST   | /canciones          | Crear una canción asociada a un álbum |

---

## 🧪 Cómo probar la API

1. Asegurate de tener Docker y Docker Compose instalados.
2. Levantá la base de datos con:

```bash
docker-compose up -d
```

3. Ejecutá la aplicación desde tu IDE o con:

```bash
./mvnw spring-boot:run
```

4. Probá los endpoints usando Postman, Insomnia o `curl`.

---

## 🧱 Consideraciones técnicas

- Uso de DTOs para desacoplar la lógica interna del modelo expuesto.
- Relación de entidades unidireccional para evitar bucles infinitos en la serialización.
- Enum `TipoArtista` mapeado con `@Enumerated(EnumType.STRING)`.
- Los mappers se ubican en una carpeta separada (`mappers`) para claridad.
- Manejo de errores con `ResponseStatusException` y `ResponseEntity`.

---

## 📬 Contacto

Proyecto desarrollado como práctica personal por **Joaquín Velázquez** siguiendo el curso de Java de [SergieCode](https://github.com/sergiecode).

Sos libre de sugerir mejoras, abrir issues o forkearlo para tus propios proyectos. 🚀
