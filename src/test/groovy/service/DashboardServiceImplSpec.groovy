package service

import com.amazonaws.AmazonServiceException
import com.amazonaws.services.ec2.AmazonEC2
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesResult
import com.amazonaws.services.ec2.model.Instance
import com.amazonaws.services.ec2.model.InstanceState
import com.amazonaws.services.ec2.model.Placement
import com.amazonaws.services.ec2.model.Reservation
import com.ganesh.service.AuthenticationService
import com.ganesh.service.DashboardServiceImpl
import com.ganesh.service.EC2Instance
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

/**
 * Created by Ganesh Kandisa.
 */
class DashboardServiceImplSpec extends Specification {


    DashboardServiceImpl dashboardServiceImpl
    AmazonEC2 amazonEC2Client

    void setup() {
        amazonEC2Client = Mock(AmazonEC2)
        dashboardServiceImpl = new DashboardServiceImpl(amazonEC2Client: amazonEC2Client)
    }


    @Unroll
    def "describeEC2Instances method success scenarios"() {

        setup:

        DescribeInstancesResult instancesResult = new DescribeInstancesResult()

        Reservation reservation = new Reservation()
        Instance instance = new Instance(instanceId: 'id1', publicDnsName: "aws.amazon.com", placement: new Placement(availabilityZone: 'west'), privateIpAddress: '1.1.1.1', publicIpAddress: '2.2.2.2', instanceType: 'medium', state: new InstanceState(name: 'running'))
        reservation.instances = [instance]
        List<Reservation> reservations = [reservation]
        instancesResult.reservations = reservations

        amazonEC2Client.describeInstances(_ as DescribeInstancesRequest) >> instancesResult


        when:
        List<EC2Instance> instancesDetailsList = dashboardServiceImpl.describeEC2Instances()

        then:

        instancesDetailsList.get(0).id == 'id1'
        instancesDetailsList.get(0).name == 'aws.amazon.com'
        instancesDetailsList.get(0).az == 'west'
        instancesDetailsList.get(0).privateIp == '1.1.1.1'
        instancesDetailsList.get(0).publicIp == '2.2.2.2'
        instancesDetailsList.get(0).type == 'medium'
        instancesDetailsList.get(0).state == 'running'


    }

    @Unroll
    def "describeEC2Instances method failure scenarios"() {

        setup:

        amazonEC2Client.describeInstances(_ as DescribeInstancesRequest) >> {
            throw new AmazonServiceException('InternalError')
        }


        when:
        List<EC2Instance> instancesDetailsList = dashboardServiceImpl.describeEC2Instances()

        then:
        WebApplicationException webApplicationException = thrown()
        webApplicationException.response.status == 500

    }
}
