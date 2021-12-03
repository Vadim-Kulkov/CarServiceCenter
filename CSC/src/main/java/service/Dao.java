package service;

import java.util.List;

public interface Dao<T, V extends Comparable<V>> {

    T get(V id);

    List<T> getAllAsList();

    void save(T t);

    void update(T t);

    void delete(T t);
}
