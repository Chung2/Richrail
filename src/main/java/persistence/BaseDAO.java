package persistence;

import java.util.List;

/**
 * Created by nickw on 30-11-2017.
 */
public interface BaseDAO<T> {

    T findById(int id);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
    void insert(T entity);
}
