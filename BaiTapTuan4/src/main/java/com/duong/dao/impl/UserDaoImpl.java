package com.duong.dao.impl;

import com.duong.config.JPAConfig;
import com.duong.dao.UserDao;
import com.duong.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByEmail(String email) {
        EntityManager entityManager =JPAConfig.getEntityManager();

        try {
            TypedQuery<User> q = entityManager.createQuery("select u from User u where u.email = :em", User.class);
            q.setParameter("em", email);
            List<User> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        EntityManager entityManager =JPAConfig.getEntityManager();

        try {
            TypedQuery<User> q = entityManager.createQuery("select u from User u where u.username = :un", User.class);
            q.setParameter("un", username);
            List<User> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public User login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        EntityManager entityManager =JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user); // = insert
            transaction.commit();
            return true;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            return false;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateUser(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(user);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
