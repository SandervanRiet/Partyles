package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
    @Query("SELECT v FROM Venue v where v.capacity >= :max")
    Iterable<Venue> findByCapacitySmallerthan(@Param("max")int capacity);


    @Query("SELECT v FROM Venue v WHERE v.capacity <=?2 and v.capacity >= ?1")
    Iterable<Venue> findByCapacityBetween(int min, int max);
    Iterable<Venue> findByCapacityIsBetween(int min, int max);
    @Query("SELECT v FROM Venue v WHERE v.capacity >= ?1")
    Iterable<Venue> findByCapacityBiggerThanEqual(int capacity);
    @Query("SELECT v FROM Venue v WHERE :min IS NULL OR v.capacity >= :min")
    List<Venue> findByBigCapacity(@Param("min") Integer min);
    @Query("SELECT v FROM Venue v")
    Iterable<Venue> findAllVenues();
    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR v.capacity >= :min) AND (:max IS NULL OR v.capacity <= :max) and (:maxd IS NULL OR v.distanceFromPublicTransportInKm <= :maxd) and ()")
    Iterable<Venue> findAllVenues(@Param("min")Integer minimum,
                             @Param("max")Integer maximum,
                             @Param("maxd")Double maximumd,
                                  );



    Optional<Venue> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Venue> findFirstByIdGreaterThanOrderById(int id);
    Optional<Venue> findFirstByOrderByIdDesc();
    Optional<Venue> findFirstByOrderByIdAsc();


    Iterable<Venue> findByCapacityIsGreaterThanEqual(Integer capacity);

}

