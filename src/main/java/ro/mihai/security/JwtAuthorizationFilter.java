package ro.mihai.security;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  // the following resources don't need to be authorized
  private static final String[] AUTH_EXCEPTIONS = {

      // login endpoint
      "POST /login",

      // create positions
      "POST /positions",

      // create users
      "POST /users",
      // edit users
      "PUT /users",
      // delete users
      "DELETE /users",

      // swagger URLs
      "GET /swagger-ui/",
      "GET /v3/api-docs",

      // the UI (Admin app) URLs
      "GET /index.html",
      "GET /map.html",
      "GET /js/",
      "GET /favicon.ico"
  };

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    //get the Authorization header from request
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null) { // null check on Authorization header
      response.sendError(403); // return a 403-Forbidden error if the header is null
      return;
    }

    //remove 'Bearer' from the token header
    String jwtToken = authHeader.substring(7);

    try {
      JwtUtil.validateToken(jwtToken); // verify that the JWT token is valid
    } catch (JwtException e) {
      response.sendError(403); // return a 403-Forbidden error if the token is invalid
      return;
    }

    filterChain.doFilter(request, response);
  }

  // exclude the URL exceptions from the authorization filtering
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String requestUrl = request.getRequestURI();
    String requestMethod = request.getMethod();

    //System.out.println("URL === " + requestUrl);

//    return (requestUrl.equals("/") && requestMethod.equals("GET"))
//            || Arrays.stream(AUTH_EXCEPTIONS).anyMatch(s -> (requestMethod + " " + requestUrl).startsWith(s));

    return ( requestMethod.equals("GET"))
        || Arrays.stream(AUTH_EXCEPTIONS).anyMatch(s -> (requestMethod + " " + requestUrl).startsWith(s));

//    return Arrays.stream(AUTH_EXCEPTIONS)
//        .anyMatch(s -> (requestMethod + " " + requestUrl).startsWith(s));
  }
}
