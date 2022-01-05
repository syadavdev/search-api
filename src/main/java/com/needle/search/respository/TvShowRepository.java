package com.needle.search.respository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TvShowRepository extends PagingAndSortingRepository<TvShow, Integer> {

    @Query("select T from TvShow T where T.cast like %:cast% or T.director = :director")
    List<TvShow> findAllByCastIsContainingOrDirector(String cast, String director, Pageable pageable);

}
