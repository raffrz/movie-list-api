package com.farias.movielist.movielistapi.domain.dto;

import java.util.Arrays;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class MovieImportItemDTO {

    @CsvBindByName(column = "year")
    private String year;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "studios")
    private String studios;

    @CsvBindByName(column = "producers")
    private String producers;

    @CsvBindByName(column = "winner")
    private String winner;

    @JsonIgnore
    public Stream<String> getProducersAsStream() {
        var normalizedProducers = producers.replaceAll("and,", ",").replaceAll("and", ",");
        return Arrays.stream(normalizedProducers.split(","))
                .map(String::trim)
                .distinct();
    }

    @JsonIgnore
    public boolean getWinnerAsBoolean() {
        return winner != null && winner.equals("yes") ? true : false;
    }
}
