package br.com.erudio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.print.Book;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ModelRepositoryTests {
    
    private RestTemplate restTemplate = new TestRestTemplate();
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public final String basePath = "http://localhost:8080/api";
    
    @Test
    public void testCreateModel() throws JsonProcessingException{
      
      Map<String, Object> requestBody = new HashMap<String, Object>();
      requestBody.put("name", "Book 1");
      requestBody.put("isbn", "QWER1234");
      requestBody.put("author", "Author 1");
      requestBody.put("pages", 200);
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<String> httpEntity =  new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
    Map<String, Object> apiResponse =  restTemplate.postForObject(string, httpEntity, Map.class, Collections.EMPTY_MAP);

      assertNotNull(apiResponse);
      
      //Asserting the response of the API.
      String message = apiResponse.get("message").toString();
      assertEquals("Book created successfully", message);
      String bookId = ((Map<String, Object>)apiResponse.get("book")).get("id").toString();
      
      assertNotNull(bookId);
      
      //Fetching the Book details directly from the DB to verify the API succeeded
      Book bookFromDb = bookRepository.findOne(bookId);
      assertEquals("Book 1", bookFromDb.getName());
      assertEquals("QWER1234", bookFromDb.getIsbn());
      assertEquals("Author 1", bookFromDb.getAuthor());
      assertTrue(200 == bookFromDb.getPages());
      
      //Delete the data added for testing
      bookRepository.delete(bookId);

    }

}
