package com.movies.repository;

import com.movies.entity.Biodata;
import com.movies.entity.Category;
import com.movies.entity.Movies;
import com.movies.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

    List<Movies> findByCategory (Category category);

    @Query(value = "select m from Movies m where m.original_title LIKE '%:keyword%';")
    List <Movies> findBySearch (@Param("keyword")String keyword);

}
