package com.iaid.webservice.resources;

import com.iaid.webservice.dto.Responses;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Crawlers on 8/20/2016.
 */
public class BaseResource<T> {
  public Response response(int status, T t, String msg) {
    return Response.status(status).type("application/json").entity(Responses.ok(status, t, msg)).build();
  }

  public Response responseCustom(int status, Object obj, String msg) {
    return Response.status(status).type("application/json").entity(obj).build();
  }

  public Response responseList(int status, List<T> t, String msg) {
    return Response.status(status).type("application/json").entity(Responses.ok(status, t, msg)).build();
  }

  public Response failResponse() {
    return null;
  }
}
