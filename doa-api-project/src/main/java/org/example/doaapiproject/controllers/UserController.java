package org.example.doaapiproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.models.User;
import org.example.doaapiproject.models.UserRegistrationRequest;
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
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    // create
    @PostMapping("/create")
    @Operation(summary = "Create an user", description = "Returns the user created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content())
    })
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationRequest user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            User userCreated = userService.createUser(new User(user.getName(), user.getEmail(), user.getUserPhoto()));
            loginService.createLogin(new Login(userCreated.getUserId(), user.getEmail(), user.getPassword()));
            return new ResponseEntity<>(userCreated, HttpStatus.OK);
        }
    }

    // update
    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an user", description = "Returns the user updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "The user was not found",
                    content = @Content())
    })
    public ResponseEntity<?> updateUser(@Parameter(name = "id", description = "Requires the id of the user", required = true) @PathVariable String id, @Valid @RequestBody UserRegistrationRequest updatedUser, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                // Coloque o nome do campo e a mensagem de erro no mapa
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            try {
                User user = userService.findUser(id);

                Login login = loginService.findLoginByUserIdAndEmail(id, user.getEmail());
                login.setEmail(updatedUser.getEmail());
                login.setPassword(updatedUser.getPassword());
                loginService.createLogin(login);

                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setUserPhoto(updatedUser.getUserPhoto());

                userService.createUser(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (RuntimeException r) {
                return new ResponseEntity<>(r.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an user", description = "Returns the user updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content())
    })
    public ResponseEntity<?> deleteUser(@Parameter(name = "id", description = "Requires the id of the user", required = true)@PathVariable String id) {
        try {
            User userDeleted = userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDeleted);
        } catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r.getMessage());
        }
    }
}
