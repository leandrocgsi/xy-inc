package br.com.erudio.controller.entities;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class Model implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String _id;
    private String name;
    private HashMap<String, DataDictionary> attributes;

    public Model() {}

    public Model(String _id, String name, HashMap<String, DataDictionary> attributes) {
        this._id = _id;
        this.name = name;
        this.attributes = attributes;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public HashMap<String, DataDictionary> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(HashMap<String, DataDictionary> attributes) {
        this.attributes = attributes;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Model other = (Model) obj;
        if (_id == null) {
            if (other._id != null) return false;
        } else if (!_id.equals(other._id)) return false;
        if (attributes == null) {
            if (other.attributes != null) return false;
        } else if (!attributes.equals(other.attributes)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Model [_id=" + _id + ", name=" + name + ", attributes=" + attributes + "]";
    }
}
