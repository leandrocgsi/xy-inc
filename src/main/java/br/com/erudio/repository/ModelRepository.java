package br.com.erudio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.erudio.controller.entities.Model;


public interface ModelRepository extends MongoRepository<Model, String> {

    public Model findByName(String name);
    public Model findById(String name);

}