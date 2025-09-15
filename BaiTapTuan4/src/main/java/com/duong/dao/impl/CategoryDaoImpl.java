package com.duong.dao.impl;

import com.duong.config.JPAConfig;
import com.duong.dao.CategoryDao;
import com.duong.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean addCategory(Category category) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(category); // = insert
            transaction.commit();
            return true;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = entityManager.find(Category.class, id);
            if (category != null) entityManager.remove(category); // DELETE
            transaction.commit();
            return true;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(category);
            transaction.commit();
            return true;
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            return false;
        }
    }

    @Override
    public Category getCategoryById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> q = entityManager.createQuery("select c from Category c where c.id = :i", Category.class);
            q.setParameter("i", id);

            return q.getSingleResult();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> q = entityManager.createQuery("select c from Category c order by c.id desc", Category.class);
            return q.getResultList();
        }
        catch (Exception e) {
            return null;
        }
    }
}
