package com.service.Nimbus.Controller;

import com.service.Nimbus.DTO.JwtResponse;
import com.service.Nimbus.DTO.LoginRequest;
import com.service.Nimbus.Model.User;
import com.service.Nimbus.Service.LoginAndRegister;
import com.service.Nimbus.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/controller")
@CrossOrigin
public class AuthController {

    private final LoginAndRegister loginAndRegister;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(LoginAndRegister loginAndRegister, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.loginAndRegister = loginAndRegister;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/health")
    public void health() {
        // Logic to check the health of the service
        System.out.println("Service is running and healthy.");
    }

    @PostMapping("/register")
    public void register(@RequestBody User userDetails) {
        // Logic to handle user registration
        System.out.println("Registering user with details: " + userDetails);
        loginAndRegister.register(userDetails);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Logging in user with username: " + loginRequest.username());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("User authenticated successfully: " + authentication.getName());

        String Token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(Token));
    }

    @PostMapping("/content")
    public void content(@RequestBody String content) {
        // Logic to handle content creation or management
        System.out.println("Handling content: " + content);
        // You can add your content handling logic here
    }
}
