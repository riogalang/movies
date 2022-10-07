package com.movies.service;

import com.movies.dto.*;
import com.movies.entity.*;
import com.movies.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Slf4j
@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public List<Movies> getPopularMovies() {

        List<Movies> movies = new ArrayList<>();

        var allMovies = moviesRepository.findAll();

        for (var movie : allMovies ){
            if (movie.getPopularity() > 7) movies.add(movie);
        }
        return movies;
    }

    public List<Movies> getSearchMovies(GetMoviesSearchRequest request) {

        var allMovies = moviesRepository.findBySearch(request.getKeyword());
        return new ArrayList<>(allMovies);
    }

    public List<Movies> getUserFavoriteMovies(GetUserFavoriteMoviesRequest request) {

        List<Movies> movies = new ArrayList<>();
        List<UserFavorite> userFavorites;

        var userAuthentication = userAuthenticationRepository.findByEmail(request.getEmail());
        if (userAuthentication.isPresent()) {
            userFavorites = userFavoriteRepository.findByUserAuthentication(userAuthentication.get());
        }else throw new RuntimeException("User tidak ditemukan");

        for (var userFav : userFavorites){
            movies.add(userFav.getMovies());
        }
        return movies;
    }

    public List<Movies> getLatestMovies() {

        List<Movies> movies = new ArrayList<>();

        var allMovies = moviesRepository.findAll();

        for (var movie : allMovies ){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            Date dateBefore = cal.getTime();
            if (movie.getReleaseDate().after(dateBefore)) movies.add(movie);
        }
        return movies;
    }

    public GetMoviesDetailResponse getDetailsMovies(GetMoviesDetailRequest request) {

        var movie = moviesRepository.findById(request.getId());
        if (movie.isEmpty()) throw new RuntimeException("Movie tidak ditemukan");
        var recommendation = moviesRepository.findByCategory(movie.get().getCategory());

        List<Movies> movies = new ArrayList<>(recommendation);
        return GetMoviesDetailResponse
                .builder()
                .recommendation(movies)
                .movies(movie.get())
                .build();
    }

    @Transactional
    public void commentMovies(CommentMoviesRequest request) {

        var userAuth = userAuthenticationRepository.findByEmail(request.getEmail());

        var movie = moviesRepository.findById(request.getId());
        if (movie.isPresent() && userAuth.isPresent())
            commentRepository.save(Comments.builder()
                    .review(request.getReview())
                    .movies(movie.get())
                    .userAuthentication(userAuth.get())
                    .build());
        else throw new RuntimeException("Data tidak ditemukan");
    }




}
