package com.farias.movielist.movielistapi.application.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.farias.movielist.movielistapi.domain.parser.CsvParser;
import com.farias.movielist.movielistapi.domain.parser.CsvParserException;
import com.farias.movielist.movielistapi.domain.service.MovieService.ImportMoviesInput;
import com.farias.movielist.movielistapi.domain.service.MovieService.ImportMoviesInputItem;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvParserOpenCsv implements CsvParser {

    @Override
    public ImportMoviesInput parseCsvToMoviesInput(InputStream inputStream) throws CsvParserException {
        List<ImportMoviesInputItem> moviesInput = new CsvToBeanBuilder<ImportMoviesInputItem>(
                new InputStreamReader(inputStream))
                .withType(ImportMoviesInputItem.class)
                .withSeparator(';')
                .build()
                .parse();
        return new ImportMoviesInput(moviesInput);
    }

}
