package com.arachcorp.smartkitchen.rest.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "{auth.register-dto.nome.empty}")
    private String nome;

    @Email(message = "{auth.register-dto.email.valid}")
    private String email;

    @Length(min = 1, max = 32, message = "{auth.register-dto.password.length}")
    @NotBlank(message = "{auth.register-dto.password.empty}")
    private String password;

}
