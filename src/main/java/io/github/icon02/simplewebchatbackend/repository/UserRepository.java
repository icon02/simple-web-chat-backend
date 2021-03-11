package io.github.icon02.simplewebchatbackend.repository;

import io.github.icon02.simplewebchatbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM USER ORDER BY RAND() LIMIT 1")
    User getRandom();

    @Query("select u from User u where u.username = :username")
    User getByUsername(@Param("username") String username);

    @Query(nativeQuery = true, value="SELECT * FROM USER u WHERE u.id != ?1 order by u.username") // TODO sort by messages
    List<User> getContacts(Long userId);
}
