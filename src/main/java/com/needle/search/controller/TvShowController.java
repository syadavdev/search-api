package com.needle.search.controller;

import com.needle.search.respository.TvShow;
import com.needle.search.respository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TvShowController {

    @Autowired
    private TvShowRepository repository;

    /**
     *
     * @param actor - actor from cast section of csv data
     * @param director - director name of the movie
     * @param page - page number of pageable record, value start from 1
     * @param size - size of records in a page default value 10
     * @param sort - sorting params value separated by ':' character like 'fieldName:ASC' or 'fieldName:DESC'
     * @return number of records based on param passed
     */
    @GetMapping("/search")
    public ResponseEntity<List<TvShow>> search(@RequestParam(value = "actor", required = false) String actor,
                                               @RequestParam(value = "director", required = false) String director,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sort", defaultValue = "releaseYear:desc") String sort){
        String[] split = sort.split(":");
        if(split.length != 2)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String fieldName = split[0];
        String direction = split[1];
        return ResponseEntity.status(HttpStatus.OK).body(
                repository.findAllByCastIsContainingOrDirector(
                        actor,
                        director,
                        PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(direction), fieldName))
                )
        );
    }

}
