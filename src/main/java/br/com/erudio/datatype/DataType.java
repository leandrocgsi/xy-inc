package br.com.erudio.datatype;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DataType {

    private Map<String,Class<?>> supportedTypes;
    
    public DataType() {
        supportedTypes = new HashMap<String, Class<?>>();
        
        supportedTypes.put("boolean", Boolean.class);   
        supportedTypes.put("char", String.class);
        supportedTypes.put("character", String.class);
        supportedTypes.put("date", Date.class);
        supportedTypes.put("datetime", Date.class);
        supportedTypes.put("decimal", Double.class);
        supportedTypes.put("double", Double.class);
        supportedTypes.put("int", Integer.class);
        supportedTypes.put("integer", Integer.class);
        supportedTypes.put("long", Long.class);
        supportedTypes.put("string", String.class);
        supportedTypes.put("text", String.class);
        supportedTypes.put("varchar", String.class);
    }
    
    public Class<?> getType(String key){
        return supportedTypes.get(key);
    }
}
