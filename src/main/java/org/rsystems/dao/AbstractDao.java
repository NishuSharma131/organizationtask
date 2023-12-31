package org.rsystems.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByPrimaryKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public PK persist(T entity) {
        return (PK) getSession().save(entity);
    }
    public void update(T entity) {
        getSession().update(entity);
    }

    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }


}
