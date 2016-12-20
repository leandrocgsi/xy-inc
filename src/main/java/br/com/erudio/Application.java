package br.com.erudio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ModelRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of models
        repository.save(new Model("Alice", "Smith"));
        repository.save(new Model("Bob", "Smith"));

        // fetch all models
        System.out.println("Models found with findAll():");
        System.out.println("-------------------------------");
        for (Model model : repository.findAll()) {
            System.out.println(model);
        }
        System.out.println();

        // fetch an individual model
        System.out.println("Model found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Models found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Model model : repository.findByLastName("Smith")) {
            System.out.println(model);
        }

    }

}