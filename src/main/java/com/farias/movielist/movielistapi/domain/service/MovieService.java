package com.farias.movielist.movielistapi.domain.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farias.movielist.movielistapi.domain.entity.Movie;
import com.farias.movielist.movielistapi.domain.parser.CsvParser;
import com.farias.movielist.movielistapi.domain.repository.MovieRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NonNull;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private ProducerService producerService;
    private CsvParser csvParser;
    private String moviesImportFile;

    @Autowired
    public MovieService(MovieRepository movieRepository,
            ProducerService producerService,
            CsvParser csvParser,
            @Value("${movies.import.file}") String moviesImportFile) {
        this.movieRepository = movieRepository;
        this.producerService = producerService;
        this.csvParser = csvParser;
        this.moviesImportFile = moviesImportFile;
    }

    @Transactional
    public void importMovies() {
        try {
            InputStream inputStream = new FileInputStream(moviesImportFile);
            ImportMoviesInput moviesInput = csvParser.parseCsvToMoviesInput(inputStream);
            if (moviesInput.isEmpty()) {
                return;
            }
            moviesInput.items.forEach(item -> {
                var movie = new Movie(Integer.valueOf(item.year), item.title, item.studios, item.getWinnerAsBoolean());
                movieRepository.save(movie);

                item.getProducersAsStream()
                        .map(producerService::getProducerByNameOrCreateNew)
                        .forEach(movie::addProducer);
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException("o arquivo de importação dos filmes não foi encontrado", e);
        }
    }

    public static class ImportMoviesInput {
        final List<ImportMoviesInputItem> items;

        public ImportMoviesInput() {
            this.items = new ArrayList<>();
        }

        public ImportMoviesInput(@NonNull List<ImportMoviesInputItem> items) {
            this.items = items;
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }
    }

    @Data
    public static class ImportMoviesInputItem {

        @CsvBindByName(column = "year")
        String year;

        @CsvBindByName(column = "title")
        String title;

        @CsvBindByName(column = "studios")
        String studios;

        @CsvBindByName(column = "producers")
        String producers;

        @CsvBindByName(column = "winner")
        String winner;

        @JsonIgnore
        Stream<String> getProducersAsStream() {
            return Arrays.stream(producers.split(","))
                    .map(String::trim)
                    .distinct();
        }

        @JsonIgnore
        public boolean getWinnerAsBoolean() {
            return winner != null && winner.equals("yes") ? true : false;
        }
    }

    @Transactional
    public void printMovies() {
        movieRepository.findAll().forEach(System.out::println);
    }
}
