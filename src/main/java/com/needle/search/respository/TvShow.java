package com.needle.search.respository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "tv_shows")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TvShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "show_id")
    private String showId;

    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;

    @Column(name = "date_added")
    private String dateAdded;
    @Column(name = "release_year")
    private Integer releaseYear;

    private String rating;
    private String duration;

}
