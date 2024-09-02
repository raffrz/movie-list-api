package com.farias.movielist.movielistapi.application.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.farias.movielist.movielistapi.domain.dto.MovieImportDTO;
import com.farias.movielist.movielistapi.domain.dto.MovieImportItemDTO;
import com.farias.movielist.movielistapi.domain.parser.CsvParser;
import com.farias.movielist.movielistapi.domain.parser.CsvParserException;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvParserOpenCsvImpl implements CsvParser {

    @Override
    public MovieImportDTO parseCsvToMovieImportDTO(InputStream inputStream) throws CsvParserException {
        try {
            List<MovieImportItemDTO> movieItems = new CsvToBeanBuilder<MovieImportItemDTO>(
                    new InputStreamReader(inputStream))
                    .withType(MovieImportItemDTO.class)
                    .withSeparator(';')
                    .build()
                    .parse();
            return new MovieImportDTO(movieItems);
        } catch (Exception e) {
            throw new CsvParserException(e);
        }
    }

}
