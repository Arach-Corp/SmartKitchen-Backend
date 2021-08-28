package com.arachcorp.smartkitchen.rest.dto.auth;

import com.arachcorp.smartkitchen.entities.Role;
import com.arachcorp.smartkitchen.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String nome;
    private String urlFoto;
    private List<String> roles;


    public static UserDTO fromUser(User user) {
        return Objects.isNull(user) ? null : UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nome(user.getNome())
                .urlFoto(user.getUrlFoto())
                .roles(
                        Objects.isNull(user.getRoles()) ?
                                new ArrayList<>() : user.getRoles().stream().map(Role::getDescricao).collect(Collectors.toList())
                )
                .build();
    }
}

