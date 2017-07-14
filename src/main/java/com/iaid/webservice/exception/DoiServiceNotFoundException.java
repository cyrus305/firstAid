package com.iaid.webservice.exception;

import com.iaid.webservice.dto.Responses;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Mukesh Maharjan on 12/24/2015.
 * Redirect all Not Found Error here
 */
public class DoiServiceNotFoundException extends WebApplicationException {

  public DoiServiceNotFoundException(String msg) {
    super(Response.ok().type("application/json").entity(Responses.ok(HttpStatus.NOT_FOUND_404, null, msg)).build());
  }

  public DoiServiceNotFoundException(String msg, Throwable th) {
    super(th, Response.status(HttpStatus.NOT_FOUND_404).
        entity(Responses.error(msg, HttpStatus.NOT_FOUND_404)).
        type("application/json").build());
  }
}
