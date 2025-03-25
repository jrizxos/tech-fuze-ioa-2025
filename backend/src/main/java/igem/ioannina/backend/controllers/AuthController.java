package igem.ioannina.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igem.ioannina.backend.components.JwtUtil;

import lombok.Data;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @GetMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        if (System.getenv("USER").equals(authRequest.getUsername()) && System.getenv("PASSWORD").equals(authRequest.getPassword())) {
            String token = JwtUtil.generateToken(authRequest.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}