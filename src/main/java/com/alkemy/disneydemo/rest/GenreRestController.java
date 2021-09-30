package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.Genre;
import com.alkemy.disneydemo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreRestController implements Serializable {

    private GenreService genreService;

    @Autowired
    public GenreRestController(GenreService theGenreService){
        genreService = theGenreService;
    }

    @GetMapping("/genres")
    public List<Genre> getAll(){
        return genreService.getAll();
    }

    @GetMapping("/genres/{genreId}")
    public Genre get(@PathVariable int genreId){
        return genreService.get(genreId);
    }

    @PostMapping("/genres")
    public Genre addGenre(@RequestBody Genre theGenre){
        theGenre.setId(0);//so hibernate assigns the correct id in case the object already has one as a parameter
        genreService.save(theGenre);
        return theGenre;
    }

    @PutMapping("/genres")
    public Genre updateGenre(@RequestBody Genre theGenre){
        genreService.save(theGenre);
        return theGenre;
    }

    @DeleteMapping("/genres/{genreId}")
    public String deleteGenre(@PathVariable int genreId){
        Genre tempGenre = genreService.get(genreId);
        //todo exception si es null
        genreService.delete(genreId);

        return "The Genre deleted is: " + tempGenre + "\nId: " + tempGenre.getId();
    }

}
