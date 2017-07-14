package com.iaid.webservice.filter;

import com.iaid.webservice.exception.DoiInternalErrorException;
import com.iaid.webservice.exception.DoiValidationException;
import com.iaid.webservice.utils.JwtUtils;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;

/**
 * Created by Crawlers on 5/19/2016.
 */
public class AuthorisationFilter implements ContainerRequestFilter {
  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    if (!requestContext.getUriInfo().getPath().contains("login")){
      // Get the HTTP Authorization header from the request
      String authorizationHeader =
          requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
      // Check if the HTTP Authorization header is present and formatted correctly
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        //throw new NotAuthorizedException("Authorization header must be provided");
        throw  new DoiValidationException("Token is not valid");
      }
      // Extract the token from the HTTP Authorization header
      String token = authorizationHeader.substring("Bearer".length()).trim();
      KeyStore keyStore = JwtUtils.loadKeystore(JwtUtils.wrkDir, JwtUtils.keyStrPwd);
      Key key = null;
      try {
        key = JwtUtils.retriveKey(keyStore, JwtUtils.keyName, JwtUtils.keyPwd);
      }catch (Exception e){
        e.printStackTrace();
        throw new DoiInternalErrorException("Token cannot be parsed");
      }
      if (!JwtUtils.isValid(key, token)){
        throw  new DoiValidationException("Token is not valid");
      }
    }
  }
}
