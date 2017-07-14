package com.iaid.webservice.dao;

import com.iaid.webservice.utils.BeanMapperSnakeCase;
import com.iaid.webservice.utils.BeanMapperSnakeCaseFactory;
import org.skife.jdbi.v2.BeanMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import org.skife.jdbi.v2.util.IntegerMapper;
import org.skife.jdbi.v2.util.LongMapper;

import java.util.List;

/**
 * Created by Crawlers on 8/25/2016.
 */
@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
public abstract class AbstractDao<T>{
  private DBI dbi;

  public AbstractDao(){

  }

  public AbstractDao(DBI dbi){
    this.dbi = dbi;
  }

  public Long insert(String tableName, String columnNames, String values){
    return insert(insertStatement(tableName, columnNames, values));
  }

  public Long update(String tableName, String statement, Long id){
    return update(updateStatement(tableName, statement, id));
  }

  public void delete(String tableName, Long id){
    delete(deleteStatement(tableName, id));
  }

  public T get(String tableName, String relation, String condition, Class clazz){
    return get(getQuery(tableName, relation, condition, null), clazz);
  }

  public List<T> getAll(String tableName, String relation, String condition, String pagination, Class clazz){
    System.out.println("Relation:"+relation);
    System.out.println("Table name:"+tableName);
    return getAll(getQuery(tableName, relation, condition, pagination), clazz);
  }

  protected Long insert(String statement) {
    Handle handle = dbi.open();
    Update insert = handle.createStatement(statement);
    Long id = insert.executeAndReturnGeneratedKeys(LongMapper.FIRST).first();
    handle.close();
    return id;
  }

  protected Long update(String statement) {
    Handle handle = dbi.open();
    Update update = handle.createStatement(statement);
    Integer id = update.execute();
    System.out.println("Update Id:"+id);
    handle.close();
    return (long)id;
  }

  protected Integer delete(String statement) {
    Handle handle = dbi.open();
    Update update = handle.createStatement(statement);
    Integer id = update.execute();
    System.out.println("Delete Id:"+id);
    handle.close();
    return id;
  }

  protected T get(String query, Class clazz) {
    Handle handle = dbi.open();
    T t = handle.createQuery(query).map(new BeanMapper<T>(clazz)).first();
    return t;
  }

  protected List<T> getAll(String query, Class clazz) {
    Handle handle = dbi.open();
    List<T> t = handle.createQuery(query).map(new BeanMapper<T>(clazz)).list();
    System.out.println("Get By Id:"+t.toString());
    return t;
  }

  private String insertStatement(String tableName, String columnNames, String values){
    return "INSERT INTO "+tableName+" ( "+ columnNames+" ) VALUES ( "+values+" )";
  }

  private String updateStatement(String tableName, String statement, Long id){
    return "UPDATE "+tableName+" SET "+ statement+" WHERE id =  "+id;
  }

  private String deleteStatement(String tableName, Long id){
    return "DELETE FROM "+tableName+" WHERE id =  "+id;
  }

  private String getQuery(String tableName, String relation, String condition, String pagination){
    String query = "SELECT "+relation+" FROM "+ tableName;
    if (condition!=null){
      query+=" WHERE "+condition;
    }
    if (pagination!=null){
      query+=pagination;
    }
    System.out.println("Query:"+query);
    return query;
  }
}
