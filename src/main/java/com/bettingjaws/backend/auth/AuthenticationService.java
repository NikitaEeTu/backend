package com.bettingjaws.backend.auth;

import com.bettingjaws.backend.persistence.entity.Role;
import com.bettingjaws.backend.config.jwtauthontication.JwtService;
import com.bettingjaws.backend.persistence.entity.User;
import com.bettingjaws.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        if(checkIfIsNotExist(request.getEmail())) {
            var user = new User(
                    UUID.randomUUID().toString(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPhoneNumber(),
                    request.getCountry(),
                    1,
                    0,
                    0,
                    Role.valueOf(Role.USER.name()),
                    passwordEncoder.encode(request.getPassword()
                    )
            );
            userRepository.createAUser(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else {
            throw new Exception("User is already created. Please log in");
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.getAUserByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    private boolean checkIfIsNotExist(String email){
        if(userRepository.getAUserByEmail(email).isEmpty()){
            return true;
        }
        return false;
    }
}
