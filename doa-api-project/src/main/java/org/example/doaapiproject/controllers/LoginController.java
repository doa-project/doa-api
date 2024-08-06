package org.example.doaapiproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.services.LoginService;
import org.example.doaapiproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    @Operation(summary = "Login", description = "Returns the login found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login was found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "The login was not found",
                    content = @Content())
    })
    public Object login(@Valid @RequestBody Login login, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {

            // verificando o login
            try {
                loginService.findLoginByEmailAndPassword(login.getEmail(), login.getPassword());
            } catch (RuntimeException r) {
                return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_FOUND);
            }

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
