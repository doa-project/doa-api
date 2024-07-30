package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.repositories.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    public LoginService(LoginRepository loginRepository) {this.loginRepository = loginRepository;}

    // create
    @Transactional
    public Login createLogin(Login login) {

        return loginRepository.save(login);
    }

    // find user by id
    public Login findLogin(String email) {
        return loginRepository.findById(email).orElseThrow(() ->
                new RuntimeException("login not found"));
    }

    // delete
    @Transactional
    public Login deleteLogin(String email) {
        Login login = findLogin(email);
        loginRepository.deleteById(email);
        return login;
    }
}
