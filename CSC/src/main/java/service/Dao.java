package service;

import java.util.List;
import java.util.Optional;

public interface Dao<T, V extends Comparable<V>> {

    T get(V id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
