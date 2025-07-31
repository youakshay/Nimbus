package com.service.Nimbus.Controller;

import com.service.Nimbus.DTO.JwtResponse;
import com.service.Nimbus.DTO.LoginRequest;
import com.service.Nimbus.DTO.UpdatePassword;
import com.service.Nimbus.Model.User;
import com.service.Nimbus.Service.LoginAndRegister;
import com.service.Nimbus.Service.RedisTokenBlacklistService;
import com.service.Nimbus.Service.UpdateUserDetails;
import com.service.Nimbus.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final LoginAndRegister loginAndRegister;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UpdateUserDetails updateUserDetails;
    private final RedisTokenBlacklistService blacklistService;

    public AuthController(LoginAndRegister loginAndRegister,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          RedisTokenBlacklistService blacklistService,
                          UpdateUserDetails updateUserDetails) {
        this.loginAndRegister = loginAndRegister;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.blacklistService=blacklistService;
        this.updateUserDetails = updateUserDetails;
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

    @PostMapping("/updatepassword")
    public void updatePassword(@RequestBody UpdatePassword updatedPassword, HttpServletRequest request) {
        updateUserDetails.updatePassword(updatedPassword, request);
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        System.out.println("Logout called");
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("Invalidating token: " + token);
            long expiration = jwtUtil.getExpirationDateFromToken(token).getTime() - System.currentTimeMillis();
            blacklistService.blacklistToken(token, expiration);
        } else {
            System.out.println("No valid token found for logout.");
        }
        System.out.println("User logged out successfully.");
        return ResponseEntity.ok("User logged out successfully.");
    }
}
