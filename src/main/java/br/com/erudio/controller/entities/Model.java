package br.com.erudio.controller.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class Model {

    private String name;
    private Map<String, String> fields;
    
    public Model() {}
    
    public Model(String name, Map<String, String> fields) {    
        this.name = name;
        this.fields = fields;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Map<String, String> getFields() {
        return fields;
    }
    
    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
    
}
