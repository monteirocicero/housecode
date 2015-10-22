package br.com.housecode.store.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.housecode.store.models.User;

@Repository
public class UserDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "select u from User u where u.login = :login";
		List<User> users = em.createQuery(jpql, User.class).setParameter("login", username).getResultList();
		
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("The user " + username + " not found");
		}
		return users.get(0);
	}

}
