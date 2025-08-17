-- V2: Usuario admin por defecto (admin@foro.com / 123456)
INSERT INTO usuarios (nombre, correo_electronico, contrasena, rol)
VALUES ('Admin', 'admin@foro.com', '$2b$10$aYVXCmRpBi4jvoqAUXiKluku6ojIHldk8LjLRNzapSbyrzTC5oeM.', 'ADMIN')
ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);
