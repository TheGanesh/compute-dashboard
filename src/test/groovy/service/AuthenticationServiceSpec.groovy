package service

import com.ganesh.resources.DashBoardResource
import com.ganesh.service.AuthenticationService
import com.ganesh.service.DashboardService
import org.spockframework.compiler.model.Spec
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.core.Response

/**
 * Created by Ganesh Kandisa.
 */
class AuthenticationServiceSpec extends Specification {


    AuthenticationService authenticationService

    void setup() {
        authenticationService = new AuthenticationService(credentialsMap: ['admin': 'password'], apiAuthEntriesList: ['sampleToken'])
    }

    @Unroll
    def "isValidCredentials method test scenarios"() {


        when:
        boolean result = authenticationService.isValidCredentials(username, password)

        then:
        result == expectedBoolean


        where:

        username   | password   | expectedBoolean
        "username" | 'password' | false
        "admin"    | 'password' | true

    }

    @Unroll
    def "isValidAuthHeader method test scenarios"() {


        when:
        boolean result = authenticationService.isValidAuthHeader(authHeader)

        then:
        result == expectedBoolean


        where:

        authHeader    | expectedBoolean
        "sampleToken" | true
        "token"       | false

    }

}
