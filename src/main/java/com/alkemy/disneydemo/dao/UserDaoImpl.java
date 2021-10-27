package com.alkemy.disneydemo.dao;

import javax.persistence.EntityManager;

import com.alkemy.disneydemo.entity.User;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);


		// create the user ... finally LOL
		currentSession.save(theUser);
	}

	@Override
	public void update(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.merge(theUser);
	}

	@Override
	public User findByVerificationCode(String verification_code) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("select u from User u where u.verificationCode = :verification_code", User.class);
		theQuery.setParameter("verification_code",verification_code);
		System.out.println("User verified: " + theQuery.getResultList());
		return theQuery.getSingleResult();
	}

}
