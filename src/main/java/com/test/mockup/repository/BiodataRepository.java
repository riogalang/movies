package com.test.mockup.repository;

import com.test.mockup.entity.Biodata;
import com.test.mockup.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Long> {

    Optional<Biodata> findByUserAuthentication (UserAuthentication userAuthentication);
}
