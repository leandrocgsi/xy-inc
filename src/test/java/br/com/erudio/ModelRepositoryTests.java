package br.com.erudio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.erudio.builders.ModelBuilder;
import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelRepositoryTests {

    private MockMvc mockMvc;

    /*@Test
    public void test_01_IncluiUmModeloValido() throws Exception {
        String jsonModel = ModelBuilder
                            .newModel("products")
                            .addField("name", "string")
                            .addField("description", "text")
                            .addField("category", "string")
                            .addField("stock", "double")
                            .buildStringJson();
                
        MvcResult result = mockMvc.perform(post("/models")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(jsonModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.CREATED),true);        
    }
    
    @Test
    public void test_02_IncluiOutroModeloValido() throws Exception {
        String jsonModel = ModelBuilder
                            .newModel("customers")
                            .addField("name", "string")
                            .addField("address", "text")
                            .addField("city", "string")
                            .addField("age", "integer")
                            .buildStringJson();
                
        MvcResult result = mockMvc.perform(post("/models")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(jsonModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.CREATED),true);        
    }
    
    @Test
    public void test_03_AlteraUmModeloValido() throws Exception {
        String jsonModel = ModelBuilder
                            .newModel("products")
                            .addField("name", "string")
                            .addField("description", "text")
                            .addField("category", "string")
                            .addField("price", "double")
                            .buildStringJson();
                
        MvcResult result = mockMvc.perform(put("/models")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(jsonModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.OK),true);        
    }
    
    @Test
    public void test_03_AlteraUmModeloQueNaoExiste() throws Exception {
        String jsonModel = ModelBuilder
                            .newModel("productsERRADO")
                            .addField("name", "string")
                            .addField("description", "text")
                            .addField("category", "string")
                            .addField("price", "double")
                            .buildStringJson();
                
        MvcResult result = mockMvc.perform(put("/models")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(jsonModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.NOT_FOUND),true);        
    }        
    
    @Test
    @SuppressWarnings("rawtypes")
    public void test_04_RecuperaUmModelo() throws Exception {               
        MvcResult result = mockMvc.perform(get("/models/products")
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.OK),true);   
        ResponseWrapper<Object> resultJson = ModelBuilder.buildModelFromJson(result.getResponse().getContentAsString());
        LinkedHashMap objetos = (LinkedHashMap) resultJson.getResult();
        Assert.assertEquals(objetos.size(), 2);
    }
    
    @Test
    @SuppressWarnings("unchecked")    
    public void test_05_RecuperaTodosModelo() throws Exception {                
        MvcResult result = mockMvc.perform(get("/models")
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.OK),true);
       
        ResponseWrapper<Object> resultJson = ModelBuilder.buildModelFromJson(result.getResponse().getContentAsString());        
        ArrayList<Object> objetos = (ArrayList<Object>) resultJson.getResult();
        Assert.assertEquals(objetos.size(), 2);

    }    
    
    @Test
    public void test_06_ExcluiUmModeloValido() throws Exception {
        MvcResult result = mockMvc.perform(delete("/models/customers")
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        
        Assert.assertEquals(result.getResponse().getContentAsString().contains(Response.OK),true);        
    }*/
}
