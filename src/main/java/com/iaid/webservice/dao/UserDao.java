package com.iaid.webservice.dao;

import com.iaid.webservice.dto.Users;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Crawlers on 8/24/2016.
 */
public class UserDao extends AbstractDao<Users>{
  public UserDao(DBI dbi) {
    super(dbi);
  }
}
