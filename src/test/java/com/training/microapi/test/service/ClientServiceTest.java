package com.training.microapi.test.service;

import com.training.microapi.server.components.ClientValidator;
import com.training.microapi.server.model.Client;
import com.training.microapi.server.service.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.training.microapi.test.helper.GenericHelper.createDefaultAnswer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import com.training.microapi.server.repository.ClientRepository;
import static com.training.microapi.test.helper.ClientHelper.createBasicClient;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

    @TestConfiguration
    static class ClientServiceTestContextConfiguration {

        @Bean
        public ClientService smsService() {
            return new ClientService();
        }
    }

    @Autowired
    private ClientService service;

    @MockBean
    private ClientRepository repository;

    @MockBean
    private ClientValidator validator;

    @Test
    public void save_client_with_success() {
        Client register = createBasicClient("testName", "test@mail.com");

        doNothing().when(validator).validate(any(Client.class));

        given(repository.save(any(Client.class))).willAnswer(createDefaultAnswer());

        Client ret = service.save(register);
        Assert.assertEquals(register.getName(), ret.getName());
        Assert.assertEquals(register.getEmail(), ret.getEmail());
    }

    @Test
    public void find_all_clients_by_name_like_with_success() {
        Client register = createBasicClient("testName", "test@mail.com");
        Client register2 = createBasicClient("testName", "test2@mail.com");

        given(repository.findAllByNameLike("test")).willReturn(Arrays.asList(register, register2));
        List<Client> ret = service.findAllByNameLike("test");

        Assert.assertEquals(2, ret.size());
        Assert.assertEquals(register.getName(), ret.get(0).getName());
        Assert.assertEquals(register.getEmail(), ret.get(0).getEmail());
        Assert.assertEquals(register2.getName(), ret.get(1).getName());
        Assert.assertEquals(register2.getEmail(), ret.get(1).getEmail());
    }

}
