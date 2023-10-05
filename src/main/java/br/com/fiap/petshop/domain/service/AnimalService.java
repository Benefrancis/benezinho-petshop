package br.com.fiap.petshop.domain.service;

import br.com.fiap.petshop.Main;
import br.com.fiap.petshop.domain.entity.animal.Animal;
import br.com.fiap.petshop.domain.repository.AnimalRepository;
import br.com.fiap.petshop.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class AnimalService implements Service<Animal, Long> {

    private static final AtomicReference<AnimalService> instance = new AtomicReference<>();

    private AnimalRepository repository;

    private AnimalService(AnimalRepository repository) {

        this.repository = repository;
    }

    public static AnimalService build() {
        AnimalService result = instance.get();
        if (Objects.isNull( result )) {
            EntityManagerFactory factory = EntityManagerFactoryProvider.of( Main.PERSISTENCE_UNIT ).provide();
            EntityManager manager = factory.createEntityManager();

            AnimalRepository repository = AnimalRepository.build( manager );

            AnimalService service = new AnimalService( repository );
            if (instance.compareAndSet( null, service )) {
                result = service;
            } else {
                result = instance.get();
            }
        }
        return result;
    }

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public List<Animal> findByName(String texto) {
        return repository.findByTexto( texto );
    }

    @Override
    public Animal persist(Animal animal) {
        return repository.persist( animal );
    }

    @Override
    public Animal update(Long id, Animal animal) {
        var entidade = repository.findById( id );
        if (Objects.isNull( entidade )) return null;
        animal.setId( id );
        return repository.update(animal);
    }

    @Override
    public boolean delete(Animal animal) {
        return repository.delete(animal);
    }
}