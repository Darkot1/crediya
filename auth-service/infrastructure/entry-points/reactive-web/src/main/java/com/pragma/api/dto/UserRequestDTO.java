package com.pragma.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotBlank(message = "El apellido es obligatorio")
        String lastName,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotNull(message = "El número de documento es obligatorio")
        @Positive(message = "El número de documento debe ser positivo")
        Long documentNumber,

        @NotBlank(message = "El número de teléfono es obligatorio")
        String phoneNumber,

        @NotNull(message = "El salario base es obligatorio")
        @Positive(message = "El salario base debe ser mayor que cero")
        Double baseSalary,

        @NotNull(message = "La contraseña es obligatoria")
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password

) {
}
