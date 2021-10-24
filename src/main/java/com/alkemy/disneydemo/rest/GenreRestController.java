package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.Genre;
import com.alkemy.disneydemo.model.MovieTVSerie;
import com.alkemy.disneydemo.service.GenreService;
import com.alkemy.disneydemo.utils.JsonPatchUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/genres")
public class GenreRestController implements Serializable {

    private GenreService genreService;
    private JsonPatchUtils jsonPatchUtils;

    @Autowired
    public GenreRestController(GenreService genreService, JsonPatchUtils jsonPatchUtils) {
        this.genreService = genreService;
        this.jsonPatchUtils = jsonPatchUtils;
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{genreId}")
    public Genre get(@PathVariable int genreId) {
        return genreService.get(genreId);
    }

    @PostMapping
    public Genre add(@RequestBody Genre theGenre) {
        theGenre.setId(0);//so hibernate assigns the correct id in case the object already has one as a parameter
        genreService.save(theGenre);
        return theGenre;
    }

    @PutMapping
    public Genre update(@RequestBody Genre theGenre) {
        genreService.save(theGenre);
        return theGenre;
    }

    @DeleteMapping("/{genreId}")
    public String delete(@PathVariable int genreId) {
        Genre tempGenre = genreService.get(genreId);

        //getting genre
        //geting movies associated to genre
        Set<MovieTVSerie> MovieTVSerieSet = tempGenre.getMovieTVSeries();
        //deleting movies genre
        for(MovieTVSerie mts : MovieTVSerieSet){
            mts.setGenre(null);
        }

        //setmovieTvSerie set to null
        tempGenre.removeMovieTvSeriesSet();
        //save to bd
        genreService.save(tempGenre);
        //null
        genreService.delete(genreId);
        return "The Genre deleted is: " + tempGenre + "\nId: " + tempGenre.getId();
    }

    @PatchMapping(path = "/{genreId}", consumes = "application/json-patch+json")
    public Genre update(@PathVariable int genreId, @RequestBody JsonPatch patch){
        try {
            Genre genre = this.get(genreId);
            Genre genrePatched = (Genre) jsonPatchUtils.applyPatch(patch, genre);
            genreService.update(genrePatched);
            return genrePatched;
        }
        catch( JsonPatchException| JsonProcessingException e){
            System.out.println("Error: Genre not updated.");
            return genreService.get(genreId);
        }
    }
}