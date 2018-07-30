package py.com.testcoding.repositories;

import py.com.testcoding.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alfredo Cano
 * @since 1.0.0
 */
@Stateless
public class UserRepository {
    // DataSource implementation
    // @PersistenceContext(name = "UserPU")
    // EntityManager em;

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User("Test", "test@mail.com", new Date()));
        return users;
        // return this.em.createNamedQuery(User.FIND_ALL).getResultList();
    }

    public User findById(Long id) {
        return new User("Test", "test@mail.com", new Date());
        // return this.em.find(User.class, id);
    }

    public void create(User user) {
        // this.em.persist(user);
    }

    public void remove(User user) {
        // this.em.remove(user);
    }
}