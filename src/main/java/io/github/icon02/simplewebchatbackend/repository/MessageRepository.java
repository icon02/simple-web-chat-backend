package io.github.icon02.simplewebchatbackend.repository;

import io.github.icon02.simplewebchatbackend.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m where (m.fromUserId = :userId1 or m.fromUserId = :userId2) and (m.toUserId = :userId1 or m.toUserId = :userId2) order by m.timestamp desc")
    List<Message> getForUser(@Param("userId1") Long userId1, @Param("userId2") Long userId2, Pageable pageable);
}
