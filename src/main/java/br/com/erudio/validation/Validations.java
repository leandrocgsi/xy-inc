package br.com.erudio.validation;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import br.com.erudio.datatype.DataType;
import br.com.erudio.repository.ModelRepository;

@Component
public class Validations {

    @Autowired
    private DataType types;

    @Autowired
    private ModelRepository modelRepository;

    @SuppressWarnings("rawtypes")
    public void schema(Map<String, String> schema, Map data) {
        if (schema == null || schema.size() == 0 || data == null    || data.size() == 0) {
            throw new IllegalArgumentException("schema.empty");
        }
        
        int dataSize = (data.containsKey("_id"))? data.size()-1 : data.size();
        
        if (dataSize!=schema.size()){
            throw new IllegalArgumentException("schema.invalid");
        }
        
        Class<?> javaType = null;
        String mapType;
        for (Object field : data.keySet()) {

            if ("_id".equalsIgnoreCase((String) field)){
                continue;
            }
            
            mapType = schema.get((String) field);
            if (mapType == null) {
                throw new IllegalArgumentException("field.not_exist.schema" + field);
            }

            javaType = types.getType(mapType);
            if (javaType == null) {
                throw new IllegalArgumentException("field.invalid.datatype" + mapType + field);
            }

            try {
                Object object = javaType.cast(data.get(field));
                if (object == null) {
                    throw new IllegalArgumentException("field.invalid.datatype" + mapType + field);
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("field.invalid.datatype" + mapType + field);            }
        }
    }
    
    public void checkModelParam(String modelName) throws Exception{
        if (modelName==null || modelName.length()==0){
            throw new IllegalArgumentException("model.not_set" + modelName);
        }
        if (modelRepository.find(modelName)==null){
            throw new IllegalArgumentException("model.not_exist" + modelName);
        }
    }
    
    
    @SuppressWarnings("rawtypes")
    public void checkNotFound(DBObject dbResult, Map result) throws SQLException{
        if (dbResult==null){
            throw new SQLException("record.not_found");
        }
        
        result = dbResult.toMap(); 
        if (result==null || result.size()==0){
            throw new SQLException("record.not_found");
        }
    }
    
    public void checkCursor(DBCursor cursor) throws SQLException{
        if (cursor==null){
            throw new SQLException("record.not_found");
        }
    }
}
