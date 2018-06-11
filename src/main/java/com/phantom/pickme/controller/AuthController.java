package com.phantom.pickme.controller;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.UserLoginDto;
import com.phantom.pickme.dto.UserSignupDto;
import com.phantom.pickme.exception.AuthenticationException;
import com.phantom.pickme.security.JwtTokenUtil;
import com.phantom.pickme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<?> signin(@Valid @RequestBody UserLoginDto dto) {
        final String password = dto.getPassword();
        final User user = (User) userService.loadUserByUsername(dto.getUsername());
        return authentication(user, password);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignupDto dto) {
        final String password = dto.getPassword();
        final User user = userService.signup(dto);
        System.out.println(user.getUsername());
        return authentication(user, password);
    }

    private ResponseEntity<?> authentication(User user, String password) {
        final String userId = user.getUserId();
        Objects.requireNonNull(userId);
        Objects.requireNonNull(password);
        System.out.println(userId);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
        } catch (DisabledException e) {
            System.out.println("disabled");
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            System.out.println("bad");
            throw new AuthenticationException("Bad credentials!", e);
        }

        final String tokenStr = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new Token(tokenStr));
    }


    private class Token {
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        Token(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @GetMapping("/me")
    public String me(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(4);
        return "hello, " + jwtTokenUtil.getUserIdFromToken(token);
    }
}
