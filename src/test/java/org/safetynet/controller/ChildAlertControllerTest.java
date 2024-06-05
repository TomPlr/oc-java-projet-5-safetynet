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
public class ChildAlertControllerTest {

    @Autowired
    private ChildAlertController childAlertController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DataLoadJson.init();
        mockMvc = MockMvcBuilders.standaloneSetup(childAlertController).build();
    }

    @Test
    public void testFindChildrenByAddress() throws Exception {

        mockMvc.perform(get("/childAlert").contentType(MediaType.APPLICATION_JSON).param("address", "1509 Culver St"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(status().isOk())
                .andReturn();
    }
}
