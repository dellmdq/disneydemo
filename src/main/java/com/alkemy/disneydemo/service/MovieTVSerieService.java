package com.alkemy.disneydemo.service;

import java.util.List;
import com.alkemy.disneydemo.model.MovieTVSerie;

public interface MovieTVSerieService {
    public List<MovieTVSerie> getAll();

    public MovieTVSerie get(int theId);

    public void save(MovieTVSerie theMovieTVSerie);

    public void delete(int theId);

    public void update(MovieTVSerie movieTVSerie);

    public List<MovieTVSerie> getMovieTVSerieByName(String name);

    public List<MovieTVSerie> getMovieTVSerieByGenre(String genre);

    public List<MovieTVSerie> getMovieTVSerieSorted(String sort);
}
