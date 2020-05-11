package tomatosolutions.najdiprevoz.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tomatosolutions.najdiprevoz.models.auth.Role;
import tomatosolutions.najdiprevoz.models.auth.RoleName;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.exceptions.security.EmailAlreadyExistsException;
import tomatosolutions.najdiprevoz.models.exceptions.security.UsernameAlreadyExistsException;
import tomatosolutions.najdiprevoz.repositories.*;
import tomatosolutions.najdiprevoz.utils.security.JwtTokenProvider;
import tomatosolutions.najdiprevoz.services.AuthService;

import java.util.Collections;
import java.util.NoSuchElementException;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String authenticateUser(String usernameOrEmail, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Override
    public User registerUser(String username, String email, String password, String name) {
        if(userRepository.existsByUsername(username)) throw new UsernameAlreadyExistsException(username);
        if(userRepository.existsByEmail(email)) throw new EmailAlreadyExistsException(email);

        // Creating user's account
        User user = new User(name, username, email, password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new NoSuchElementException("Корисничката улога не постои."));
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    @Override
    public Boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public Boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }


}
