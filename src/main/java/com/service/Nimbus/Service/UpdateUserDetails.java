package com.service.Nimbus.Service;

import com.service.Nimbus.DTO.UpdatePassword;
import com.service.Nimbus.Model.User;
import com.service.Nimbus.Respository.CrudRepo;
import com.service.Nimbus.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;
import java.util.Optional;

@Service
public class UpdateUserDetails {
    private final CrudRepo crudRepo;
    private PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UpdateUserDetails(CrudRepo crudRepo,
                             PasswordEncoder passwordEncoder,
                             JwtUtil jwtUtil,
                             AuthenticationManager authenticationManager) {
        this.crudRepo=crudRepo;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void updatePassword(UpdatePassword updatedPassword, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        } else {
            throw new IllegalArgumentException("Invalid token format");
        }

        String username = jwtUtil.extractUsername(token);

        System.out.println("Username from token: " + username);
        System.out.println("Username provided: " + updatedPassword.username());
        if (!username.equals(updatedPassword.username())) {
            throw new IllegalArgumentException("Wrong username Entered");
        }

        Optional<User> user = crudRepo.findByUsername(username);
        String oldPassword = updatedPassword.oldPassword();

        if(!passwordEncoder.matches(oldPassword, user.get().password())) {
            throw new IllegalArgumentException("Old password is not correct");
        }

        String newPassword = updatedPassword.newPassword();
        String hashedPassword = passwordEncoder.encode(newPassword);
        System.out.println(user.get().id());
        User updatedUser = new User(
                user.get().id(),
                user.get().username(),
                user.get().fullName(),
                user.get().email(),
                hashedPassword,
                user.get().userRole(),
                user.get().createdAt()
        );
        crudRepo.save(updatedUser);
    }
}
