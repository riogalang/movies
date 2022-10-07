package com.movies.repository;

import com.movies.entity.Comments;
import com.movies.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    Optional<UserAuthentication> findByEmail (String email);

}
