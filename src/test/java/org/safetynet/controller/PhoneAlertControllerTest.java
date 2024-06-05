package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class PhoneAlertControllerTest {

    @Autowired
    private PhoneAlertController phoneAlertController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DataLoadJson.init();
        mockMvc = MockMvcBuilders.standaloneSetup(phoneAlertController).build();
    }

    @Test

    public void testFindPersonsPhoneNumbers() throws Exception {

        mockMvc.perform(get("/phoneAlert").contentType(MediaType.APPLICATION_JSON).param("firestation", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andReturn();

    }
}
