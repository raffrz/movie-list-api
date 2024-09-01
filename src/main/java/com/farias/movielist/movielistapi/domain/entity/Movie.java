package com.farias.movielist.movielistapi.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "_year")
	private Integer year;
	
	private String title;
	
	private String studios;
	
	@ManyToMany
    @JoinTable(
        name = "movie_producer",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
	private Set<Producer> producers;
	
	private boolean winner;

	public Movie(Integer year, String title, String studios, boolean winner) {
		super();
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.winner = winner;
		this.producers = new HashSet<>();
	}
	
	public void addProducer(Producer producer) {
		this.producers.add(producer);
	}
	
	

}
