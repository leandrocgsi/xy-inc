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

import com.wordnik.swagger.annotations.ApiOperation;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;
import br.com.erudio.response.ResponseWrapper;

@RestController
@RequestMapping("/api/model")
public class ModelEndpoint {

    @Autowired
    private ModelRepository modelRepository;
    
    @ApiOperation(value = "Find all models." )
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Model>>> getAllRecords() {
        List<Model> models = null;
        try {
            models = modelRepository.findAll();
            if (models==null) {
                return new ResponseWrapper<List<Model>>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }        
            return new ResponseWrapper<List<Model>>(HttpStatus.OK, "Success!", models).response();    
        } catch (Exception e) {
            return new ResponseWrapper<List<Model>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), models).response();            
        }
    }

    @ApiOperation(value = "Find a model by ID" )
    @RequestMapping(method=RequestMethod.GET,value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Model>> getRecord(@PathVariable(value="id") String id) {
        Model model = null;
        try {
            model = modelRepository.find(id);
            if (model==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }    
            return new ResponseWrapper<Model>(HttpStatus.OK, "Success!", model).response();                
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), model).response();
        }
    }
    
    @ApiOperation(value = "Create a new person" )
    @RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Update an existing person")
    @RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
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
    
    @ApiOperation(value = "Delete person by ID" )
    @RequestMapping(method=RequestMethod.DELETE,value="/{id}")
    public ResponseEntity<ResponseWrapper<Model>> deleteRecord(@PathVariable(value="id") String id) {        
        Model result = null;
        try {            
            if (modelRepository.find(id)==null) {
                return new ResponseWrapper<Model>(HttpStatus.NOT_FOUND, "Register not found.", null).response();
            }
            result = modelRepository.delete(id);
            return new ResponseWrapper<Model>(HttpStatus.OK, "Success!", result).response();    
    
        } catch (Exception e) {
            return new ResponseWrapper<Model>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), result).response();
        }        
    }
}
