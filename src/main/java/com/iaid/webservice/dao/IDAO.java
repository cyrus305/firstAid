package com.iaid.webservice.dao;

import com.iaid.webservice.utils.BeanMapperSnakeCase;
import com.iaid.webservice.utils.BeanMapperSnakeCaseFactory;
import com.iaid.webservice.utils.MapMapper;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.List;

/**
 * Created by Crawlers on 8/15/2016.
 */
/*@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
@RegisterMapperFactory(BeanMapperSnakeCaseFactory.class)
@Mapper(BeanMapperSnakeCase.class)*/
public interface IDAO<T> {
  Long insert(String tableName, String columnNames, String values);

  Integer update(String tableName, String statement, Long id);

  void delete(String tableName, Long id);

  T get(String tableName, String relation, String condition);

  /*@SqlUpdate
  @GetGeneratedKeys
  Long insert(@Define("table_name") String tableName, @Define("column_names") String columnNames, @Define("values") String values);

  @SqlUpdate
  Integer update(@Define("table_name") String tableName, @Define("statement") String statement, @Define("id") Long id);

  @SqlUpdate
  void delete(@Define("table_name") String tableName, @Define("id") Long id);

  @SqlQuery
  T get(@Define("table_name") String tableName, @Define("relation") String relation, @Define("condition") String condition);

  @SqlQuery
  List<T> getAll(String condition, String searchText, String limit, String offset, List<String> searchFields);*/
}