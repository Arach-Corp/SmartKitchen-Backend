package com.arachcorp.smartkitchen.rest.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    @Email(message = "{auth.credentials-dto.email.valid}")
    @NotBlank(message = "{auth.credentials-dto.email.empty}")
    private String email;

    @NotBlank(message = "{auth.credentials-dto.password.empty}")
    private String password;
}
