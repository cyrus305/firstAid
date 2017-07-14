package com.iaid.webservice.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.iaid.webservice.dao.DetailInfoDao;
import com.iaid.webservice.dao.UsersDao;
import com.iaid.webservice.dto.DetailInfo;
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
@Path("/firstAid/detail-info")
@Produces(MediaType.APPLICATION_JSON)
public class DetailInfoResource {
  final static Logger logger = Logger.getLogger(DetailInfoResource.class);
  private DetailInfoDao detailInfoDao;

  public DetailInfoResource(DetailInfoDao detailInfoDao) {
    this.detailInfoDao = detailInfoDao;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addDetailInfo(DetailInfo detailInfo) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      //System.out.println("User Json:"+objectMapper.writeValueAsString(users1));
      Integer id = detailInfoDao.addDetailInfo(detailInfo);
      detailInfo.setId(id);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, detailInfo, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      e.printStackTrace();
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateDetailInfo(@PathParam("id") Integer id, DetailInfo detailInfo) {
    try {
      detailInfo.setId(id);
      detailInfoDao.updateDetailInfo(detailInfo);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, detailInfo, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      e.printStackTrace();
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response deleteDetailInfo(@PathParam("id") Integer id) {
    try {
      detailInfoDao.deleteDetailInfo(id);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, null, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }

  @GET
  public Response getDetailInfo(@QueryParam("id") Optional<Integer> id) {
    try {
      String query = " WHERE id IS NOT NULL";
      if (id.isPresent()) {
        query = query + " AND id = " + id.get();
      }
      System.out.println("Query is:" + query);
      List<DetailInfo> detailInfoList = detailInfoDao.getDetailInfo(query);
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.OK_200, detailInfoList, HttpStatus.getMessage(HttpStatus.OK_200))).build();
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Response.ok().type("application/json").entity(Responses.ok(HttpStatus.BAD_REQUEST_400, null, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))).build();
    }
  }
}
