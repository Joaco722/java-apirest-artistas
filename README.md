# 🎵 API REST de Artistas y Bandas

Este proyecto es una API REST desarrollada con **Spring Boot** que permite gestionar información sobre artistas o bandas musicales. Incluye operaciones CRUD básicas y está preparada para conectarse a una base de datos PostgreSQL en un entorno local o mediante Docker.

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
- **PostgreSQL**
- **Docker** y **Docker Compose**
- **TablePlus** (como cliente visual para base de datos, opcional)
- **Maven** (gestor de dependencias)
  - DotEnv
  - Lombok

## 📦 Estructura del proyecto

src/
└── main/
├── java/
│ └── com.tecjocs.apirest.apirest/
│ ├── Controllers/
│ │ └── ArtistaBandaController.java
│ ├── Entities/
│ │ └── ArtistaBanda.java
│ └── Repositories/
│ └── ArtistaBandaRepository.java
└── resources/
└── application.properties

## 📡 Endpoints disponibles

| Método | Ruta                   | Descripción                  |
| ------ | ---------------------- | ---------------------------- |
| GET    | `/artistas/`           | Listar todos los artistas    |
| GET    | `/artistas/{id}`       | Listar un solo artista/banda |
| POST   | `/artistas`            | Crear un nuevo artista/banda |
| DELETE | `/artistas/{id}`       | Eliminar un artista por ID   |
| PUT    | `/artistas/{id}`       | Actualizar un artista por ID |

## 📬 Contacto
Proyecto creado como práctica personal por Joaquin Velazquez siguiendo el curso de JAVA de https://github.com/sergiecode
Podés sugerir mejoras o forkearlo para tus propios proyectos.


