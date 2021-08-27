package com.arachcorp.smartkitchen.rest;

import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.rest.dto.auth.CredentialsDTO;
import com.arachcorp.smartkitchen.rest.dto.auth.RegisterDTO;
import com.arachcorp.smartkitchen.rest.dto.auth.TokenDTO;
import com.arachcorp.smartkitchen.rest.dto.auth.UserDTO;
import com.arachcorp.smartkitchen.rest.utils.UriUtils;
import com.arachcorp.smartkitchen.security.jwt.JwtService;
import com.arachcorp.smartkitchen.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody CredentialsDTO credentialsDTO) {
        final User user = new User();
        user.setEmail(credentialsDTO.getEmail());
        user.setPassword(credentialsDTO.getPassword());
        final UserDetails userDetails = userService.autenticate(user);
        final String token = jwtService.createToken(user);
        log.info("User " + user.getEmail() + "logged");
        final TokenDTO tokenDTO = TokenDTO.builder()
                .token(token)
                .email(user.getEmail())
                .expiresIn(jwtService.getExpiresIn(token))
                .build();
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        User user = User.builder()
            .email(registerDTO.getEmail())
            .password(registerDTO.getPassword())
            .nome(registerDTO.getNome())
            .urlFoto(registerDTO.getUrlFoto())
            .build();
        user = userService.create(user);

        final UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nome(user.getNome())
                .build();
        return ResponseEntity.created(UriUtils.createFromCurrentRequest(null)).body(dto);
    }

}
