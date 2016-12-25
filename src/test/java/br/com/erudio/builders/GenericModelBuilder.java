package br.com.erudio.builders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import br.com.erudio.response.ResponseWrapper;

public class GenericModelBuilder {
	
	private Map<String,Object> map = new HashMap<String, Object>();
	
	public GenericModelBuilder(Map<String,Object> map) {
		this.map = map;		
	}
	
	public static GenericModelBuilder newModel(){
		Map<String,Object> genericModelBuilderTest = create();
		return new GenericModelBuilder(genericModelBuilderTest);		
	}
	
	private static Map<String,Object> create() {
		return new HashMap<String, Object>();
	}
	
	public GenericModelBuilder addData(String field, Object value){
		this.map.put(field, value);
		return this;
	}
	public Map<String,Object> build(){
		return this.map;
	}
	
	public String buildStringJson() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this.map);
	}
	
	@SuppressWarnings("unchecked")
	public static ResponseWrapper<Object> buildModelFromJson(String json) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return ((ResponseWrapper<Object>) mapper.readValue(json, ResponseWrapper.class));
	}	
	
	@SuppressWarnings("rawtypes")
	public static String getId(String collection, int skip){
		DB db = new DB(new MongoClient(MongoConfiguration.HOST,MongoConfiguration.PORT), MongoConfiguration.DATABASE_NAME);
		DBCursor cursor = db.getCollection(collection).find();
		String id = null;
		if (cursor != null && cursor.hasNext()){
			for (int i = 0; i < skip; i++) {
				cursor.next();
			}
			Map map = cursor.next().toMap();
			ObjectId oid = (ObjectId)map.get("_id");
			id = oid.toString();
		}		
		return id;
	}
}
