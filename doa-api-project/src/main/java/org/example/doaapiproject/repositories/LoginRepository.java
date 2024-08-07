package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Login;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<Login, String> {
    Login findLoginByEmail (String email);
    Login findLoginByEmailAndPassword(String email, String password);
    Login findLoginByUserIdAndEmail (Integer id, String email);
}
