package com.iaid.webservice.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.iaid.webservice.dao.CategoryDao;
import com.iaid.webservice.dao.UsersDao;
import com.iaid.webservice.dto.Category;
import com.iaid.webservice.dto.Responses;
import com.iaid.webservice.dto.Users;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Crawlers on 4/26/2016.
 */
@Path("/firstAid/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
  final static Logger logger = Logger.getLogger(UserResource.class);
  private CategoryDao categoryDao;

  public CategoryResource(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addCategory(Category category) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      //System.out.println("User Json:"+objectMapper.writeValueAsString());
      Integer id = categoryDao.addCategory(category);
      category.setId(id);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, category, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      e.printStackTrace();
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateCategory(@PathParam("id") Integer id, Category category) {
    try {
      category.setId(id);
      categoryDao.updateCategory(category);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, category, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      e.printStackTrace();
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response deleteCategory(@PathParam("id") Integer id) {
    try {
      categoryDao.deleteCategory(id);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, null, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @GET
  public Response getCategory(@QueryParam("id") Optional<Integer> id) {
    try {
      String query = " WHERE id IS NOT NULL";
      if (id.isPresent()) {
        query = query + " AND id = " + id.get();
      }
      System.out.println("Query is:" + query);
      List<Category> categoryList = categoryDao.getCategory(query);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, categoryList, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }
}
