package com.ganesh.filters

import com.ganesh.service.AuthenticationService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.annotation.Resource
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Ganesh Kandisa.
 * This filter authenticates all REST API requests,all http requests should come with header named "X-AUTH-TOKEN",
 * and its value should be one of the whitelisted tokens in the configuration.
 */
@Component
@Slf4j
class DashboardAPIAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationService authenticationService

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = request as HttpServletRequest
        HttpServletResponse httpServletResponse = response as HttpServletResponse

        String authHeader = httpServletRequest.getHeader("X-AUTH-TOKEN")

        if (httpServletRequest?.requestURI?.contains('/api') && authenticationService.isValidAuthHeader(authHeader)) {
            filterChain.doFilter(request, response)
        } else {
            handleAuthFailure(httpServletRequest, httpServletResponse)
        }

    }


    private void handleAuthFailure(HttpServletRequest request, HttpServletResponse response) {

        log.info("Conditions not satisfied for authorization: Unauthorized")
        response.contentType = "application/json"
        response.status = HttpStatus.UNAUTHORIZED.value()
        String responseJson = '{"errors": [{"errorCode": "UNAUTHORIZED","errorMessage": "Please provide valid auth header."}]}'
        ServletOutputStream servletOut = response.getOutputStream()
        servletOut?.write(responseJson.getBytes())
        servletOut?.close()
    }


}
