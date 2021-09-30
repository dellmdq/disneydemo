package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Genre;

import java.util.List;

public interface GenreDAO {

    public List<Genre> getAll();

    public Genre get(int theId);

    public void save(Genre theGenre);

    public void delete(int theId);

}
