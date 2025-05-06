package com.data.examen.Service;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.data.examen.Entities.Role;
import com.data.examen.Entities.User;
import com.data.examen.Repository.RoleRepository;
import com.data.examen.Repository.UserRepository;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ROLE_ADMIN")));
            
            Role userRole = roleRepo.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepo.save(new Role("ROLE_USER")));

            if (userRepo.findByUsername("yasmine").isEmpty()) {
                User admin = new User("yasmine", encoder.encode("2002"), Set.of(adminRole));
                userRepo.save(admin);
            }
            
            if (userRepo.findByUsername("Eya").isEmpty()) {
                User user = new User("Eya", encoder.encode("1234"), Set.of(userRole));
                userRepo.save(user);
            }
        };
    }
}
