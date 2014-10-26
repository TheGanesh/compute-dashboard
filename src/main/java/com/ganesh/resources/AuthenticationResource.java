package com.ganesh.resources;

import com.ganesh.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Path("/auth")
public class AuthenticationResource {

    @Autowired
    AuthenticationService authenticationService;

    @POST
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        if(authenticationService.isValidCredentials(username,password)){
            return Response.ok().build();
        }
        return Response.status(401).build();

    }

}
