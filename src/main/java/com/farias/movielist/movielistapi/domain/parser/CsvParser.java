package com.farias.movielist.movielistapi.domain.parser;

import java.io.InputStream;

import com.farias.movielist.movielistapi.domain.dto.MovieImportDTO;

public interface CsvParser {

    public MovieImportDTO parseCsvToMoviesInput(InputStream inputStream) throws CsvParserException;
}
