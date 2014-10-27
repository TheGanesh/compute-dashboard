package com.ganesh.service

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.PropertiesCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.ec2.AmazonEC2
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesResult
import com.amazonaws.services.ec2.model.Instance
import com.amazonaws.services.ec2.model.Reservation
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.ws.rs.WebApplicationException

/**
 * Created by Ganesh Kandisa.
 * This is the service layer class for getting all cloud instances details.
 */
@Component
@Slf4j
class DashboardServiceImpl implements DashboardService {

    AmazonEC2 amazonEC2Client

    @PostConstruct
    public void initialize() throws Exception {

        AWSCredentials credentials = new PropertiesCredentials(getClass().getClassLoader().getResourceAsStream("AwsCredentials.properties"))
        amazonEC2Client = new AmazonEC2Client(credentials)
        Region usWest2 = Region.getRegion(Regions.US_WEST_2)
        amazonEC2Client.setRegion(usWest2)

    }

    /**
     * This method fetches all AWS EC2 instances details
     * @return List < EC2Instance >
     */
    public List<EC2Instance> describeEC2Instances() {

        List<EC2Instance> instancesDetailsList = []

        try {

            DescribeInstancesRequest instancesRequest = new DescribeInstancesRequest()
            DescribeInstancesResult instancesResult = amazonEC2Client.describeInstances(instancesRequest)


            instancesResult?.reservations?.each { Reservation reservation ->

                reservation?.instances?.each { Instance instance ->

                    EC2Instance instanceDetailsHolder = new EC2Instance()
                    instanceDetailsHolder.id = instance.instanceId
                    if (instance.publicDnsName) {
                        instanceDetailsHolder.name = instance.publicDnsName
                    } else {
                        instanceDetailsHolder.name = instance.privateDnsName
                    }
                    instanceDetailsHolder.az = instance.placement.availabilityZone
                    instanceDetailsHolder.privateIp = instance.privateIpAddress
                    instanceDetailsHolder.publicIp = instance.publicIpAddress
                    instanceDetailsHolder.type = instance.instanceType
                    instanceDetailsHolder.state = instance.state?.name

                    instancesDetailsList << instanceDetailsHolder
                }

            }

        } catch (Exception ex) {
            log.error(ex.localizedMessage)
            throw new WebApplicationException(500)
        }

        return instancesDetailsList

    }

}
