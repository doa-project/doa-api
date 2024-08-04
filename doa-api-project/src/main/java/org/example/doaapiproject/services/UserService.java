package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.User;
import org.example.doaapiproject.repositories.UserIdRepository;
import org.example.doaapiproject.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserIdRepository userIdRepository;
    public UserService(UserRepository userRepository, UserIdRepository userIdRepository) {
        this.userRepository = userRepository;
        this.userIdRepository = userIdRepository;
    }

    // create
    @Transactional
    public User createUser(User user) {
        // criar o login
        // não vamos cadastrar usuários novos, eles já vão estar cadastrados
        // loginService.createLogin(new Login(user.getEmail(), user.getPassword()));
        user.setUserId(userIdRepository.findUserId());
        System.out.println(userIdRepository.findUserId());
        System.out.println(user.getUserId());
        return userRepository.save(user);
    }

    // find user by id
    public User findUser(String id) {
        User user = userRepository.findUserByUserId(Integer.parseInt(id));
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("user not found");
        }
    }

    // find user by email
    public User findUserByEmail(String email) throws RuntimeException{
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("user not found");
        }
    }

    // delete
    @Transactional
    public User deleteUser(String id) {
        User user = findUser(id);
        userRepository.deleteById(Integer.parseInt(id));
        return user;
    }
}
