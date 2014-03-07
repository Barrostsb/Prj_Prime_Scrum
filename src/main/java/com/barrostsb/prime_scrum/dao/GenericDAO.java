package com.barrostsb.prime_scrum.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T > {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    void merge(T entity);

    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria criteria);
    List<T> getEntities();
    List<T> getListByDetachedCriteria(DetachedCriteria criteria);
}