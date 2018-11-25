package com.training.microapi.server.repository;

import com.training.microapi.server.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    void deleteAllByEmail(String email);

    List<Client> findAllByNameLike(String name);

    public Optional<Client> findByEmail(String email);

}
