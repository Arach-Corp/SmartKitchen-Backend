package com.arachcorp.smartkitchen.rest.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String email;
    private String token;
    private Long expiresIn;
    private UserDTO user;
}
