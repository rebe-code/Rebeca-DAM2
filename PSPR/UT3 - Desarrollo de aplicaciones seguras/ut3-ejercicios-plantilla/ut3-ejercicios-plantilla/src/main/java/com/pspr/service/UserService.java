package com.pspr.service;

import com.pspr.model.User;
import com.pspr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //se puede poner en el constructor

    // Inicializar el Repositorio
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Metodo requerido por SpringSecurity //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO buscar usuario
        return userRepository.findByUsername(username).get();
    }

    // Buscar por Nombre
    public Optional<User> buscarPorNombre(String username) {
        // TODO buscar usuario
        return userRepository.findByUsername(username);
    }


    // Dado un nombre y una pass sin codificar valida si existe en BD //
    public boolean validaUsuarioPassword(String nombreUsuario, String rawPassword){
        // rawPassword: password sin cifrar
        //TODO validacion de usuario y pass

        //buscar el usuario por el nombre
        Optional<User> opt = userRepository.findByUsername(nombreUsuario);
            if (opt.isPresent()) {
                //El usuario existe
                User usuario = opt.get();

                    //Si existe compruebo la password con passwordEncoder.match
                    if (passwordEncoder.matches(rawPassword,usuario.getPassword())){
                        return true;
                    }else{
                        //Si la password no coincide retorna falso
                        return false;
                    }
            }else{
                //Si el usuario no existe retorna falso
                return false;
            }
    }
}
