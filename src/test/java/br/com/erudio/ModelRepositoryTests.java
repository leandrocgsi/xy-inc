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
public class ModelRepositoryTests {
    
    final String BASE_PATH = "http://localhost:8080/api/model";
    private RestTemplate restTemplate;
    
    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateModel() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/products");

        Model model = new Model();
        Map<String, String> fields = new HashMap<String, String>();

        fields.put("name", "string");
        fields.put("description", "text");
        fields.put("price", "decimal");
        fields.put("category", "string");
        model.setFields(fields);
        model.setName("products");

        //Insert a new model 
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
        
        //Insert a repeated model 
        ResponseWrapper<Model> response2 = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response2);
        Assert.assertEquals(HttpStatus.CONFLICT, response2.getStatus());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateModel() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/products");
        
        Model model = new Model();
        Map<String, String> fields = new HashMap<String, String>();
        
        fields.put("name", "string");
        fields.put("description", "text");
        fields.put("price", "decimal");
        fields.put("category", "string");
        model.setFields(fields);
        model.setName("products");
        
        //Insert a new model 
        ResponseWrapper<Model> response = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
        
        //Insert a repeated model 
        ResponseWrapper<Model> response2 = restTemplate.postForObject(BASE_PATH, model, ResponseWrapper.class);
        assertNotNull(response2);
        Assert.assertEquals(HttpStatus.CONFLICT, response2.getStatus());
    }
    
    @Test
    public void testFindAll() throws JsonProcessingException{
        ResponseWrapper response = restTemplate.getForObject(BASE_PATH, ResponseWrapper.class);
        assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
    }

}
