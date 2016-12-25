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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import br.com.erudio.repository.GenericModelRepository;
import br.com.erudio.response.ResponseWrapper;

@Api(value = "Dinamic Endpoints")
@RestController
@RequestMapping("/api")
public class GenericModelEndpoint {

    @Autowired
    private GenericModelRepository genericModelRepository;
    
    @ApiOperation(value = "Find all dinamic resource by modelName" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.GET,value="/{modelName}",produces=MediaType.APPLICATION_JSON_VALUE)
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
     
    @ApiOperation(value = "Find a dinamic resource by modelName and ID" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.GET,value="/{modelName}/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> getRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="id") String id) {
        Map<String, Object> result = null;
        try {
    
            genericModelRepository.setModelName(modelName);
            result = (Map<String, Object>) genericModelRepository.find(id);
            if (result==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }        
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Success!", result).response();
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }
    }
    
    @ApiOperation(value = "Create an new dinamic resource")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.POST,value="/{modelName}",consumes=MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Update an existing dinamic resource")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.PUT,value="/{modelName}",consumes=MediaType.APPLICATION_JSON_VALUE)
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
    
    @ApiOperation(value = "Delete a dinamic resource by ID" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method=RequestMethod.DELETE,value="/{modelName}/{id}")
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> deleteRecord(@PathVariable(value="modelName") String modelName, @PathVariable(value="id") String id) {        
        Map<String, Object> result = null;
        try {                        
            genericModelRepository.setModelName(modelName);
            if (genericModelRepository.find(id)==null) {
                return new ResponseWrapper<Map<String, Object>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = genericModelRepository.delete(id);
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.OK, "Success!", result).response();            
        } catch (Exception e) {
            return new ResponseWrapper<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
    

}
