package com.iaid.webservice.services;

import com.google.common.base.CaseFormat;
import com.iaid.webservice.dao.AbstractDao;
import com.iaid.webservice.dao.IDAO;
import com.iaid.webservice.dto.Responses;
import com.iaid.webservice.utils.JsonParserUtils;
import com.iaid.webservice.utils.ReflectionUtil;
import com.iaid.webservice.validator.FieldValidator;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 8/15/2016.
 */
public class AbstractService<T> extends FieldValidator<T> implements IService<T> {
  private StringBuilder statement;
  private StringBuilder columns;
  private StringBuilder values;
  private Map<String, String> columnToValueMap;
  private Map<String, Object> valueMap;
  AbstractDao<T> dao;

  AbstractService(AbstractDao<T> dao) {
    this.dao = dao;
    columns = new StringBuilder();
    values = new StringBuilder();
    statement = new StringBuilder();
  }

  @Override
  public Long insert(T t) {
    if (validate(t)) {
      columnToValueMap = classToDbFieldMap(t.getClass());
      valueMap = mapProperties(t);
      if (valueMap != null) {
        columnOrValues(columnToValueMap, valueMap);
        Long id = dao.insert(getTableName(t), columns.toString(), values.toString());
        Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, id.toString(), HttpStatus.getMessage(HttpStatus.OK_200))).build();
        return id;
      }
    }
    return null;
  }

  public Long add(T t, String tableName, String columnNames, String values, IDAO<T> dao) {
    return (validate(t)) ? dao.insert(tableName, columnNames, values) : null;
  }

  @Override
  public Integer update(T t, Long id, boolean validate) {
    boolean isValid = false;
    if (validate)
      isValid = validate(t);
    else
      isValid = true;

    if (isValid) {
      Long row = dao.update(getTableName(t), updateStatement(t), id);
      return (int)(long)row;
    } else {
      return null;
    }
  }

  @Override
  public void addList(List<T> l) {

  }

  @Override
  public List<T> getAll(String searchText, List<String> searchFields, String offset, String limit, Class clazz) {
    return getAll(searchText, searchFields, offset, limit, clazz, false);
  }

  public List<T> getAll(String searchText, List<String> searchFields, String offset, String limit, Class clazz, boolean isOverridden) {
    String condition = null;
    if (isOverridden){
      condition = getCondition(searchText, searchFields);
    }
    List<T> t = dao.getAll(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), condition, getPagination(offset, limit), clazz);
    System.out.println("Got T:" + t.toString());
    return t;
  }

  public List<T> customGetAll(String condition, Class clazz) {
    List<T> t = dao.getAll(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), condition, null, clazz);
    return t;
  }

  @Override
  public T getbyid(Long id, Class clazz) {
    System.out.println("inside get by id");
    T t = dao.get(getTableName(clazz), getRelation(classToDbFieldMap(clazz)), " id = " + id, clazz);
    return t;
  }

  @Override
  public void delete(Long id, Class clazz) {
    dao.delete(getTableName(clazz), id);
  }

  public String getTableName(Class t) {
    return snakeCase(t.getSimpleName());
  }

  private String getTableName(T t) {
    return snakeCase(t.getClass().getSimpleName());
  }

  private String snakeCase(String str){
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
  }

  private Map<String, String> classToDbFieldMap(Class clazz) {
    Map<String, String> map = new HashMap<>();
    for (Field field : clazz.getDeclaredFields()) {
      map.put(field.getName(), snakeCase(field.getName()));
    }
    return map;
  }

  public Map<String, Object> mapProperties(T bean) {
    Map<String, Object> properties = new HashMap<>();
    try {
      for (PropertyDescriptor property : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors()) {
        String name = property.getName();
        Object value = property.getReadMethod().invoke(bean);
        if (value instanceof String) {
          value = "'" + value + "'";
        }
        properties.put(name, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return properties;
  }

  private void columnOrValues(Map<String, String> map, Map<String, Object> valueMap) {
    columns.setLength(0);
    values.setLength(0);
    //Change with Lamda expression later
    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      values.append(valueMap.get(pair.getKey())).append(" , ");
      columns.append(pair.getValue()).append(" , ");
      it.remove(); // avoids a ConcurrentModificationException
    }
    values.delete(values.lastIndexOf(" , "), values.lastIndexOf(" , ") + 3);
    columns.delete(columns.lastIndexOf(" , "), columns.lastIndexOf(" , ") + 3);
    //return isColumn ? columns.toString() : values.toString();
  }

  private String updateStatement(T t) {
    columnToValueMap = classToDbFieldMap(t.getClass());
    valueMap = mapProperties(t);
    statement.setLength(0);
    Iterator it = valueMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      if (pair.getValue() != null && columnToValueMap.get(pair.getKey()) != null) {
        statement.append(columnToValueMap.get(pair.getKey())).append(" = ").append(pair.getValue()).append(" , ");
      }
      it.remove();
    }
    System.out.println("Statement:" + statement.toString());
    statement.delete(statement.lastIndexOf(" , "), statement.lastIndexOf(" , ") + 3);
    return statement.toString();
  }

  private String getRelation(Map<String, String> columnToValueMap){
    columns.setLength(0);
    Iterator it = columnToValueMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      columns.append(pair.getValue()).append(" AS ");
      columns.append(pair.getKey()).append(" , ");
      it.remove();
    }
    columns.delete(columns.lastIndexOf(" , "), columns.lastIndexOf(" , ") + 3);
    return columns.toString();
  }

  private String getCondition(String searchText, List<String> searchFields){
    if (searchText != null && searchFields.size() > 0) {
      StringBuilder query = new StringBuilder();
      query.append("(");
      for (String str : searchFields) {
        query.append(" LOWER(").append(str).append(") like LOWER('").append(searchText).append("%')").append(" or ");
      }
      query.delete(query.lastIndexOf(" or "), query.lastIndexOf(" or ") + 3);
      query.append(")");
      return query.toString();
    }
    return null;
  }

  private String getPagination(String offset, String limit){
    StringBuilder pagination = new StringBuilder();
    if (limit != null) {
      pagination.append(" LIMIT ").append(limit);
      if (offset != null) {
        pagination.append(" OFFSET ").append(offset);
      }
    }
    return pagination.toString();
  }

}
