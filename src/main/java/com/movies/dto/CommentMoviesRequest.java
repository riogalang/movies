package com.movies.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentMoviesRequest {

    private Long id;
    private String email;
    private String review;
}
