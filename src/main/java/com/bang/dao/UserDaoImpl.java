package com.bang.dao;

import com.bang.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by johnnyGrimes on 22/02/2018.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getId();
    }

    @Override
    public User get(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query<User> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void update(long id, User user) {
        Session session = sessionFactory.getCurrentSession();
        User user2 = session.byId(User.class).load(id);
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        session.flush();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.byId(User.class).load(id);
        session.delete(user);
    }
}
