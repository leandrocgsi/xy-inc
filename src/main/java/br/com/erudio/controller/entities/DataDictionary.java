package br.com.erudio.controller.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class DataDictionary {

    private String type;
    private Boolean nullable;
    private Boolean unique;

    public DataDictionary() {}

    public DataDictionary(String type, Boolean nullable, Boolean unique) {
        this.type = type;
        this.nullable = nullable;
        this.unique = unique;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean isUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }
}