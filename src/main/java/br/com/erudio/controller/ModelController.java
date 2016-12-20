package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;

@RestController
@RequestMapping("/modelo/")
public class ModelController {

    @Autowired
    private ModelRepository repository;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{modelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Model get(@PathVariable(value = "modelId") String modelId) {
        return repository.findOne(modelId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Model> findAll() {
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Model create(@RequestBody Model model) {
        return repository.save(model);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Model update(@RequestBody Model model) {
        return repository.insert(model);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{modelId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "modelId") String modelId) {
        repository.delete(modelId);
    }

}
