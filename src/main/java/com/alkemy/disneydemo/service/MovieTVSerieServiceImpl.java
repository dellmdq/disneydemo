package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.dao.MovieTVSerieDAO;
import com.alkemy.disneydemo.model.MovieTVSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieTVSerieServiceImpl implements MovieTVSerieService {

    private MovieTVSerieDAO movieTVSerieDAO;

    @Autowired
    public MovieTVSerieServiceImpl(MovieTVSerieDAO theMovieTVSerieDAO){
        movieTVSerieDAO = theMovieTVSerieDAO;
    }

    @Override
    @Transactional
    public List<MovieTVSerie> getAll() {
        return movieTVSerieDAO.getAll();
    }

    @Override
    @Transactional
    public MovieTVSerie get(int theId) {
        return movieTVSerieDAO.get(theId);
    }

    @Override
    @Transactional
    public void save(MovieTVSerie theMovieTVSerie) {
        movieTVSerieDAO.save(theMovieTVSerie);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        movieTVSerieDAO.delete(theId);
    }

    @Override
    @Transactional
    public void update(MovieTVSerie movieTVSerie) {
        movieTVSerieDAO.update(movieTVSerie);
    }
}
