package com.iaid.webservice.dao;

import com.iaid.webservice.dto.Users;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

/**
 * Created by Crawlers on 4/5/2016.
 */
@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
public interface UsersDao {
    @SqlUpdate("INSERT INTO users (id, first_name, last_name, username, email, password, bgroup, phone, remember_token, created_at, updated_at, enabled, auth_type) "
            + "VALUES (:id, :firstName, :lastName, :username, :email, :password, :bgroup, :phone, :rememberToken, :createdAt, :updatedAt, :enabled, :authType)")
    @GetGeneratedKeys
    public Integer addUser(@BindBean Users users);

    @SqlUpdate("UPDATE users SET first_name=:firstName, last_name=:lastName, username=:username, email=:email, password=:password, bgroup=:bgroup, phone=:phone, " +
            "remember_token=:rememberToken, created_at=:createdAt, updated_at=:updatedAt, enabled=:enabled, auth_type=:authType WHERE id = :id")
    public void updateUser(@BindBean Users users);

    @SqlUpdate("DELETE FROM users WHERE id = :id")
    public void deleteUser(@Bind("id") Integer id);

    /*@SqlQuery("SELECT *, first_name AS firstName,last_name AS lastName," +
            "username AS username, email AS email, password AS password," +
            "bgroup AS bgroup, phone AS phone, remember_token AS rememberMe, " +
            "created_at AS createdAt, updated_at AS updatedAt, enabled AS enabled, " +
            "auth_type AS authType" +
            " FROM users WHERE id = :id")
    public List<Users> getUser(@Bind("id") Integer id);*/

    @SqlQuery
    public List<Users> getUser(@Define("query") String query);
}
