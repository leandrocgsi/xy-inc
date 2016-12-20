package br.com.erudio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.erudio.controller.entities.Model;
import br.com.erudio.repository.ModelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelRepositoryTests {

    @Autowired
    ModelRepository repository;

    Model dave, oliver, carter;

    @Before
    public void setUp() {

        repository.deleteAll();

        dave = repository.save(new Model("Dave", "Matthews"));
        oliver = repository.save(new Model("Oliver August", "Matthews"));
        carter = repository.save(new Model("Carter", "Beauford"));
    }

    @Test
    public void setsIdOnSave() {

        Model dave = repository.save(new Model("Dave", "Matthews"));

        assertThat(dave.id).isNotNull();
    }

    @Test
    public void findsByLastName() {

        List<Model> result = repository.findByLastName("Beauford");

        assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
    }

    @Test
    public void findsByExample() {

        Model probe = new Model(null, "Matthews");

        List<Model> result = repository.findAll(Example.of(probe));

        assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
    }
}
