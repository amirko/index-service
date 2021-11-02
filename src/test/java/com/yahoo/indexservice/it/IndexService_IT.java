package com.yahoo.indexservice.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahoo.indexservice.IndexServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = IndexServiceApplication.class)
@AutoConfigureMockMvc
public class IndexService_IT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testWebCall() throws Exception {
        URL url1 = this.getClass().getClassLoader().getResource("2.txt");
        URL url2 = this.getClass().getClassLoader().getResource("poem1.txt");
        String url1Encoded = URLEncoder.encode(url1.toString(), StandardCharsets.UTF_8);
        String url2Encoded = URLEncoder.encode(url1.toString(), StandardCharsets.UTF_8);
        mockMvc.perform(post("/document?url=" + url1.toString()))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();
        mockMvc.perform(post("/document?url=" + url2.toString()))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();
        String resp = mockMvc.perform(get("/search?words=know&words=life"))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Set<String> res = mapper.readValue(resp, Set.class);
        Set<String> expected = Set.of(url1.toString(), url2.toString());
        assertEquals(expected, res);
    }
}
