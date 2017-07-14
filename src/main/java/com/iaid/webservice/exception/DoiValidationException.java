package com.iaid.webservice.exception;


import com.iaid.webservice.dto.Responses;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mukesh Maharjan on 12/24/2015.
 * Redirect all Validation Error here
 */
public class DoiValidationException extends WebApplicationException {

  public DoiValidationException(String msg) {
    super(Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).type("application/json").entity(msg).build());
  }


  public DoiValidationException(String msg, Throwable th) {
    super(th, Response.status(HttpStatus.BAD_REQUEST_400).
        entity(Responses.error(msg, HttpStatus.BAD_REQUEST_400)).
        type("application/json").build());
  }

}
