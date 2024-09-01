package com.farias.movielist.movielistapi.domain.parser;

public class CsvParserException extends RuntimeException {

    public CsvParserException(Throwable e) {
        super("Erro ao converter o arquivo CSV", e);
    }

    public CsvParserException() {
        super("Erro ao converter o arquivo CSV");
    }
}
