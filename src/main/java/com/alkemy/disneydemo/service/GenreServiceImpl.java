package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.dao.GenreDAO;
import com.alkemy.disneydemo.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    private GenreDAO genreDAO;

    @Autowired
    public GenreServiceImpl(GenreDAO theGenreDAO){
        genreDAO = theGenreDAO;
    }


    @Override
    @Transactional
    public List<Genre> getAll() {
        return genreDAO.getAll();
    }

    @Override
    @Transactional
    public Genre get(int theId) {
        return genreDAO.get(theId);
    }

    @Override
    @Transactional
    public void save(Genre theGenre) {
        genreDAO.save(theGenre);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        genreDAO.delete(theId);
    }
}
