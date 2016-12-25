package br.com.erudio;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.response.ResponseWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ModelRepositoryTests {
    
    final String BASE_PATH = "http://localhost:8080/api/model";
    
    @Before
    public void setUp() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseWrapper response = restTemplate.getForObject(BASE_PATH, ResponseWrapper.class);

        restTemplate.delete(BASE_PATH + "/products");
    }
    
    @Test
    public void testCreateModel() throws JsonProcessingException{
        RestTemplate restTemplate = new RestTemplate();
      
        Model model = new Model();
        Map<String, String> fields = new HashMap<String, String>();
        
        fields.put("name", "string");
        fields.put("description", "text");
        fields.put("price", "decimal");
        fields.put("category", "string");
        model.setFields(fields);
        model.setName("products");
      
      ResponseEntity<ResponseWrapper<Model>> response = restTemplate.postForEntity(BASE_PATH, model, ResponseWrapper.class, Collections.EMPTY_MAP);

      assertNotNull(response);
    }

}
