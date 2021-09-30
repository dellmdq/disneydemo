package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Actor;
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
    public void save(Actor theActor) {
        //get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //save or update actor
        currentSession.saveOrUpdate(theActor);
    }

    @Override
    public Actor get(int theId) {
        //get hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        //get the actor
        return currentSession.get(Actor.class, theId);
    }

    @Override
    public void delete(int theId) {
        //get the hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Actor where id:=actorId");
        theQuery.setParameter("actorId", theId);
        theQuery.executeUpdate();
    }
}












