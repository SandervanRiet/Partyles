package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Optional<Artist> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Artist> findFirstByIdGreaterThanOrderById(int id);
    Optional<Artist> findFirstByOrderByIdDesc();
    Optional<Artist> findFirstByOrderByIdAsc();
    List<Artist> findByIdIn(Integer[] ids);

    @Query("SELECT a FROM Artist a WHERE :word IS NULL OR LOWER(a.artistName) LIKE LOWER(CONCAT('%',:word,'%')) OR LOWER(a.bio) LIKE LOWER(CONCAT('%',:word,'%')) " +
            "OR LOWER(a.portfolio) LIKE LOWER(CONCAT('%',:word,'%')) OR LOWER(a.genre) LIKE LOWER(CONCAT('%',:word,'%'))")
    List<Artist> findByKeyword(@Param("word") String word);


}
