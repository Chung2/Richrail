package persistence.jpa;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import persistence.BaseDAO;

/**
 * Created by nickw on 7-12-2017.
 */
public abstract class AbstractDaoJpaImpl<T> implements BaseDAO<T> {
    protected EntityManager em;
    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDaoJpaImpl(EntityManager entityManager) {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        this.em = entityManager;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void insert(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        em.persist(entity);
    }

    @Override
    public T findById(int id) {
        T entity = (T) em.find(type, id);
        return entity;
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        List<T> entities = (List<T>) em.createQuery("from "+type.getName()).getResultList();
        return entities;
    }
}