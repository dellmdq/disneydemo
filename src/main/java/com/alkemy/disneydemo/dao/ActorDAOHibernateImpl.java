package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.model.MovieTVSerie;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ActorDAOHibernateImpl implements ActorDAO {

    private EntityManager entityManager;

    @Autowired//spring will find it anyways but its a good practice to show the constructor injection
    public ActorDAOHibernateImpl(EntityManager theEntityManager) {entityManager = theEntityManager;}

    @Override
    public List<Actor> getAll() {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //create a query
        Query<Actor> theQuery = currentSession.createQuery("from Actor", Actor.class);

        //execute query and get results
        //return results
        return theQuery.getResultList();
    }

    @Override
    public Actor get(int theId) {
        //get hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        //get the actor
        return currentSession.get(Actor.class, theId);
    }


    @Override
    public void save(Actor theActor) {
        //get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //save or update actor
        currentSession.saveOrUpdate(theActor);
    }



    @Override
    public void delete(int theId) {
        //get the hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete object with primary key
        Query<Actor> theQuery = currentSession.createQuery("delete from Actor where id=:actorId",Actor.class);
        theQuery.setParameter("actorId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public List<Actor> getActorsByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Actor> theQuery = currentSession.createQuery("from Actor where name=:name",Actor.class);

        theQuery.setParameter("name",name);
        return theQuery.getResultList();
    }

    @Override
    public List<Actor> getActorsByAge(int age) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Actor> theQuery = currentSession.createQuery("from Actor where age=:age",Actor.class);

        theQuery.setParameter("age",age);
        return  theQuery.getResultList();
    }

    /*
    @Override
    public List<Actor> getActorsByMovieTVSerieId(int theMovieTVSerieId) {

        Session currentSession = entityManager.unwrap(Session.class);

        //Query<Actor> theQuery = currentSession.createQuery("from movietvserie_actor where movietvserie_id=:movietvserieId",Actor.class);
        Query<Actor> theQuery = currentSession.createQuery("from movietvserie_actor where movietvserie_id=:movietvserieId",Actor.class);
        theQuery.setParameter("movietvserieId",theMovieTVSerieId);
        return  theQuery.getResultList();
    }
    */

    @Override
    public List<Actor> getActorsByMovieTVSerieId(int theMovieTVSerieId) {

        Session currentSession = entityManager.unwrap(Session.class);

        //Query<MovieTVSerie> theQuery = currentSession.createQuery("from movietvserie_actor where movietvserie_id=:theMovieTVSerieId",MovieTVSerie.class);

        Query<Actor> theQuery = currentSession.createQuery("select a from Actor a join a.movieTVSeries m where m.id=:movietvserieId",Actor.class);
        theQuery.setParameter("movietvserieId",theMovieTVSerieId);
        //return (List<Actor>) theQuery.getSingleResult().getActors();
        return theQuery.getResultList();
    }
}












