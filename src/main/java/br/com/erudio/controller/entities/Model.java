package br.com.erudio.controller.entities;

import org.springframework.data.annotation.Id;

public class Model {
    
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Model() {}

    public Model(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
