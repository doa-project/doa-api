package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.repositories.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // create
    @Transactional
    public Login createLogin(Login login) {
        return loginRepository.save(login);
    }

    // find login by email
    public Login findLogin(String email) {
        Login login = loginRepository.findLoginByEmail(email);
        if (login != null) {
            return login;
        } else {
            throw new RuntimeException("login not found");
        }
    }

    // find login by email and password
    public Login findLoginByEmailAndPassword(String email, String password) throws RuntimeException{
        Login login = loginRepository.findLoginByEmailAndPassword(email, password);
        if (login != null) {
            return login;
        } else {
            throw new RuntimeException("login not found");
        }
    }

    // find login by userId
    public Login findLoginByUserIdAndEmail(String id, String email) throws RuntimeException{
        Login login = loginRepository.findLoginByUserIdAndEmail(Integer.parseInt(id), email);
        if (login != null) {
            return login;
        } else {
            throw new RuntimeException("login not found");
        }
    }

    // delete
    @Transactional
    public Login deleteLogin(String email) {
        Login login = findLogin(email);
        loginRepository.deleteById(email);
        return login;
    }
}
