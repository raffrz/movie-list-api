package com.farias.movielist.movielistapi.domain.parser;

import java.io.InputStream;

import com.farias.movielist.movielistapi.domain.service.MovieService.ImportMoviesInput;

public interface CsvParser {

    public ImportMoviesInput parseCsvToMoviesInput(InputStream inputStream) throws CsvParserException;
}
