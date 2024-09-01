package com.farias.movielist.movielistapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.movielist.movielistapi.domain.entity.Producer;
import com.farias.movielist.movielistapi.domain.repository.ProducerRepository;

import jakarta.transaction.Transactional;

@Service
public class ProducerService {

    ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Transactional
    public Producer getProducerByNameOrCreateNew(String producerName) {
        return producerRepository.findByName(producerName).orElseGet(() -> {
            var producer = new Producer(producerName);
            producerRepository.save(producer);
            return producer;
        });
    }
}
