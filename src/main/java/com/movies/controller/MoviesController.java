package com.movies.controller;

import com.movies.dto.*;
import com.movies.entity.Movies;
import com.movies.entity.UserAuthentication;
import com.movies.service.MoviesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @ApiOperation("Get into Home / Dashboard")
    @GetMapping("/dashboard")
    public DashboardResponse dashBoard(@RequestBody GetUserFavoriteMoviesRequest getUserFavoriteMoviesRequest){

        return DashboardResponse.builder()
                .latest(moviesService.getLatestMovies())
                .popularMovies(moviesService.getPopularMovies())
                .userFavorite(moviesService.getUserFavoriteMovies(getUserFavoriteMoviesRequest))
                .build();
    }

    @ApiOperation("Search for Movies with Keyword")
    @GetMapping("/movie-search")
    public List<Movies> movieSearch(@RequestBody GetMoviesSearchRequest request){
        return moviesService.getSearchMovies(request);
    }

    @ApiOperation("Get Movie Detail")
    @GetMapping("/movie-detail")
    public GetMoviesDetailResponse movieDetail(@RequestBody GetMoviesDetailRequest request){
        return moviesService.getDetailsMovies(request);
    }

    @ApiOperation("Comment on movie that you watch")
    @PostMapping("/movie-comment")
    public void movieDetail(@RequestBody CommentMoviesRequest request){
        moviesService.commentMovies(request);
    }



}
