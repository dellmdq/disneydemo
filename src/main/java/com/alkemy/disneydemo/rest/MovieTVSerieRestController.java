package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.MovieTVSerie;
import com.alkemy.disneydemo.service.MovieTVSerieService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieTVSerieRestController implements Serializable {

    private MovieTVSerieService movieTVSerieService;

    @Autowired
    public MovieTVSerieRestController(MovieTVSerieService theMovieTVSerieService){
        movieTVSerieService = theMovieTVSerieService;
    }

    //expose "/shows" and return list of shows
    /*@GetMapping("/movietvseries")
    public List<MovieTVSerie> getAll(){return movieTVSerieService.getAll();}*/

    @GetMapping()
    public List<MovieTVSerie> getAll(){
        JsonResult json = JsonResult.instance();
        List<MovieTVSerie> movieTVSeries = movieTVSerieService.getAll();
        String[] excludedProps = {"id","rating","genre","actors"};

        return json.use(JsonView.with(movieTVSeries)
                        .onClass(MovieTVSerie.class, Match.match()
                                .exclude(excludedProps)))
                .returnValue();
    }

    @GetMapping("/{movietvseriesId}")
    public MovieTVSerie get(@PathVariable int movietvseriesId){
        return movieTVSerieService.get(movietvseriesId);
    }

    @PostMapping()
    public MovieTVSerie add(@RequestBody MovieTVSerie movieTVSerie){
        movieTVSerie.setId(0);
        movieTVSerieService.save(movieTVSerie);
        return movieTVSerie;
    }

    @PutMapping()
    public MovieTVSerie update(@RequestBody MovieTVSerie movieTVSerie){
        movieTVSerieService.save(movieTVSerie);
        return movieTVSerie;
    }

    @DeleteMapping("/{movietvseriesId}")
    public MovieTVSerie delete(@PathVariable int movietvseriesId){
        MovieTVSerie tempMovieTvSerie = movieTVSerieService.get(movietvseriesId);

        movieTVSerieService.delete(movietvseriesId);

        return tempMovieTvSerie;
    }
}
