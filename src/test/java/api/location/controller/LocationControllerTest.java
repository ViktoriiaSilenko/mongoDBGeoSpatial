package api.location.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLocationsShouldReturnJsonResponse() throws Exception {
        this.mockMvc.perform(get("/locations")
                .param("lowerLatitude", "10.1")
                .param("lowerLongitude", "20.2")
                .param("higherLatitude", "18.1")
                .param("higherLongitude", "28.2")
                .param("subjects", "subjects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
