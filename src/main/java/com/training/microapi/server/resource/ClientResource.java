package com.training.microapi.server.resource;


import com.training.microapi.server.model.Client;
import com.training.microapi.server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @PostMapping()
    public Client save(@RequestBody Client client) {
        return service.save(client);
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable String id) {
        return service.findById(id);
    }
    
    @GetMapping("/email/{email}")
    public Client findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    @GetMapping()
    public List<Client> findAll() {
        return service.findAll();
    }

    @GetMapping("/findAllByNameLike/{name}")
    public List<Client> findAllByNameLike(@PathVariable String name) {
        return service.findAllByNameLike(name);
    }

    @DeleteMapping("/deleteAllByEmail/{email}")
    public void deleteAllByEmail(@PathVariable String email) {
        service.deleteAllByEmail(email);
    }

}
