package com.farias.movielist.movielistapi.application.controller.response;

import java.util.List;

import com.farias.movielist.movielistapi.domain.dto.AwardWinnerIntervalDTO;

import lombok.Value;

@Value
public class AwardWinnerIntervalResponse {

    List<AwardWinnerIntervalDTO> min;
    List<AwardWinnerIntervalDTO> max;
}
