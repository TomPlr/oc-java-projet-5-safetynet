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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PhoneAlertController phoneAlertController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(phoneAlertController).build();
    }

    @Test

    public void testFindPersonsPhoneNumbers() throws Exception {
        mockMvc.perform(get("/phoneAlert").contentType(MediaType.APPLICATION_JSON).param("firestation","1"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
