package br.com.erudio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.erudio.controller.entities.Model;


public interface ModelRepository extends MongoRepository<Model, String> {

    public Model findByFirstName(String firstName);
    public List<Model> findByLastName(String lastName);

}