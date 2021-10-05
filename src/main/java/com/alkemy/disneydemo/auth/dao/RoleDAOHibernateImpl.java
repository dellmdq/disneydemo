package com.alkemy.disneydemo.auth.dao;

import com.alkemy.disneydemo.auth.model.Role;
import com.alkemy.disneydemo.model.Actor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class RoleDAOHibernateImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOHibernateImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Role> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Role> theQuery = currentSession.createQuery("from Role", Role.class);
        return theQuery.getResultList();
    }

    @Override
    public Role get(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Role.class, theId);

    }

    @Override
    public void save(Role theRole) {
        //get hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //save or update actor
        currentSession.saveOrUpdate(theRole);
    }

    @Override
    public void delete(int theId) {
        //get the hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Role where id=:roleId");
        theQuery.setParameter("roleId", theId);
        theQuery.executeUpdate();
    }
}
