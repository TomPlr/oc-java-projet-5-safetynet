package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private CommunityEmailController communityEmailController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(communityEmailController).build();
    }

    @Test
    public void testFindEmails() throws Exception {

        mockMvc.perform(get("/communityEmail").contentType(MediaType.APPLICATION_JSON).param("city","test"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
