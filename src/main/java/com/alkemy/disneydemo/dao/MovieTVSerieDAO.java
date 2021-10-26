package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.MovieTVSerie;

import java.util.List;

public interface MovieTVSerieDAO {

    public List<MovieTVSerie> getAll();

    public MovieTVSerie get(int theId);

    public void save(MovieTVSerie theMovieTVSerie);

    public void delete(int theId);

    public void update(MovieTVSerie movieTVSerie);

    public List<MovieTVSerie> getMovieTVSerieByName(String name);

    public List<MovieTVSerie> getMovieTVSerieByGenre(String genre);

    public List<MovieTVSerie> getMovieTVSerieSorted(String sort);
}
