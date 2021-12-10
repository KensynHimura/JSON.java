package web.dao;

import org.springframework.stereotype.Repository;

import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.find(User.class,user.getId()));
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findByUserName(String name){
        return entityManager.createQuery("select u from User u where u.name=:name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}

