package io.github.icon02.simplewebchatbackend.repository;

import io.github.icon02.simplewebchatbackend.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    @Query("select p from ProfileImage p where p.userId = :id")
    ProfileImage getForUser(@Param("id") Long userId);
}
