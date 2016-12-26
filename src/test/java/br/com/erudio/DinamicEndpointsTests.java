package br.com.erudio;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.response.ResponseWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@SuppressWarnings({ "rawtypes", "deprecation" })
public class DinamicEndpointsTests {
    
    final String BASE_PATH = "http://localhost:8080/api";
    final String BASE_PATH_MODEL = "http://localhost:8080/api/model";
    
    private RestTemplate restTemplate;
    
    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        
        restTemplate.delete(BASE_PATH_MODEL + "/albums");
        
        Model model = new Model();
        Map<String, String> fields = new HashMap<String, String>();
        
        fields.put("gender", "string");
        fields.put("year", "integer");
        fields.put("artist", "string");
        fields.put("name", "string");
        model.setFields(fields);
        model.setName("albums");
        
        //Insert a new model 
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH_MODEL, model, ResponseWrapper.class);
    }
    
    
    //POST
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateModel() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        
        fields.put("gender", "Hard Rock");
        fields.put("year", 1976);
        fields.put("artist", "Nazareth");
        fields.put("name", "Close Enough for Rock 'n' Roll");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/albums", fields, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
    }
    
    //GET by ID
    @Test
    @SuppressWarnings("unchecked")
    public void testFindOne() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        
        fields.put("gender", "Progressive Rock");
        fields.put("year", 1971);
        fields.put("artist", "Jethro Tull");
        fields.put("name", "Aqualung");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/albums", fields, ResponseWrapper.class);
        
        Map result = (Map) response.getResult();
        String id = (String) result.get("_id");

        ResponseWrapper response2 = restTemplate.getForObject(BASE_PATH + "/albums/" + id , ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertTrue(response2.getResult().toString().contains("gender=Progressive Rock, year=1971, artist=Jethro Tull, name=Aqualung"));
    }
    
    //PUT
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateUnsuportedModel() throws JsonProcessingException{
        Map<String, Object> fields = new HashMap<String, Object>();
        
        fields.put("manufacturer", "SAMSUNG");
        fields.put("details", "Explosive");
        fields.put("model", "Galaxy Note 7 ");

        ResponseWrapper<Map<String, Object>> response = restTemplate.postForObject(BASE_PATH + "/telephones", fields, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, response.getStatus());
    }
    
    //GET all
    @Test
    public void testFindAll() throws JsonProcessingException{
        ResponseWrapper response = restTemplate.getForObject(BASE_PATH + "/albums", ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
    }

}
