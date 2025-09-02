-- Elimina la tabla si ya existe, para poder reiniciar la app sin errores
DROP TABLE IF EXISTS users;

-- Crea la tabla 'users'
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(255),
                       last_name VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       document_number BIGINT,
                       phone_number VARCHAR(20),
                       base_salary DOUBLE PRECISION,
                       password VARCHAR(255)
);