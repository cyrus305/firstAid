package com.iaid.webservice.services;

import java.util.List;

/**
 * Created by Crawlers on 8/15/2016.
 */
public interface IService<T> {
  public Long insert(T t);

  public void addList(List<T> l);

  public Integer update(T t, Long id, boolean validate);

  public List<T> getAll(String search, List<String> searchFields, String offset, String limit, Class clazz);

  public T getbyid(Long id, Class clazz);

  public void delete(Long id, Class clazz);
}
