package com.training.microapi.test.helper;

import com.training.microapi.server.model.Client;

public class ClientHelper {


    public static Client createBasicClient(String name, String email) {
        Client ret = new Client();
        ret.setEmail(email);
        ret.setName(name);
        return ret;
    }
}
