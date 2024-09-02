package com.farias.movielist.movielistapi.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.movielist.movielistapi.application.controller.response.AwardWinnerIntervalResponse;
import com.farias.movielist.movielistapi.domain.service.ProducerService;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping(value = "/awardWinningInterval", produces = "application/json")
    public AwardWinnerIntervalResponse getAwardInterval() {
        var minAwardWinnerIntervals = producerService.findProducersWithMinProductionInterval();
        var maxAwardWinnerIntervals = producerService.findProducersWithMaxProductionInterval();

        return new AwardWinnerIntervalResponse(minAwardWinnerIntervals, maxAwardWinnerIntervals);
    }

}
