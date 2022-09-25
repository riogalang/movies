package com.test.mockup.repository;

import com.test.mockup.entity.Biodata;
import com.test.mockup.entity.JobExperiences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExperiencesRepository extends JpaRepository<JobExperiences, Long> {
    List<JobExperiences> findByBiodata (Biodata biodata);
}
