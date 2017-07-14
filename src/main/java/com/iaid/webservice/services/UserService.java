package com.iaid.webservice.services;

import com.iaid.webservice.dao.AbstractDao;
import com.iaid.webservice.dao.IDAO;
import com.iaid.webservice.dao.UserDao;
import com.iaid.webservice.dto.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Crawlers on 8/20/2016.
 */
public class UserService extends AbstractService<Users> {
  //search field name should be database name.
  List<String> searchFields = new ArrayList<>(Arrays.asList("username","first_name","last_name","bgroup","email"));
  public UserService(UserDao dao) {
    super(dao);
  }

  @Override
  public List<Users> getAll(String searchText, List<String> searchField, String offset, String limit, Class clazz){
    return getAll(searchText, searchFields, offset, limit, clazz, true);
  }

  public Users login(Users users){
    String condition = "email = '"+users.getEmail()+"' AND password = '"+users.getPassword()+"'";
    List<Users> list = customGetAll(condition,Users.class);
    if (list!=null && !list.isEmpty()){
      users = list.get(0);
      users.setPassword(null);
      return users;
    }
    return null;
  }
}
