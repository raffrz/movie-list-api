package com.farias.movielist.movielistapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farias.movielist.movielistapi.domain.dto.AwardWinnerIntervalDTO;
import com.farias.movielist.movielistapi.domain.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Optional<Producer> findByName(String name);

    @Query("SELECT MAX(m1.year - m2.year) FROM Producer p " +
            "JOIN p.movies m1 JOIN p.movies m2 " +
            "WHERE m1.winner = true AND m2.winner = true")
    Integer findMaxAwardWinningProductionInterval();

    @Query("SELECT new com.farias.movielist.movielistapi.domain.dto.AwardWinnerIntervalDTO(" +
            "p.name, " +
            "MAX(m1.year - m2.year), " +
            "MIN(m1.year), " +
            "MAX(m2.year)) " +
            "FROM Producer p " +
            "JOIN p.movies m1 " +
            "JOIN p.movies m2 " +
            "WHERE m1.winner = true AND m2.winner = true " +
            "GROUP BY p.id, p.name " +
            "HAVING MAX(m1.year - m2.year) = :maxInterval")
    List<AwardWinnerIntervalDTO> findProducersWithMaxAwardWinningInterval(@Param("maxInterval") Integer maxInterval);

    @Query("SELECT MIN(ABS(m1.year - m2.year)) FROM Producer p " +
            "JOIN p.movies m1 JOIN p.movies m2 " +
            "WHERE m1.winner = true AND m2.winner = true " +
            "AND m1.year > m2.year")
    Integer findMinAwardWinningProductionInterval();

    @Query("SELECT new com.farias.movielist.movielistapi.domain.dto.AwardWinnerIntervalDTO(" +
            "p.name, " +
            "MIN(ABS(m1.year - m2.year)), " +
            "m2.year, " +
            "m1.year) " +
            "FROM Producer p " +
            "JOIN p.movies m1 " +
            "JOIN p.movies m2 " +
            "WHERE m1.winner = true AND m2.winner = true " +
            "AND m1.year > m2.year " +
            "GROUP BY p.id, p.name, m1.year, m2.year " +
            "HAVING MIN(ABS(m1.year - m2.year)) = :maxInterval")
    List<AwardWinnerIntervalDTO> findProducersWithMinAwardWinningInterval(@Param("maxInterval") Integer maxInterval);

}
