package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.MovieTVSerie;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MovieTVSerieDAOHibernateImpl implements MovieTVSerieDAO {

    private EntityManager entityManager;

    @Autowired
    public MovieTVSerieDAOHibernateImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<MovieTVSerie> getAll() {
        //get the current session
        Session currentSession = entityManager.unwrap(Session.class);
        //create a query
        Query<MovieTVSerie> theQuery = currentSession.createQuery("from MovieTVSerie", MovieTVSerie.class);
        //execute and return results
        return theQuery.getResultList();
    }

    @Override
    public MovieTVSerie get(int theId) {
        //get the current session
        Session currentSession = entityManager.unwrap(Session.class);
        //get the MovieTVSerie
        return currentSession.get(MovieTVSerie.class, theId);
    }

    @Override
    public void save(MovieTVSerie theMovieTVSerie) {
        //get the current session
        Session currentSession = entityManager.unwrap(Session.class);
        //save or update
        currentSession.saveOrUpdate(theMovieTVSerie);
    }

    @Override
    public void delete(int theId) {
        //get the current session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete object with primary key
        Query theQuery = currentSession.createQuery("delete from MovieTVSerie where id=:MovieTVSerieId");
        theQuery.setParameter("MovieTVSerieId",theId);
        theQuery.executeUpdate();
    }
}
