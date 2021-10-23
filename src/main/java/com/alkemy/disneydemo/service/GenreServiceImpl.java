package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.dao.GenreDAO;
import com.alkemy.disneydemo.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService{
    private GenreDAO genreDAO;

    @Autowired
    public GenreServiceImpl(GenreDAO theGenreDAO){
        genreDAO = theGenreDAO;
    }


    @Override
    public List<Genre> getAll() {
        return genreDAO.getAll();
    }

    @Override
    public Genre get(int theId) {
        return genreDAO.get(theId);
    }

    @Override
    public void save(Genre theGenre) {
        genreDAO.save(theGenre);
    }

    public void update(Genre theGenre){
        genreDAO.update(theGenre);
    }

    @Override
    public void delete(int theId) {
        genreDAO.delete(theId);
    }
}
