package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Role;
import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.repositories.UserRepository;
import com.arachcorp.smartkitchen.services.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void deleteById(Long id) {
        try {
            userRepository.delete(getById(id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DeleteResourceException("User resource cannot be deleted");
        }
    }

    public void update(Long id, User user) {
        try {
            final User entity = getById(id);
            populate(user, entity);
            userRepository.save(entity);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UpdateResourceException("User resource cannot be updated");
        }
    }

    public User getById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User resource not found"));
    }

    public User create(User user) throws UserRegisterException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage(), e);
            throw new UserRegisterException("O email '" + user.getEmail() + "' já está em uso");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserRegisterException("Erro trying create a new user");
        }
    }

    public User getByEmail(final String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User " + email + " not found"));
    }

    public UserDetails autenticate(User user) throws UsernameNotFoundException, InvalidPasswordException {
        final UserDetails details = loadUserByUsername(user.getEmail());
        if (passwordEncoder.matches(user.getPassword(), details.getPassword())) {
            return details;
        }
        throw new InvalidPasswordException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usúario não encontrado"));
        String[] roles = user.getRoles().stream().map(Role::getDescricao).toArray(String[]::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public User getCurrentUser() {
        try {
            final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return getByEmail(user.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    protected void populate(User source, User target) {
        target.setNome(source.getNome());
    }

}
