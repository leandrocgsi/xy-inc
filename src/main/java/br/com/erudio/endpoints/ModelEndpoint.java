package br.com.erudio.endpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;
import br.com.erudio.response.ResponseWrapper;

@RestController
public class ModelEndpoint {

    @Autowired
    private ModelRepository modelRepository;
    
    @RequestMapping(method=RequestMethod.GET,value="/models",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Model>>> getAllRecords() {
        List<Model> models = null;
        try {
//            models = modelRepository.findAll();
            models = mockModels();
            if (models==null) {
                return new ResponseWrapper<List<Model>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();                
            }        
            return new ResponseWrapper<List<Model>>(HttpStatus.OK, "Success!", models).response();    
        } catch (Exception e) {
            return new ResponseWrapper<List<Model>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), models).response();            
        }
    }

    private List<Model> mockModels() {
        List<Model> models;
        models = new ArrayList<Model>();
        Model model1 = new Model();
        Map<String, String> fields1 = new HashMap<String, String>();
        
        fields1.put("name", "string");
        fields1.put("description", "text");
        fields1.put("price", "decimal");
        fields1.put("category", "string");
        model1.setFields(fields1);
        model1.setName("products");
        
        Model model2 = new Model();
        Map<String, String> fields2 = new HashMap<String, String>();
        
        fields2.put("name", "string");
        fields2.put("artist", "string");
        fields2.put("gender", "string");
        fields2.put("year", "integer");
        model2.setFields(fields2);
        model2.setName("albums");
        
        models.add(model1);
        models.add(model2);
        return models;
    }
     
    @RequestMapping(method=RequestMethod.GET,value="/models/{key}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> getRecord(@PathVariable(value="key") String key) {
        Model model = null;
        try {
            model = modelRepository.find(key);
            if (model==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }    
            return new ResponseWrapper<Model>(HttpStatus.OK, "Success!", model).response();                
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/models",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> insertRecord(@RequestBody Model model) {        
        Model result;
        try {                        
            if (modelRepository.find(model.getName())!=null) {
                return new ResponseWrapper<Model>(HttpStatus.CONFLICT, "Record already exists.", model).response();
            }
            result = modelRepository.insert(model);
            return new ResponseWrapper<Model>(HttpStatus.CREATED, "Success!", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }
    }

    @RequestMapping(method=RequestMethod.PUT,value="/models",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> updateRecord(@RequestBody Model model){
        Model result = null;
        try {
            if (modelRepository.find(model.getName())==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = modelRepository.update(model);
            return new ResponseWrapper<Model>(HttpStatus.OK, "Success!", result).response();
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }        
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/models/{key}")
    public ResponseEntity<ResponseWrapper<Model>> deleteRecord(@PathVariable(value="key") String key) {        
        Model result = null;
        try {            
            if (modelRepository.find(key)==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = modelRepository.delete(key);
            return new ResponseWrapper<Model>(HttpStatus.OK, "Success!", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
}
