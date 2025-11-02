package com.uade.tpo.rescate_fauna.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

import com.uade.tpo.rescate_fauna.entity.User;
import com.uade.tpo.rescate_fauna.services.UserService;
import com.uade.tpo.rescate_fauna.dto.LoginRequest;
import com.uade.tpo.rescate_fauna.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        User u = service.authenticate(request.getEmail(), request.getPassword());

        // Create session and store minimal info
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("userId", u.getId());
        session.setMaxInactiveInterval(30 * 60);
        LoginResponse resp = new LoginResponse();
        resp.setId(u.getId());
        resp.setEmail(u.getEmail());
        resp.setNombre(u.getNombre());
        resp.setApellido(u.getApellido());
        resp.setRol(u.getRol());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession s = request.getSession(false);
        if (s != null) {
            s.invalidate();
        }

        // Clear only the session cookie(s) that the client actually sent to avoid duplicates.
        // Many setups use either 'JSESSIONID' (servlet) or 'SESSION' (Spring Session).
        jakarta.servlet.http.Cookie[] incoming = request.getCookies();
        if (incoming != null) {
            for (jakarta.servlet.http.Cookie c : incoming) {
                String name = c.getName();
                if ("JSESSIONID".equalsIgnoreCase(name) || "SESSION".equalsIgnoreCase(name)) {
                    Cookie clear = new Cookie(name, "");
                    clear.setPath("/");
                    clear.setMaxAge(0);
                    clear.setHttpOnly(true);
                    // Don't set Secure here; match whatever the original cookie had in production
                    response.addCookie(clear);
                }
            }
        } else {
            // If client didn't send any cookies, try to clear both common names as a fallback.
            Cookie jcookie = new Cookie("JSESSIONID", "");
            jcookie.setPath("/");
            jcookie.setMaxAge(0);
            jcookie.setHttpOnly(true);
            response.addCookie(jcookie);
            Cookie scookie = new Cookie("SESSION", "");
            scookie.setPath("/");
            scookie.setMaxAge(0);
            scookie.setHttpOnly(true);
            response.addCookie(scookie);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Simple test endpoint to start a session with example attributes.
     * NOT for production: use real authentication and POST /login instead.
     */
    @GetMapping("/login-test")
    public ResponseEntity<String> loginTest(HttpSession session) {
        // set example values in session for testing
        session.setAttribute("userId", "12345");
        session.setAttribute("role", "admin");
        // set session timeout in seconds (optional)
        session.setMaxInactiveInterval(30 * 60);
        return ResponseEntity.ok("Sesión iniciada de prueba");
    }
    
    @GetMapping("/session/check")
    public ResponseEntity<?> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(401).body("No hay sesión activa");
        }
        
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("userId", session.getAttribute("userId"));
        sessionData.put("sessionId", session.getId());
        sessionData.put("activa", true);
        
        return ResponseEntity.ok(sessionData);
    }
}