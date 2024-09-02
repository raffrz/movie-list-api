package com.farias.movielist.movielistapi.domain.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farias.movielist.movielistapi.domain.dto.MovieImportDTO;
import com.farias.movielist.movielistapi.domain.entity.Movie;
import com.farias.movielist.movielistapi.domain.exception.ServiceException;
import com.farias.movielist.movielistapi.domain.parser.CsvParser;
import com.farias.movielist.movielistapi.domain.repository.MovieRepository;

import jakarta.transaction.Transactional;

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
            MovieImportDTO moviesToImport = csvParser.parseCsvToMovieImportDTO(inputStream);
            if (moviesToImport.isEmpty()) {
                return;
            }
            moviesToImport.getItems().forEach(item -> {
                var movie = new Movie(Integer.valueOf(item.getYear()), item.getTitle(), item.getStudios(),
                        item.getWinnerAsBoolean());
                movieRepository.save(movie);

                item.getProducersAsStream()
                        .map(producerService::getProducerByNameOrCreateNew)
                        .forEach(movie::addProducer);
            });
        } catch (FileNotFoundException e) {
            throw new ServiceException("o arquivo de importação dos filmes não foi encontrado", e);
        }
    }

}
