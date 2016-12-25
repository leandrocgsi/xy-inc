package br.com.erudio.converter;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class Converter {
	
	protected void createCollection(DB database, String name, boolean autoIndexID) {
		if (!database.collectionExists(name)) {
			database.createCollection(name,BasicDBObjectBuilder.start().add("autoIndexID", autoIndexID).get());
		}
	}

	protected DBObject convertMapToMongo(Map<String, String> map) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			builder
				.add(
					entry.getKey(),
					BasicDBObjectBuilder
							.start()
							.add("type", entry.getValue().toLowerCase())
							.get())
				.get();
		}
		return builder.get();
	}

	protected Map<String, String> convertMongoToMap(DBObject object) {
		Map<String, String> map = new HashMap<String, String>();
		DBObject dbObject = null;
		for (String field : object.keySet()) {
			dbObject = (DBObject) object.get(field);
			if (dbObject != null) {
				map.put(field, (String) dbObject.get("type"));
			}
		}
		return map;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public void convertObjectIdToString(Map map) {
		ObjectId id = (ObjectId) map.get("_id");
		map.remove("_id");
		map.put("_id",id.toString());
	}

}
