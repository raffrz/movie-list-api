package com.farias.movielist.movielistapi.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

public class MovieImportDTO {

    @Getter
    final List<MovieImportItemDTO> items;

    public MovieImportDTO() {
        this.items = new ArrayList<>();
    }

    public MovieImportDTO(@NonNull List<MovieImportItemDTO> items) {
        this.items = items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
