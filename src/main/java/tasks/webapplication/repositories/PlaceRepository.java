package tasks.webapplication.repositories;

import entities.Place;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByOwnerId(User ownerId);
}