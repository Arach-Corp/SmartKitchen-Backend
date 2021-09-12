package com.arachcorp.smartkitchen.config;

import com.arachcorp.smartkitchen.entities.Role;
import com.arachcorp.smartkitchen.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (!roleRepository.existsByDescricao("CLIENT")) {
            Role role = new Role(null, "CLIENT");
            roleRepository.save(role);
        }

        if (!roleRepository.existsByDescricao("ADMIN")) {
            Role role = new Role(null, "ADMIN");
            roleRepository.save(role);
        }

    }
}
