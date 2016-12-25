package br.com.erudio.endpoints;

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

import br.com.erudio.repository.GenericModelRepository;
import br.com.erudio.response.ResponseWrapper;

@RestController
public class GenericModelEndpoint {

    @Autowired
    private GenericModelRepository genericModelRepository;
    
    @RequestMapping(method=RequestMethod.GET,value="/model/{modelName}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Map<String, Object>>>> getAllRecords(@PathVariable(value="modelName") String modelName) {
        List<Map<String, Object>> results = null;
        try {
            genericModelRepository.setModelName(modelName);
            results = genericModelRepository.findAll();
            if (results==null) {
                return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }    
            return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.OK, "Success!", results).response();            
        } catch (Exception e) {
            return new ResponseWrapper<List<Map<String, Object>>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), results).response();
        }
    }
     
    @RequestMapping(method=RequestMethod.GET,value="/model/{modelName}/{key}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> getRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="key") String key) {
        Map<String, Object> result = null;
        try {
    
            genericModelRepository.setModelName(modelName);
            result = (Map<String, Object>) genericModelRepository.find(key);
            if (result==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }        
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Success!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/model/{modelName}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> insertRecord(@PathVariable(value="modelName") String modelName, @RequestBody Map<String, Object> data) {        
        Map<String, Object> result = null;
        try {        
            genericModelRepository.setModelName(modelName);
            result = genericModelRepository.insert((Map<String, Object>) data);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.CREATED, "Success!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }
    }

    @RequestMapping(method=RequestMethod.PUT,value="/model/{modelName}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> updateRecord(@PathVariable(value="modelName") String modelName, @RequestBody Map<String, Object> data){
        Map<String, Object> result = null;
        try {        
            genericModelRepository.setModelName(modelName);
            if (genericModelRepository.find(data.get("_id").toString())==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = genericModelRepository.update(data);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Success!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/model/{modelName}/{key}")
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> deleteRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="key") String key) {        
        Map<String, Object> result = null;
        try {                        
            genericModelRepository.setModelName(modelName);
            if (genericModelRepository.find(key)==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = genericModelRepository.delete(key);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Success!", result).response();            
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
    

}
