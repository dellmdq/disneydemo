package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Genre;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class GenreDAOHibernateImpl implements GenreDAO {

    private EntityManager entityManager;

    @Autowired
    public GenreDAOHibernateImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    };

    @Override
    public List<Genre> getAll() {
        //get the current session
        Session currentSession = entityManager.unwrap(Session.class);
        //create query
        Query theQuery = currentSession.createQuery("from Genre",Genre.class);
        //execute and return
        return theQuery.getResultList();
    }

    @Override
    public Genre get(int theId) {
        //get the hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Genre.class, theId);
    }

    @Override
    public void save(Genre theGenre) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theGenre);
    }

    @Override
    public void delete(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Genre where id=:genreId");
        theQuery.setParameter("genreId",theId);
        theQuery.executeUpdate();
    }
}
