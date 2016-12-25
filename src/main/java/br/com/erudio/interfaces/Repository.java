package br.com.erudio.interfaces;

import java.util.List;

public interface Repository<T> {
    public T insert(T entity) throws Exception;    
    public T update(T entity) throws Exception;
    public T delete(String id) throws Exception;
    public T find(String id) throws Exception;
    public List<T> findAll() throws Exception;
}
