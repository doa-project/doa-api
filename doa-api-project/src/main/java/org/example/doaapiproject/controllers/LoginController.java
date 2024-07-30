package org.example.doaapiproject.controllers;

import jakarta.validation.Valid;
import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.models.User;
import org.example.doaapiproject.services.LoginService;
import org.example.doaapiproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@RestController
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;
    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    // create
    @PostMapping("/login")
    public Object login(@Valid @RequestBody Login login, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            loginService.createLogin(login);
            Object response;

            // verificando se é User
            try {
                response = userService.findUserByEmail(login.getEmail());;
            } catch (RuntimeException r) {
                response = null;
            }

            // verificando se é Institution
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
