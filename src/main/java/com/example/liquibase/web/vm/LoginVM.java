package com.example.liquibase.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginVM {
    @NotNull(message = "null value")
    @NotBlank(message = "Username is required.")
    private String username;

    @NotNull
    @Size(min = 3)
    @NotBlank(message = "Password is required.")
    private String password;
}