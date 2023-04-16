package ua.com.project.dao;

import java.util.List;

public interface GeneralDao<T> {
    void saveNativeSQL(T obj);
    void updateNativeSQL(T obj);
    void deleteNativeSQL(T obj);
    void deleteAllNativeSQL();
    List<T> findAllNativeSQL();
    T findByIdNativeSQL(Long id);
    T findByNameNativeSQL(String name);

    void saveHQL(T obj);
    void updateHQL(T obj);
    void deleteHQL(T obj);
    void deleteAllHQL();
    List<T> findAllHQL();
    T findByIdHQL(Long id);
    T findByNameHQL(String name);
}