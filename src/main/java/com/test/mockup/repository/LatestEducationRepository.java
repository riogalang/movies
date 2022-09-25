package com.test.mockup.repository;

import com.test.mockup.entity.Biodata;
import com.test.mockup.entity.LatestEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatestEducationRepository extends JpaRepository<LatestEducation, Long> {

    List<LatestEducation> findByBiodata (Biodata biodata);
}
