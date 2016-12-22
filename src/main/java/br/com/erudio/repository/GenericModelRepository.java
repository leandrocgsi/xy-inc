package br.com.erudio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.erudio.controller.entities.Model;


public interface GenericModelRepository extends MongoRepository<Model, String> {


}