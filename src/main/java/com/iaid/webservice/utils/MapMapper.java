package com.iaid.webservice.utils;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MapMapper implements ResultSetMapper<Map<String, Object>> {
  @Override
  public Map<String, Object> map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    HashMap<String, Object> result = new HashMap<>();
    for(int i =1; i <= r.getMetaData().getColumnCount(); i++) {
      String columnName = r.getMetaData().getColumnName(i);
      Object value = r.getObject(i);
      result.put(columnName, value);
    }
    return result;
  }
}
