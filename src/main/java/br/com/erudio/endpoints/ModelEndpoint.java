package br.com.erudio.endpoints;

import java.util.List;

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
    private ModelRepository dao;
    
    @RequestMapping(method=RequestMethod.GET,value="/models",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Model>>> getAllRecords() {
        List<Model> models = null;
        try {
            models = dao.findAll();
            if (models==null) {
                return new ResponseWrapper<List<Model>>(HttpStatus.NOT_FOUND, "http.status.not_found", null).response();                
            }        
            return new ResponseWrapper<List<Model>>(HttpStatus.OK, "http.status.ok", models).response();    
        } catch (Exception e) {
            return new ResponseWrapper<List<Model>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), models).response();            
        }
    }
     
    @RequestMapping(method=RequestMethod.GET,value="/models/{key}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> getRecord(@PathVariable(value="key") String key) {
        Model model = null;
        try {
            model = dao.find(key);
            if (model==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "http.status.not_found", null).response();
            }    
            return new ResponseWrapper<Model>(HttpStatus.OK, "http.status.ok", model).response();                
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/models",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> insertRecord(@RequestBody Model model) {        
        Model result;
        try {                        
            if (dao.find(model.getName())!=null) {
                return new ResponseWrapper<Model>(HttpStatus.CONFLICT, "http.status.conflict", model).response();
            }
            result = dao.insert(model);
            return new ResponseWrapper<Model>(HttpStatus.CREATED, "http.status.ok", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }
    }

    @RequestMapping(method=RequestMethod.PUT,value="/models",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> updateRecord(@RequestBody Model model){
        Model result = null;
        try {
            if (dao.find(model.getName())==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "http.status.not_found", null).response();
            }
            result = dao.update(model);
            return new ResponseWrapper<Model>(HttpStatus.OK, "http.status.ok", result).response();
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }        
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/models/{key}")
    public ResponseEntity<ResponseWrapper<Model>> deleteRecord(@PathVariable(value="key") String key) {        
        Model result = null;
        try {            
            if (dao.find(key)==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "http.status.not_found", null).response();
            }
            result = dao.delete(key);
            return new ResponseWrapper<Model>(HttpStatus.OK, "http.status.ok", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
}
