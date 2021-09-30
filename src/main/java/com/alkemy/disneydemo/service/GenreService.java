package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.model.Genre;

import java.util.List;

public interface GenreService {
    public List<Genre> getAll();

    public Genre get(int theId);

    public void save(Genre theGenre);//hibernate saveOrUpdate

    public void delete(int theId);
}
