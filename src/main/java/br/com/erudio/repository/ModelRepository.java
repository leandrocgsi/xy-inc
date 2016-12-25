package br.com.erudio.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.converter.Converter;
import br.com.erudio.repository.interfaces.Repository;

@Component
public class ModelRepository extends Converter implements Repository<Model>{

	@Autowired
	private MongoTemplate template;
	
	@Override
	public Model insert(Model model) throws SQLException{
		String modelName = model.getName().toLowerCase();
		
		createCollection(template.getDb(), "models", false);
		createCollection(template.getDb(), modelName,true);
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.append("_id", modelName);
		dbobject.append("fields", convertMapToMongo(model.getFields()));		
		
		template.getDb().getCollection("models").save(dbobject);		
	
		return find(modelName);
	}
	
	@Override
	public Model update(Model model) throws SQLException {
		String modelName = model.getName().toLowerCase();
		DBObject data = BasicDBObjectBuilder.start().add("fields", convertMapToMongo(model.getFields())).get();
		DBObject filter = BasicDBObjectBuilder.start().add("_id", modelName).get();
		
		template.getDb().getCollection("models").update(filter, data);
		
		return find(modelName);
	}
	
	@Override
	public Model delete(String modelName) throws SQLException {
		Model deletedModel = find(modelName.toLowerCase());
		
		DBObject filter = BasicDBObjectBuilder.start().add("_id", modelName.toLowerCase()).get();
		
		template.getDb().getCollection("models").remove(filter);
		template.getDb().getCollection(modelName.toLowerCase()).drop();
		
		return deletedModel; 
	}	
	
	@Override
	@SuppressWarnings("rawtypes")
	public Model find(String id) throws SQLException {
		Model resultModel = null;
		DBCursor cursor = template.getDb().getCollection("models").find(BasicDBObjectBuilder.start().add("_id", id.toLowerCase()).get());
		if (cursor.hasNext()) {
			Map map = cursor.next().toMap();
			
			if (map==null || map.size()==0){
				throw new SQLException("record.not_found");
			}
			resultModel = new Model();
			resultModel.setName(map.get("_id").toString());
			resultModel.setFields(convertMongoToMap((BasicDBObject) map.get("fields")));
		}
		cursor.close();
		return resultModel;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Model> findAll() throws Exception {
		List<Model> resultModels = new ArrayList<Model>();
		DBCursor cursor = template.getDb().getCollection("models").find();
		Map map = null;
		while (cursor.hasNext()) {
			map = cursor.next().toMap();
			resultModels.add(new Model((String) map.get("_id"),convertMongoToMap((BasicDBObject) map.get("fields"))));
		}
		cursor.close();
		return resultModels;
	}
	
}
