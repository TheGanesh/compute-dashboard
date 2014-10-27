package com.ganesh.resources

import com.ganesh.service.AuthenticationService
import com.ganesh.service.DashboardService
import com.ganesh.service.EC2Instance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.ws.rs.FormParam
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Created by Ganesh Kandisa.
 * This is the Jersey resource for all of the available REST APIs.
 * '/auth' resource is used for authentication of user provided username/password
 * '/describe/ec2instances' resource is used to fetch all aws ec2 instances details.
 */
@Component
@Path("/")
class DashBoardResource {

    @Autowired
    DashboardService dashboardService

    @Autowired
    AuthenticationService authenticationService;

    @POST
    @Path("/auth")
    public Response doAuthenticate(@FormParam("username") String username, @FormParam("password") String password) {
        if (authenticationService.isValidCredentials(username, password)) {
            return Response.ok().build()
        }
        return Response.status(401).build()

    }


    @GET
    @Path("/describe/ec2instances")
    @Produces(MediaType.APPLICATION_JSON)
    public Response describeEC2Instances() {

        List<EC2Instance> instancesDetails = dashboardService.describeEC2Instances()

        return Response.ok(instancesDetails).build()
    }

}
