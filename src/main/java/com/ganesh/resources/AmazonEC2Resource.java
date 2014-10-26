package com.ganesh.resources;

import com.ganesh.service.AmazonEC2Service;
import com.ganesh.service.EC2Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 *
 */
@Component
@Path("/describe")
public class AmazonEC2Resource {

    @Autowired
    AmazonEC2Service amazonEC2Service;

    @GET
    @Path("/ec2instances")
    @Produces(MediaType.APPLICATION_JSON)
    public Response describeEC2Instances() {
        List<EC2Instance>  instancesDetails = amazonEC2Service.fetchAmazonEC2InstancesDetails();
        return Response.ok(instancesDetails).build();
    }
}
