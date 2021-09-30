package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.MovieTVSerie;
import com.alkemy.disneydemo.service.MovieTVSerieService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieTVSerieRestController implements Serializable {

    private MovieTVSerieService movieTVSerieService;

    @Autowired
    public MovieTVSerieRestController(MovieTVSerieService theMovieTVSerieService){
        movieTVSerieService = theMovieTVSerieService;
    }

    //expose "/shows" and return list of shows
    @GetMapping("/movietvseries")
    public List<MovieTVSerie> getAll(){return movieTVSerieService.getAll();}

    @GetMapping("/movietvseries/{movietvseriesId}")
    public MovieTVSerie get(@PathVariable int movietvseriesId){
        return movieTVSerieService.get(movietvseriesId);
    }

    @PostMapping("/movietvseries")
    public MovieTVSerie addMovieTvSerie(@RequestBody MovieTVSerie movieTVSerie){

        movieTVSerieService.save(movieTVSerie);
        return movieTVSerie;
    }
}