package com.iaid.webservice.resources;

import com.google.common.base.Optional;
import com.iaid.webservice.dto.Users;
import com.iaid.webservice.services.UserService;
import com.iaid.webservice.utils.JwtUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 4/9/2016.
 */
@Path("/firstAid/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends BaseResource<Users> {
  final static Logger logger = Logger.getLogger(UserResource.class);
  private UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(Users users) {
    //UserValidator.validateUsers(userService, users);
    Long id = userService.insert(users);
    users.setId(id);
    return response(HttpStatus.CREATED_201, users, "User Created");
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(@PathParam("id") Long id, Users users) {
    userService.update(users, id, false);
    return response(HttpStatus.OK_200, users, "User Updated");
  }

  @DELETE
  @Path("/{id}")
  public Response deleteUser(@PathParam("id") Long id) {
    userService.delete(id, Users.class);
    return response(HttpStatus.OK_200, null, "User Deleted");
  }

  @GET
  @Path("/{id}")
  public Response getUser(@PathParam("id") Long id) {
    Users user = userService.getbyid(id, Users.class);
    return response(HttpStatus.OK_200, user, "User Fetched");
  }

  @GET
  public Response getAllUser(@QueryParam("search") String searchText, @QueryParam("offset") String offset, @QueryParam("limit") String limit) {
    List<Users> user = userService.getAll(searchText,null, offset, limit, Users.class);
    return responseList(HttpStatus.OK_200, user, "User Fetched");
  }

  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response login(Users usr) {
    Users user = userService.login(usr);
    if (user!=null){
      Map<String, Object> loginResponse = new HashMap<>();
      //Change JwtUtil static variables later.
      KeyStore keyStore = JwtUtils.loadKeystore(JwtUtils.wrkDir, JwtUtils.keyStrPwd);
      try {
        Key key = JwtUtils.retriveKey(keyStore, JwtUtils.keyName, JwtUtils.keyPwd);
        String jwt = JwtUtils.getJwt(key);
        loginResponse.put("jwt", jwt);
        loginResponse.put("user", user);
        return responseCustom(HttpStatus.OK_200, loginResponse, "Login Success");
      }catch (Exception e){
        e.printStackTrace();
        return response(500, null, "Token cant be generated");
      }
    }
    return response(500, null, "Username or password is not valid");
  }
}
