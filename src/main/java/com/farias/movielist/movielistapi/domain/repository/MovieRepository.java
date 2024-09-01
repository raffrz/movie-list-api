package com.farias.movielist.movielistapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farias.movielist.movielistapi.domain.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
