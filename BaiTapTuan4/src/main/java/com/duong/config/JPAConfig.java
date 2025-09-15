package com.duong.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@PersistenceContext
public class JPAConfig {
    public static EntityManager getEntityManager() {
        try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")){
            var props = new java.util.Properties();
            props.load(is);

            var overrides = new java.util.HashMap<String, Object>();
            overrides.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));
            overrides.put("jakarta.persistence.jdbc.user", props.getProperty("db.username"));
            overrides.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));

            return Persistence.createEntityManagerFactory("dataSource", overrides).createEntityManager();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        EntityManager entityManager = JPAConfig.getEntityManager();
    }
}
