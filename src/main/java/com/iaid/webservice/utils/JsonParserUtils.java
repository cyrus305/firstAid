package com.iaid.webservice.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Crawlers on 8/22/2016.
 */
public class JsonParserUtils {
  public static JsonParserUtils jsonParserUtils;
  public static ObjectMapper objectMapper;

  public static synchronized JsonParserUtils getInstance(){
    if (jsonParserUtils == null){
      jsonParserUtils = new JsonParserUtils();
      objectMapper = new ObjectMapper();
      objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
      objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
    return jsonParserUtils;
  }

  public static <T> List<T> getListFromJson(String jsonString, Class<T> clazz) throws IOException {
    List<T> list = null;
    try {
      list = objectMapper.readValue(jsonString,
          TypeFactory.defaultInstance().constructCollectionType(List.class,
              clazz));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static <T> T getObjectFrmJson(String jsonString, Class clazz){
    T obj = null;
    try {
      obj = (T) objectMapper.readValue(jsonString, clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
}
