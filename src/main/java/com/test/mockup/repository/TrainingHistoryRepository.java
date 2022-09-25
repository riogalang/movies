package com.test.mockup.repository;

import com.test.mockup.entity.Biodata;
import com.test.mockup.entity.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {

    List<TrainingHistory> findByBiodata (Biodata biodata);
}
