package filters

import com.ganesh.filters.DashboardAPIAuthFilter
import com.ganesh.service.AuthenticationService
import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.FilterChain
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Ganesh Kandisa.
 */
class DashboardAPIAuthFilterSpec extends Specification {

    DashboardAPIAuthFilter dashboardAPIAuthFilter
    AuthenticationService authenticationService

    void setup() {
        authenticationService = Mock(AuthenticationService)
        dashboardAPIAuthFilter = new DashboardAPIAuthFilter(authenticationService: authenticationService)
    }


    @Unroll
    def "authentication filter success scenarios"() {

        setup:

        HttpServletRequest request = Mock(HttpServletRequest)
        request.getHeader('X-AUTH-TOKEN') >> authHeader
        HttpServletResponse response = Mock(HttpServletResponse)
        FilterChain filterChain = Mock(FilterChain)


        when:
        dashboardAPIAuthFilter.doFilterInternal(request, response, filterChain)

        then:
        1 * request.getRequestURI() >> requestPath
        1 * filterChain.doFilter(request, response)
        1 * authenticationService.isValidAuthHeader(authHeader) >> isValidToken



        where:

        authHeader         | requestPath                  | isValidToken
        "sampleTokenValue" | '/api/auth'                  | true
        "sampleTokenValue" | '/api/describe/ec2instances' | true

    }


    @Unroll
    def "authentication filter failure scenarios"() {

        setup:

        HttpServletRequest request = Mock(HttpServletRequest)
        ServletOutputStream servletOutputStream = Mock(ServletOutputStream)
        request.getHeader('X-AUTH-TOKEN') >> authHeader
        HttpServletResponse response = Mock(HttpServletResponse)
        FilterChain filterChain = Mock(FilterChain)


        when:

        dashboardAPIAuthFilter.doFilterInternal(request, response, filterChain)

        then:
        1 * response.setStatus(401)
        1 * response.setContentType('application/json')
        1 * response.getOutputStream() >> servletOutputStream
        1 * request.getRequestURI() >> requestPath
        1 * servletOutputStream.write(_ as byte[])
        1 * servletOutputStream.close()



        where:

        authHeader         | requestPath                  | isValidToken
        "sampleTokenValue" | '/dummy/auth'                | true
        null               | '/api/describe/ec2instances' | false


    }


}
