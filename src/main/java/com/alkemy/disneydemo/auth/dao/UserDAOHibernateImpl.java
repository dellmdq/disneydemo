package com.alkemy.disneydemo.auth.dao;

import com.alkemy.disneydemo.auth.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAOHibernateImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAll() {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //create a query
        Query<User> theQuery = currentSession.createQuery("from User", User.class);

        //execute query and get results
        //return results
        return theQuery.getResultList();
    }

    @Override
    public User get(int theId) {
        //get hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        //get the User
        return currentSession.get(User.class, theId);
    }


    @Override
    public void save(User theUser) {
        //get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //save or update User
        currentSession.saveOrUpdate(theUser);
    }



    @Override
    public void delete(int theId) {
        //get the hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete object with primary key
        Query theQuery = currentSession.createQuery("delete from User where id=:userId");
        theQuery.setParameter("UserId", theId);
        theQuery.executeUpdate();
    }
}
