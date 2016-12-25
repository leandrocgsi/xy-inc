package br.com.erudio.builders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.response.ResponseWrapper;

public class ModelBuilder {

	private Model model = new Model();
	
	public ModelBuilder(Model model) {
		this.model = model;
	}
	
	public static ModelBuilder newModel(String name){
		Model modelTest = create(name);
		return new ModelBuilder(modelTest);
	}
	
	private static Model create(String name) {
		Model model = new Model();
		model.setName(name);
		return model;
	}
	
	public ModelBuilder addField(String field, String type){
		Map<String, String> fields = this.model.getFields();
		if (fields==null){
			this.model.setFields(new HashMap<String, String>());
		}
		this.model.getFields().put(field, type);
		return this;
	}
		
	public Model build(){
		return this.model;
	}
	
	public String buildStringJson() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this.model);
	}
	
	@SuppressWarnings("unchecked")
	public static ResponseWrapper<Object> buildModelFromJson(String json) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return ((ResponseWrapper<Object>) mapper.readValue(json, ResponseWrapper.class));
	}
}
