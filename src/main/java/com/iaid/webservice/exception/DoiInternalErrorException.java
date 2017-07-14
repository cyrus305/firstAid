package com.iaid.webservice.exception;


import com.iaid.webservice.dto.Responses;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Mukesh Maharjan on 1/18/2016.
 */
public class DoiInternalErrorException extends WebApplicationException {

  public DoiInternalErrorException(String msg) {
    super(Response.ok().type("application/json").entity(Responses.ok(HttpStatus.INTERNAL_SERVER_ERROR_500, null, msg)).build());
  }

  public DoiInternalErrorException(String msg, Throwable th) {
    super(th, Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).
        entity(msg).type("application/json").build());
  }
}
