package com.pspr.config;

import com.pspr.model.Role;
import com.pspr.model.User;
import com.pspr.repository.RoleRepository;
import com.pspr.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguraBDInicial {
    private static final Logger log = LoggerFactory.getLogger(ConfiguraBDInicial.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ConfiguraBDInicial(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        // Usamos una clase anónima en lugar de lambda
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("=== Inicializando base de datos de usuarios y roles ===");
                // 1. Borrar datos existentes
                log.info("Eliminando usuarios y roles existentes...");
                userRepository.deleteAll();
                roleRepository.deleteAll();
                log.info("Tablas limpiadas correctamente.");
                // 2. Crear roles
                log.info("Creando roles iniciales...");
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
                log.info("Rol creado: {}", adminRole);
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
                log.info("Rol creado: {}", userRole);

                // 3. Crear usuario admin con los dos roles
                log.info("Creando usuario 'admin' con roles ADMIN y USER...");

                //TODO: crear y guardar usuario username "admin" con 2 roles
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                userRepository.save(admin);

                // 4. Crear un usuario normal con ROLE_USER
                log.info("Creando usuario 'user' con rol USER...");
                // TODO: crear y guardar usuario username "user" con un rol
                log.info("=== Inicialización de BD completada correctamente ===");
            }
        };
    }
}
