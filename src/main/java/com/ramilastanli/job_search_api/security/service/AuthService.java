package com.ramilastanli.job_search_api.security.service;

import com.ramilastanli.job_search_api.entity.User;
import com.ramilastanli.job_search_api.repository.UserRepository;
import com.ramilastanli.job_search_api.security.dto.AuthResponse;
import com.ramilastanli.job_search_api.security.dto.RegisterRequest;
import com.ramilastanli.job_search_api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Bu istifadəçi adı artıq alınıb");
        }

        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(String username, String password) {
        // AuthenticationManager avtomatik şifrəni yoxlayır
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("İstifadəçi tapılmadı"));

        var jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthResponse(jwtToken);
    }
}
