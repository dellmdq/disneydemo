package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.MovieTVSerie;
import com.alkemy.disneydemo.service.MovieTVSerieService;
import com.alkemy.disneydemo.utils.JsonPatchUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieTVSerieRestController implements Serializable {

    private MovieTVSerieService movieTVSerieService;
    private JsonPatchUtils jsonPatchUtils;

    @Autowired
    public MovieTVSerieRestController(MovieTVSerieService theMovieTVSerieService, JsonPatchUtils theJsonPatchUtils){
        movieTVSerieService = theMovieTVSerieService;
        jsonPatchUtils =  theJsonPatchUtils;
    }

    //expose "/shows" and return list of shows
    /*@GetMapping("/movietvseries")
    public List<MovieTVSerie> getAll(){return movieTVSerieService.getAll();}*/

    @GetMapping()
    public List<MovieTVSerie> getAll(@QueryParam("name") String name,
                                     @QueryParam("genre") String genre,
                                     @QueryParam("order") String order){


        if(name != null && name.length() >0){
            return movieTVSerieService.getMovieTVSerieByName(name);
        }
        if(genre != null && genre.length() >0){
            return movieTVSerieService.getMovieTVSerieByGenre(genre);
        }
        if((order != null && order.length() >0)){
            return movieTVSerieService.getMovieTVSerieSorted(order);
        }

        //getall sin filtro
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

    @PatchMapping("/{movietvseriesId}")
    public MovieTVSerie update(@PathVariable int movietvseriesId, @RequestBody JsonPatch patch){
        try{
            MovieTVSerie movieTVSerie = this.get(movietvseriesId);
            MovieTVSerie movieTVSeriePatched = (MovieTVSerie) jsonPatchUtils.applyPatch(patch, movieTVSerie);
            movieTVSerieService.update(movieTVSeriePatched);
            return movieTVSeriePatched;
        }
        catch(JsonPatchException | JsonProcessingException e){
            System.out.println("Error: MovieTVSerie not updated");
            return movieTVSerieService.get(movietvseriesId);
        }
    }
}
