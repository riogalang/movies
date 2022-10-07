package com.movies.repository;

import com.movies.entity.Category;
import com.movies.entity.UserAuthentication;
import com.movies.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

    List<UserFavorite> findByUserAuthentication (UserAuthentication userAuthentication);

}
