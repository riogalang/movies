package com.movies.repository;

import com.movies.entity.Category;
import com.movies.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Category, Long> {

    Participant findByEmail(String participantEmail);
}
