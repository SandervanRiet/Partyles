package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    Optional<Animal> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Animal> findFirstByIdGreaterThanOrderById(int id);
    Optional<Animal> findFirstByOrderByIdDesc();
    Optional<Animal> findFirstByOrderByIdAsc();
}
