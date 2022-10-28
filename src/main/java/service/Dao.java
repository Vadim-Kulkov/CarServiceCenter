package service;

import java.util.List;

public interface Dao<T, V extends Comparable<V>> {

    T getById(V id);

    List<T> getAllAsList();

    void save(T t);

    T update(T t);

    void delete(T t);
}
