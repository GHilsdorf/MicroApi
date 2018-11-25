package com.training.microapi.test.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.microapi.server.model.Client;
import com.training.microapi.server.resource.ClientResource;
import com.training.microapi.server.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.training.microapi.test.helper.GenericHelper.createDefaultAnswer;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.training.microapi.test.helper.ClientHelper.createBasicClient;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false, controllers = ClientResource.class)
public class ClienteResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ClientService service;

    @Test
    public void save_with_success() throws Exception {

        Client client = createBasicClient("testName", "test@mail.com");

        given(service.save(any(Client.class))).willAnswer(createDefaultAnswer());

        String register = mapper.writeValueAsString(client);

        mvc.perform(
                post("/api/client")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.email", is(client.getEmail())));

    }
}
