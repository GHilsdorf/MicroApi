package com.training.microapi.server.service;

import com.training.microapi.server.components.ClientValidator;
import com.training.microapi.server.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.training.microapi.server.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientValidator validator;

    public Client save(Client client) {

        validator.validate(client);

        return repository.save(client);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void deleteAllByEmail(String email) {
        repository.deleteAllByEmail(email);
    }

    public Client findByEmail(String email) {
        Optional<Client> ret = repository.findByEmail(email);

        if (ret.isPresent()) {
            return ret.get();
        }
        return null;
    }

    public Client findById(String id) {

        Optional<Client> ret = repository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        }
        return null;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public List<Client> findAllByNameLike(String name) {
        return repository.findAllByNameLike(name);
    }
}
