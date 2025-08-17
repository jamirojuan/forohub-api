# ForoHub API (Spring Boot)

API de foro minimalista con **JWT (Bearer)**, **BCrypt**, **JPA/Hibernate**, **Flyway** y **MySQL**.
Se implementa el CRUD de **Tópicos** y un **login** para obtener el token.

## Tecnologías
- Spring Boot 3.3
- Spring Web, Spring Security, Validation
- Spring Data JPA (Hibernate)
- Flyway Migration
- MySQL Driver
- Lombok
- JJWT (io.jsonwebtoken)
- DevTools (opcional en dev)

## Requisitos
- Java 17
- Maven 3.9+
- MySQL 8.x

## Configuración
Edita `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
security.jwt.secret=BASE64-KEY  # ya hay una por defecto
```

## Ejecutar
```bash
mvn spring-boot:run
```

Flyway creará las tablas y un usuario admin por defecto:
- **email:** `admin@foro.com`
- **password:** `123456`

## Autenticación
1. `POST /login`
```json
{ "email": "admin@foro.com", "password": "123456" }
```
2. Recibirás `{ "accessToken": "<JWT>", "tokenType": "Bearer" }`.
3. Usa el token en el header:
```
Authorization: Bearer <JWT>
```

## Endpoints de Tópicos
- `POST /topicos` (autenticado) – crea tópico
- `GET /topicos` – lista todos
- `GET /topicos/{id}` – obtiene por id
- `PUT /topicos/{id}` (autenticado) – actualiza
- `DELETE /topicos/{id}` (autenticado) – elimina

Cuerpo de creación:
```json
{
  "titulo": "Mi primer tópico",
  "mensaje": "Contenido del tópico",
  "curso": "ALURA 552 Equipo 2"
}
```

## Notas
- El **autor** del tópico se toma del `username` del token (email del usuario).
- `status` por defecto es `ABIERTO`. Se puede actualizar a `CERRADO` vía `PUT`.
- `respuestas` empieza en 0 (la entidad Respuesta **no** está implementada).

