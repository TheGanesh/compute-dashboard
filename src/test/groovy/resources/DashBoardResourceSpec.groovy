package resources

import com.ganesh.resources.DashBoardResource
import com.ganesh.service.AuthenticationService
import com.ganesh.service.DashboardService
import com.ganesh.service.EC2Instance
import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.Response

/**
 * Created by Ganesh Kandisa.
 */
class DashBoardResourceSpec extends Specification {

    DashBoardResource dashBoardResource
    AuthenticationService authenticationService
    DashboardService dashboardService


    void setup() {
        authenticationService = Mock(AuthenticationService)
        dashboardService = Mock(DashboardService)
        dashBoardResource = new DashBoardResource(authenticationService: authenticationService, dashboardService: dashboardService)
    }


    @Unroll
    def "doAuthenticate method success scenarios"() {


        when:
        Response response = dashBoardResource.doAuthenticate(username, password)

        then:
        1 * authenticationService.isValidCredentials(username, password) >> isValidCredentails
        response.status == 200


        where:

        username   | password   | isValidCredentails
        "username" | 'password' | true
        "admin"    | 'password' | true

    }

    @Unroll
    def "doAuthenticate method failure scenarios"() {


        when:
        Response response = dashBoardResource.doAuthenticate(username, password)

        then:
        1 * authenticationService.isValidCredentials(username, password) >> isValidCredentails
        response.status == 401


        where:

        username | password | isValidCredentails
        "admin"  | 'admin'  | false
        "temp"   | 'temp'   | false

    }

    @Unroll
    def "describeEC2Instances method success scenarios"() {

        setup:

        List<EC2Instance> instancesDetails = [Mock(EC2Instance)]

        when:
        Response response = dashBoardResource.describeEC2Instances()

        then:
        1 * dashboardService.describeEC2Instances() >> instancesDetails
        response.status == 200

    }

}
